package tableorder.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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

    private String orderInfo;

    private Long totalPrice;

    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    
    private Long paymentId;
    
    private String paymentStatus;
    
    private Date orderDate;
    
    @ElementCollection
    private List<MenuId> menuId;

    public static TableOrderRepository repository(){
        TableOrderRepository tableOrderRepository = TableApplication.applicationContext.getBean(TableOrderRepository.class);
        return tableOrderRepository;
    }
//<<< Clean Arch / Port Method
    @PostPersist
    public void onPostPersist() {
        RestTemplate restTemplate = new RestTemplate();
        Long menuTotalPrice = 0L;
        StringBuilder orderBuilder = new StringBuilder("주문메뉴: ");
        ArrayList<MenuId> menuList = new ArrayList<>(menuId);

        for (int i = 0; i < menuList.size(); i++) {
            MenuId menuId = menuList.get(i);
            Long id = menuId.getId();
            String url = "http://localhost:8082/menus/" + id;

            try {
                Menu menu = restTemplate.getForObject(url, Menu.class);

                if (menu == null) {
                    throw new RuntimeException("메뉴가 존재하지 않습니다: " + id);
                }

                menuTotalPrice += menu.getPrice();
                
                // 메뉴 이름 추가
                orderBuilder.append(menu.getMenuName());
                
                // 마지막 메뉴가 아닌 경우에만 쉼표와 공백 추가
                if (i < menuList.size() - 1) {
                    orderBuilder.append(", ");
                }

            } catch (HttpClientErrorException e) {
                throw new RuntimeException("메뉴를 조회하는 중 오류 발생: " + e.getMessage());
            }
        }

        this.setOrderDate(new Date());
        this.setTotalPrice(menuTotalPrice);
        this.setOrderStatus(orderStatus);
        this.setOrderInfo(orderBuilder.toString());

        repository().save(this);

        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
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

    //<<< Clean Arch / Port Method
    public static void updateOrderStatus(Served served) {
        
        ObjectMapper mapper = new ObjectMapper();
        Map<Long, Object> kitchenMap = mapper.convertValue(served.getTableOrderId(), Map.class);

        repository().findById(Long.valueOf(kitchenMap.get("id").toString())).ifPresent(tableOrder->{
            
            tableOrder.setOrderStatus(OrderStatus.RESERVED);
            repository().save(tableOrder);


        });

    }

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
