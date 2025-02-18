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
    private List<MenuId> menuIds;

    public static TableOrderRepository repository(){
        TableOrderRepository tableOrderRepository = TableApplication.applicationContext.getBean(TableOrderRepository.class);
        return tableOrderRepository;
    }
//<<< Clean Arch / Port Method
    @PostPersist
    public void onPostPersist() {
        // List<Long> menuIds = this.menuIds.stream().map(MenuId::getId).collect(Collectors.toList());

        // // 메뉴를 가져오는 부분에서 JSON 형식 확인
        // String jsonResponse = TableApplication.applicationContext
        //     .getBean(MenuService.class)
        //     .getMenus(menuIds); // JSON 응답을 String으로 받음

        // ObjectMapper objectMapper = new ObjectMapper();
        // List<Menu> menus;

        // try {
        //     // JSON 문자열을 List<Menu>로 변환
        //     JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        //     menus = objectMapper.convertValue(jsonNode.get("menus"), objectMapper.getTypeFactory().constructCollectionType(List.class, Menu.class));
        // } catch (JsonProcessingException e) {
        //     throw new RuntimeException("메뉴 정보를 가져오는 중 오류가 발생했습니다.", e);
        // }

        // if (menus != null && !menus.isEmpty()) {
        //     Long totalPrice = menus.stream()
        //         .map(menu -> menu.getPrice() * menu.getStock())
        //         .reduce(0L, Long::sum);
            
        //     LocalDateTime todayDate = LocalDateTime.now();
        //     this.setTotalPrice(totalPrice);
        //     this.setOrderDate(Date.from(todayDate.atZone(ZoneId.systemDefault()).toInstant()));
    
        //     OrderPlaced orderPlaced = new OrderPlaced(this);
        //     orderPlaced.publishAfterCommit();
        // }

        // if (menus == null || menus.isEmpty()) {
        //     System.out.println("메뉴 리스트가 비어 있습니다. 요청한 menuIds: " + menuIds);
        //     throw new RuntimeException("주문할 수 있는 메뉴가 없습니다.");
        // }

        RestTemplate restTemplate = new RestTemplate();
        Long menuTotalPrice = 0L;
        String order = "";

        for (MenuId menuId : new ArrayList<>(menuIds)) {
            // MenuId에서 ID를 추출하여 URL 구성
            Long id = menuId.getId(); // MenuId에서 Long 타입의 ID 추출
            String url = "http://localhost:8082/menus/" + id; // Menu API URL
    
            try {
                Menu menu = restTemplate.getForObject(url, Menu.class); // Menu 조회
    
                if (menu == null) {
                    throw new RuntimeException("메뉴가 존재하지 않습니다: " + id); // 메뉴가 없을 경우 예외 발생
                }
    
                menuTotalPrice += menu.getPrice();
                order += "주문 메뉴: " + menu.getMenuName() + " / ";

                // 주문일자를 오늘로 변환하여 저장
            } catch (HttpClientErrorException e) {
                throw new RuntimeException("메뉴를 조회하는 중 오류 발생: " + e.getMessage()); // HTTP 오류 처리
            }
        }

        this.setOrderDate(new Date());
        this.setTotalPrice(menuTotalPrice);
        this.setOrderStatus(orderStatus);
        this.setOrderInfo(order);

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
