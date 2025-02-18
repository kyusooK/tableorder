package tableorder.domain;

import java.util.*;
import lombok.*;
import tableorder.domain.*;
import tableorder.infra.AbstractEvent;

@Data
@ToString
public class OrderAccepted extends AbstractEvent {

    private Long id;
    private Object tableOrderId;
    private String orderInfo;
    private String requestInfo;
    private Object foodStatus;
}
