package tableorder.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.*;
import lombok.Data;
import tableorder.TableApplication;
import tableorder.domain.OrderPlaced;
import tableorder.external.Menu;
import tableorder.external.MenuService;

@Entity
@Table(name = "TableOrder_table")
@Data
//<<< DDD / Aggregate Root
public class TableOrder  {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String requestInfo;

    private Long totalPrice;

    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    
    private Long paymentId;
    
    private String paymentStatus;
    
    private Date orderDate;
    
    @ElementCollection
    private List<MenuId> menuIds;

    public static TableOrderRepository repository(){
        TableOrderRepository tableOrderRepository = TableApplication.applicationContext.getBean(TableOrderRepository.class);
        return tableOrderRepository;
    }
//<<< Clean Arch / Port Method
    @PostPersist
    public void onPostPersist() {

        List<Long> menuIds = this.menuIds.stream().map(MenuId::getId).collect(Collectors.toList());

        List<Menu> menus = TableApplication.applicationContext
        .getBean(tableorder.external.MenuService.class)
        .getMenus(menuIds);
            
        if (menus != null && !menus.isEmpty()) {

            Long totalPrice = menus.stream()
                                .map(menu -> menu.getPrice() * menu.getStock())
                                .reduce(0L, Long::sum);
            
            LocalDateTime todayDate = LocalDateTime.now();
    
            this.setPrice(totalPrice);
            this.setOrderDate(Date.from(todayDate.atZone(ZoneId.systemDefault()).toInstant()));
    
            OrderPlaced orderPlaced = new OrderPlaced(this);
            orderPlaced.publishAfterCommit();
        } else {
            // 메뉴가 없을 경우 처리할 로직 (예: 예외 발생 또는 로그 기록)
            throw new RuntimeException("주문할 수 있는 메뉴가 없습니다.");
        }
    }

//<<< Clean Arch / Port Method
    public static void updateOrderStatus(OrderAccepted orderAccepted){
        
        
        
        ObjectMapper mapper = new ObjectMapper();
        Map<Long, Object> kitchenMap = mapper.convertValue(orderAccepted.getTableOrderId(), Map.class);

        repository().findById(Long.valueOf(kitchenMap.get("id").toString())).ifPresent(tableOrder->{
            
            tableOrder.setOrderStatus(OrderStatus.ORDERPLACED);
            repository().save(tableOrder);


        });

    }
    public static void updateOrderStatus(Cooked cooked){
        
        ObjectMapper mapper = new ObjectMapper();
        Map<Long, Object> kitchenMap = mapper.convertValue(cooked.getTableOrderId(), Map.class);

        repository().findById(Long.valueOf(kitchenMap.get("id").toString())).ifPresent(tableOrder->{
            
            tableOrder.setOrderStatus(OrderStatus.COOKED);
            repository().save(tableOrder);


        });

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateOrderStatus(Served served) {
        
        ObjectMapper mapper = new ObjectMapper();
        Map<Long, Object> kitchenMap = mapper.convertValue(served.getTableOrderId(), Map.class);

        repository().findById(Long.valueOf(kitchenMap.get("id").toString())).ifPresent(tableOrder->{
            
            tableOrder.setOrderStatus(OrderStatus.RESERVED);
            repository().save(tableOrder);


        });

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void changePaymentStatus(
        RequstPaymentCompleted requstPaymentCompleted
    ) {     

       repository().findById(requstPaymentCompleted.getItemId()).ifPresent(tableOrder->{
            tableOrder.setPaymentId(requstPaymentCompleted.getId());
            tableOrder.setPaymentStatus(requstPaymentCompleted.getStatus()); // do something
            repository().save(tableOrder);
        });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
