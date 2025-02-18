package tableorder.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import tableorder.TableApplication;
import tableorder.domain.OrderPlaced;

@Entity
@Table(name = "TableOrder_table")
@Data
//<<< DDD / Aggregate Root
public class TableOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String requestInfo;

    private Long totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Long paymentId;

    private String paymentStatus;

    private Date orderDate;

    private String orderInfo;

    @ElementCollection
    private List<MenuIds> menuIds;

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    public static TableOrderRepository repository() {
        TableOrderRepository tableOrderRepository = TableApplication.applicationContext.getBean(
            TableOrderRepository.class
        );
        return tableOrderRepository;
    }

    //<<< Clean Arch / Port Method
    public static void updateOrderStatus(OrderAccepted orderAccepted) {
        //implement business logic here:

        /** Example 1:  new item 
        TableOrder tableOrder = new TableOrder();
        repository().save(tableOrder);

        */

        /** Example 2:  finding and process
        
        // if orderAccepted.tableOrderId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> kitchenMap = mapper.convertValue(orderAccepted.getTableOrderId(), Map.class);

        repository().findById(orderAccepted.get???()).ifPresent(tableOrder->{
            
            tableOrder // do something
            repository().save(tableOrder);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateOrderStatus(Cooked cooked) {
        //implement business logic here:

        /** Example 1:  new item 
        TableOrder tableOrder = new TableOrder();
        repository().save(tableOrder);

        */

        /** Example 2:  finding and process
        
        // if cooked.tableOrderId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> kitchenMap = mapper.convertValue(cooked.getTableOrderId(), Map.class);

        repository().findById(cooked.get???()).ifPresent(tableOrder->{
            
            tableOrder // do something
            repository().save(tableOrder);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateOrderStatus(Served served) {
        //implement business logic here:

        /** Example 1:  new item 
        TableOrder tableOrder = new TableOrder();
        repository().save(tableOrder);

        */

        /** Example 2:  finding and process
        
        // if served.tableOrderId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> kitchenMap = mapper.convertValue(served.getTableOrderId(), Map.class);

        repository().findById(served.get???()).ifPresent(tableOrder->{
            
            tableOrder // do something
            repository().save(tableOrder);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void changePaymentStatus(
        RequstPaymentCompleted requstPaymentCompleted
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        TableOrder tableOrder = new TableOrder();
        repository().save(tableOrder);

        */

        /** Example 2:  finding and process
        

        repository().findById(requstPaymentCompleted.get???()).ifPresent(tableOrder->{
            
            tableOrder // do something
            repository().save(tableOrder);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
