package tableorder.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import tableorder.domain.*;
import tableorder.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class MenuRegistere extends AbstractEvent {

    private Long id;
    private String menuName;
    private Long price;
    private String menuInfo;
    private String ingredients;
    private String requestInfo;
    private FoodStatus foodStatus;

    public MenuRegistere(Menu aggregate) {
        super(aggregate);
    }

    public MenuRegistere() {
        super();
    }
}
//>>> DDD / Domain Event
