package tableorder.domain;

import java.util.*;
import lombok.*;
import tableorder.domain.*;
import tableorder.infra.AbstractEvent;

@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String requestInfo;
    private String orderInfo;
    private Object menuId;
    private Long totalPrice;
    private Object orderStatus;
    private Date orderDate;
}
