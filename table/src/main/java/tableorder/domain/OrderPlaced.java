package tableorder.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import tableorder.domain.*;
import tableorder.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String requestInfo;
    private String orderInfo;
    private List<MenuId> menuIds;
    private Long totalPrice;
    private OrderStatus orderStatus;
    private String paymentId;
    private String paymentStatus;
    private Date orderDate;

    public OrderPlaced(TableOrder aggregate) {
        super(aggregate);
    }

    public OrderPlaced() {
        super();
    }
}
//>>> DDD / Domain Event
