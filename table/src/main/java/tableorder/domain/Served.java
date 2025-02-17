package tableorder.domain;

import java.util.*;
import lombok.*;
import tableorder.domain.*;
import tableorder.infra.AbstractEvent;

@Data
@ToString
public class Served extends AbstractEvent {

    private Long id;
    private Object foodStatus;
    private Object tableOrderId;
}
