package tableorder.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.scene.control.Menu;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.Data;
import tableorder.SalesanalysisApplication;
import tableorder.domain.SalesDataRegistered;
import tableorder.domain.SalesDataUpdated;

@Entity
@Table(name = "Sales_table")
@Data
//<<< DDD / Aggregate Root
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String menu;

    private Integer totalOrderCount;

    private Long totalPrice;

    private Date orderDate;

    public static SalesRepository repository() {
        SalesRepository salesRepository = SalesanalysisApplication.applicationContext.getBean(
            SalesRepository.class
        );
        return salesRepository;
    }

    //<<< Clean Arch / Port Method
    public static void registerSalesData(OrderPlaced orderPlaced) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            List<Map<String, Object>> menuIdList = mapper.convertValue(orderPlaced.getMenuId(), List.class);
            
            for (Map<String, Object> menuIdMap : menuIdList) {
                Long menuId = Long.valueOf(menuIdMap.get("id").toString());
                String url = "http://localhost:8082/menus/" + menuId;
                
                try {
                    ResponseEntity<Map> menu = restTemplate.getForEntity(url, Map.class);
                    
                    if (menu == null) {
                        throw new RuntimeException("메뉴가 존재하지 않습니다: " + menuId);
                    }
                    
                    String menuName = menu.getBody().get("menuName").toString();
                    Long menuPrice = Long.valueOf(menu.getBody().get("price").toString());
                    
                    // Convert orderPlaced date to LocalDate for comparison
                    LocalDate orderDate = orderPlaced.getOrderDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                    
                    Sales existingSales = repository().findByMenu(menuName);
                    
                    if (existingSales != null) {
                        // Convert existing sales date to LocalDate
                        LocalDate existingDate = existingSales.getOrderDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                        
                        if (orderDate.equals(existingDate)) {
                            // Same date - update existing record
                            existingSales.setTotalOrderCount(existingSales.getTotalOrderCount() + 1);
                            existingSales.setTotalPrice(existingSales.getTotalPrice() + menuPrice);
                            repository().save(existingSales);
                            
                            SalesDataUpdated salesDataUpdated = new SalesDataUpdated(existingSales);
                            salesDataUpdated.publishAfterCommit();
                        } else {
                            // Different date - create new record
                            Sales newSales = new Sales();
                            newSales.setMenu(menuName);
                            newSales.setTotalOrderCount(1);
                            newSales.setTotalPrice(menuPrice);
                            newSales.setOrderDate(orderPlaced.getOrderDate());
                            repository().save(newSales);
                            
                            SalesDataRegistered salesDataRegistered = new SalesDataRegistered(newSales);
                            salesDataRegistered.publishAfterCommit();
                        }
                    } else {
                        // Menu doesn't exist - create new record
                        Sales newSales = new Sales();
                        newSales.setMenu(menuName);
                        newSales.setTotalOrderCount(1);
                        newSales.setTotalPrice(menuPrice);
                        newSales.setOrderDate(orderPlaced.getOrderDate());
                        repository().save(newSales);
                        
                        SalesDataRegistered salesDataRegistered = new SalesDataRegistered(newSales);
                        salesDataRegistered.publishAfterCommit();
                    }
                    
                } catch (HttpClientErrorException e) {
                    throw new RuntimeException("메뉴를 조회하는 중 오류 발생: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("메뉴 ID 처리 중 오류 발생: " + e.getMessage());
        }
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
