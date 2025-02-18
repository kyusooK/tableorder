package tableorder.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import tableorder.config.kafka.KafkaProcessor;
import tableorder.domain.*;

@Service
public class OrderPageViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private OrderPageRepository orderPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderAccepted_then_CREATE_1(
        @Payload OrderAccepted orderAccepted
    ) {
        try {
            if (!orderAccepted.validate()) return;

            // view 객체 생성
            OrderPage orderPage = new OrderPage();
            // view 객체에 이벤트의 Value 를 set 함
            orderPage.setOrderInfo(orderAccepted.getOrderInfo());
            orderPage.setRequestInfo(orderAccepted.getRequestInfo());
            orderPage.setOrderStatus(
                String.valueOf(orderAccepted.getFoodStatus())
            );
            orderPage.setOrderId(orderAccepted.getId());
            // view 레파지 토리에 save
            orderPageRepository.save(orderPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCooked_then_UPDATE_1(@Payload Cooked cooked) {
        try {
            if (!cooked.validate()) return;
            // view 객체 조회

            List<OrderPage> orderPageList = orderPageRepository.findByOrderId(
                cooked.getId()
            );
            for (OrderPage orderPage : orderPageList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderPage.setOrderStatus(
                    String.valueOf(cooked.getFoodStatus())
                );
                // view 레파지 토리에 save
                orderPageRepository.save(orderPage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenServed_then_UPDATE_2(@Payload Served served) {
        try {
            if (!served.validate()) return;
            // view 객체 조회

            List<OrderPage> orderPageList = orderPageRepository.findByOrderId(
                served.getId()
            );
            for (OrderPage orderPage : orderPageList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderPage.setOrderStatus(
                    String.valueOf(served.getFoodStatus())
                );
                // view 레파지 토리에 save
                orderPageRepository.save(orderPage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenServed_then_DELETE_1(@Payload Served served) {
        try {
            if (!served.validate()) return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
