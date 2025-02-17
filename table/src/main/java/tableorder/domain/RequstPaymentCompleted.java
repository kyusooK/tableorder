package tableorder.domain;

import java.util.*;
import lombok.*;
import tableorder.domain.*;
import tableorder.infra.AbstractEvent;

@Data
@ToString
public class RequstPaymentCompleted extends AbstractEvent {

    private Long id;
    private Long itemId;
    private String paymentId;
    private Integer price;
    private String name;
    private String buyerName;
    private String buyerTel;
    private String buyerEmail;
    private String status;
}
