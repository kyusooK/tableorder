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
    SalesRepository salesRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='주문됨'"
    )
    public void whenever주문됨_RegisterSalesData(@Payload 주문됨 주문됨) {
        주문됨 event = 주문됨;
        System.out.println(
            "\n\n##### listener RegisterSalesData : " + 주문됨 + "\n\n"
        );

        // Sample Logic //
        Sales.registerSalesData(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
