package tableorder.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import tableorder.domain.*;
import tableorder.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class 주문됨 extends AbstractEvent {

    private Long id;
    private String requestInfo;
    private Long price;
    private OrderStatus orderStatus;
    private String paymentId;
    private String paymentStatus;

    public 주문됨(TableOrder aggregate) {
        super(aggregate);
    }

    public 주문됨() {
        super();
    }
}
//>>> DDD / Domain Event
