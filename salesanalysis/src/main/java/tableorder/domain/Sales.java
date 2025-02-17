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

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
