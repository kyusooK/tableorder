package tableorder.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
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
        //implement business logic here:

        /** Example 1:  new item 
        Sales sales = new Sales();
        repository().save(sales);

        SalesDataRegistered salesDataRegistered = new SalesDataRegistered(sales);
        salesDataRegistered.publishAfterCommit();
        SalesDataUpdated salesDataUpdated = new SalesDataUpdated(sales);
        salesDataUpdated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if orderPlaced.menuId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> tableOrderMap = mapper.convertValue(orderPlaced.getMenuId(), Map.class);

        repository().findById(orderPlaced.get???()).ifPresent(sales->{
            
            sales // do something
            repository().save(sales);

            SalesDataRegistered salesDataRegistered = new SalesDataRegistered(sales);
            salesDataRegistered.publishAfterCommit();
            SalesDataUpdated salesDataUpdated = new SalesDataUpdated(sales);
            salesDataUpdated.publishAfterCommit();

         });
        */
        //아이디 토대로 조회하였을 때 나온 MenuName과 등록된 값중 menu와 일치하는지 확인. 

        // 이후, 등록된 내용이 있을 경우 날짜를 조회하여 날짜가 일치하는지 확인 (다를경우 새로 등록 같은 경우 이전 정보 업데이트)

        // Sales sales = new Sales();

        // sales.setMenu(); // 받아온 id에서 조회하였을 때의 이름
        // sales.setOrderDate(orderPlaced.getOrderDate());
        // sales.setTotalOrderCount(1);
        // sales.setTotalPrice(); // 받아온 id에서 조회하였을 때의 가격

        // repository().save(sales);


        // SalesDataRegistered salesDataRegistered = new SalesDataRegistered(sales);
        //     salesDataRegistered.publishAfterCommit();


        // sales.setTotalOrderCount(sales.getTotalOrderCount + 1);
        // sales.setTotalPrice(sales.getTotalPrice() +) // 받아온 id에서 조회하였을 때 가격 더하기

        // SalesDataUpdated salesDataUpdated = new SalesDataUpdated(sales);
        // salesDataUpdated.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
