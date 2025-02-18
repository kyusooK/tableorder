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
    private List<MenuId> menuIds;
    private Long totalPrice;
    private OrderStatus orderStatus;
    private Date orderDate;
    private String orderInfo;

    public OrderPlaced(TableOrder aggregate) {
        super(aggregate);
    }

    public OrderPlaced() {
        super();
    }
}
//>>> DDD / Domain Event
