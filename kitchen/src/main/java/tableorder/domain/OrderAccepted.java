package tableorder.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import tableorder.domain.*;
import tableorder.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderAccepted extends AbstractEvent {

    private Long id;
    private TableOrderId tableOrderId;
    private String orderInfo;
    private String requestInfo;
    private FoodStatus foodStatus;

    public OrderAccepted(Kitchen aggregate) {
        super(aggregate);
    }

    public OrderAccepted() {
        super();
    }
}
//>>> DDD / Domain Event
