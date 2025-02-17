package tableorder.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import tableorder.config.kafka.KafkaProcessor;
import tableorder.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    TableOrderRepository tableOrderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderAccepted'"
    )
    public void wheneverOrderAccepted_UpdateOrderStatus(
        @Payload OrderAccepted orderAccepted
    ) {
        OrderAccepted event = orderAccepted;
        System.out.println(
            "\n\n##### listener UpdateOrderStatus : " + orderAccepted + "\n\n"
        );

        // Sample Logic //
        TableOrder.updateOrderStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Cooked'"
    )
    public void wheneverCooked_UpdateOrderStatus(@Payload Cooked cooked) {
        Cooked event = cooked;
        System.out.println(
            "\n\n##### listener UpdateOrderStatus : " + cooked + "\n\n"
        );

        // Sample Logic //
        TableOrder.updateOrderStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Served'"
    )
    public void wheneverServed_UpdateOrderStatus(@Payload Served served) {
        Served event = served;
        System.out.println(
            "\n\n##### listener UpdateOrderStatus : " + served + "\n\n"
        );

        // Sample Logic //
        TableOrder.updateOrderStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='RequstPaymentCompleted'"
    )
    public void wheneverRequstPaymentCompleted_ChangePaymentStatus(
        @Payload RequstPaymentCompleted requstPaymentCompleted
    ) {
        RequstPaymentCompleted event = requstPaymentCompleted;
        System.out.println(
            "\n\n##### listener ChangePaymentStatus : " +
            requstPaymentCompleted +
            "\n\n"
        );

        // Sample Logic //
        TableOrder.changePaymentStatus(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
