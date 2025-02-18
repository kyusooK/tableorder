package tableorder.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "SalesDataAnalysis_table")
@Data
public class SalesDataAnalysis {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String menuName;
    private Integer totalOrderCount;
    private Long totalPrice;
    private Date orderDate;
}
