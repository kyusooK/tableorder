package tableorder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.*;
import lombok.Data;
import tableorder.KitchenApplication;
import tableorder.domain.OrderAccepted;

@Entity
@Table(name = "Kitchen_table")
@Data
//<<< DDD / Aggregate Root
public class Kitchen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderInfo;

    private String requestInfo;

    @Enumerated(EnumType.STRING)
    private FoodStatus foodStatus;

    @Embedded
    private TableOrderId tableOrderId;

    public static KitchenRepository repository() {
        KitchenRepository kitchenRepository = KitchenApplication.applicationContext.getBean(
            KitchenRepository.class
        );
        return kitchenRepository;
    }

    public static void acceptOrder(OrderPlaced orderPlaced) {
    
        Kitchen kitchen = new Kitchen();
        kitchen.setTableOrderId(new TableOrderId(orderPlaced.getId()));
        kitchen.setFoodStatus(FoodStatus.ACCEPTED);
        kitchen.setRequestInfo(orderPlaced.getRequestInfo());
        kitchen.setOrderInfo(orderPlaced.getOrderInfo());
        
        // 데이터베이스에 저장
        repository().save(kitchen);
    
        OrderAccepted orderAccepted = new OrderAccepted(kitchen);
        orderAccepted.publishAfterCommit();
    }

    public void cook(CookCommand cookCommand) {

        repository().findById(this.getId()).ifPresent(kitchen ->{
            
            kitchen.setFoodStatus(cookCommand.getFoodStatus());

            Cooked cooked = new Cooked(this);
            cooked.publishAfterCommit();
        });
    }

    public void serve(ServeCommand serveCommand) {
        //implement business logic here:

        repository().findById(this.getId()).ifPresent(kitchen ->{
            
            kitchen.setFoodStatus(serveCommand.getFoodStatus());

            Served served = new Served(this);
            served.publishAfterCommit();
        });
    }


}
