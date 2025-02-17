package tableorder.domain;

import java.util.*;
import lombok.*;
import tableorder.domain.*;
import tableorder.infra.AbstractEvent;

@Data
@ToString
public class 주문됨 extends AbstractEvent {

    private Long id;
    private String requestInfo;
    private Long price;
    private Object orderStatus;
    private String paymentId;
    private String paymentStatus;
}
