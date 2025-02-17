package tableorder.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import tableorder.domain.*;
import tableorder.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class Served extends AbstractEvent {

    private Long id;
    private FoodStatus foodStatus;
    private TableOrderId tableOrderId;

    public Served(Kitchen aggregate) {
        super(aggregate);
    }

    public Served() {
        super();
    }
}
//>>> DDD / Domain Event
