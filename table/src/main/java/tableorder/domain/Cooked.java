package tableorder.domain;

import java.util.*;
import lombok.*;
import tableorder.domain.*;
import tableorder.infra.AbstractEvent;

@Data
@ToString
public class Cooked extends AbstractEvent {

    private Long id;
    private Object foodStatus;
}
