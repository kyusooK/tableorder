package tableorder.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
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

    @Embedded
    private TableOrderId tableOrderId;

    @ElementCollection
    private List<MenuId> menuId;

    private String orderInfo;

    private String requestInfo;

    @Enumerated(EnumType.STRING)
    private FoodStatus foodStatus;

    public static KitchenRepository repository() {
        KitchenRepository kitchenRepository = KitchenApplication.applicationContext.getBean(
            KitchenRepository.class
        );
        return kitchenRepository;
    }

    //<<< Clean Arch / Port Method
    public void cook(CookCommand cookCommand) {
        //implement business logic here:

        Cooked cooked = new Cooked(this);
        cooked.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void serve(ServeCommand serveCommand) {
        //implement business logic here:

        Served served = new Served(this);
        served.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void acceptOrder(OrderPlaced orderPlaced) {
        //implement business logic here:

        /** Example 1:  new item 
        Kitchen kitchen = new Kitchen();
        repository().save(kitchen);

        OrderAccepted orderAccepted = new OrderAccepted(kitchen);
        orderAccepted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if orderPlaced.menuId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> tableOrderMap = mapper.convertValue(orderPlaced.getMenuId(), Map.class);

        repository().findById(orderPlaced.get???()).ifPresent(kitchen->{
            
            kitchen // do something
            repository().save(kitchen);

            OrderAccepted orderAccepted = new OrderAccepted(kitchen);
            orderAccepted.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
