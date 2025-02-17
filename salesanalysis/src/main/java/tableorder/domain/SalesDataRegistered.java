package tableorder.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import tableorder.domain.*;
import tableorder.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SalesDataRegistered extends AbstractEvent {

    private Long id;
    private String menu;
    private Integer totalOrderCount;
    private Long totalPrice;
    private Date orderDate;

    public SalesDataRegistered(Sales aggregate) {
        super(aggregate);
    }

    public SalesDataRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
