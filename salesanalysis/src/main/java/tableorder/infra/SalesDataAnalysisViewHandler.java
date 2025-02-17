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
public class SalesDataAnalysisViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private SalesDataAnalysisRepository salesDataAnalysisRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenSalesDataRegistered_then_CREATE_1(
        @Payload SalesDataRegistered salesDataRegistered
    ) {
        try {
            if (!salesDataRegistered.validate()) return;

            // view 객체 생성
            SalesDataAnalysis salesDataAnalysis = new SalesDataAnalysis();
            // view 객체에 이벤트의 Value 를 set 함
            salesDataAnalysis.setMenuName(salesDataRegistered.getMenu());
            salesDataAnalysis.setTotalOrderCount(
                salesDataRegistered.getTotalOrderCount()
            );
            salesDataAnalysis.setTotalPrice(
                salesDataRegistered.getTotalPrice()
            );
            // view 레파지 토리에 save
            salesDataAnalysisRepository.save(salesDataAnalysis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenSalesDataUpdated_then_UPDATE_1(
        @Payload SalesDataUpdated salesDataUpdated
    ) {
        try {
            if (!salesDataUpdated.validate()) return;
            // view 객체 조회
            Optional<SalesDataAnalysis> salesDataAnalysisOptional = salesDataAnalysisRepository.findById(
                salesDataUpdated.getId()
            );

            if (salesDataAnalysisOptional.isPresent()) {
                SalesDataAnalysis salesDataAnalysis = salesDataAnalysisOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                salesDataAnalysis.setTotalPrice(
                    salesDataUpdated.getTotalPrice()
                );
                salesDataAnalysis.setTotalOrderCount(
                    salesDataUpdated.getTotalOrderCount()
                );
                // view 레파지 토리에 save
                salesDataAnalysisRepository.save(salesDataAnalysis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
