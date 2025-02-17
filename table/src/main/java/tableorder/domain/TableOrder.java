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

    private Long price;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Long paymentId;

    private String paymentStatus;

    private Date orderDate;

    @Embedded
    private MenuId menuId;

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

    public void order() {
        //
    }

    //<<< Clean Arch / Port Method
    public static void updateOrderStatus(OrderAccepted orderAccepted) {
        //implement business logic here:

        /** Example 1:  new item 
        TableOrder tableOrder = new TableOrder();
        repository().save(tableOrder);

        */

        /** Example 2:  finding and process
        
        // if orderAccepted.tableOrderIdmenuId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> kitchenMap = mapper.convertValue(orderAccepted.getTableOrderId(), Map.class);
        // Map<Long, Object> kitchenMap = mapper.convertValue(orderAccepted.getMenuId(), Map.class);

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
        
        // if cooked.tableOrderIdmenuId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> kitchenMap = mapper.convertValue(cooked.getTableOrderId(), Map.class);
        // Map<Long, Object> kitchenMap = mapper.convertValue(cooked.getMenuId(), Map.class);

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
        
        // if served.tableOrderIdmenuId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> kitchenMap = mapper.convertValue(served.getTableOrderId(), Map.class);
        // Map<Long, Object> kitchenMap = mapper.convertValue(served.getMenuId(), Map.class);

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
