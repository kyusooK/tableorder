package tableorder.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import tableorder.domain.*;
import tableorder.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SalesDataUpdated extends AbstractEvent {

    private Long id;
    private String menu;
    private Integer totalOrderCount;
    private Long totalPrice;

    public SalesDataUpdated(Sales aggregate) {
        super(aggregate);
    }

    public SalesDataUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
