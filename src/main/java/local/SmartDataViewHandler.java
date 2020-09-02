package local;

import local.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmartDataViewHandler {


    @Autowired
    private SmartDataRepository smartDataRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRequested_then_CREATE_1 (@Payload Requested requested) {
        try {
            if (requested.isMe()) {
                // view 객체 생성
                SmartData smartData = new SmartData();
                // view 객체에 이벤트의 Value 를 set 함
                smartData.setScreeningId(requested.getId());
                smartData.setCustNm(requested.getCustNm());
                smartData.setHospitalNm(requested.getHospitalNm());
                smartData.setStatus(requested.getStatus());
                // view 레파지 토리에 save
                smartDataRepository.save(smartData);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenCanceled_then_UPDATE_1(@Payload Canceled canceled) {
        try {
            if (canceled.isMe()) {
                // view 객체 조회
                List<SmartData> smartDataList = smartDataRepository.findByScreeningId(canceled.getId());
                for(SmartData smartData : smartDataList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    smartData.setStatus(canceled.getStatus());
                    // view 레파지 토리에 save
                    smartDataRepository.save(smartData);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenForceCanceled_then_UPDATE_2(@Payload ForceCanceled forceCanceled) {
        try {
            if (forceCanceled.isMe()) {
                // view 객체 조회
                List<SmartData> smartDataList = smartDataRepository.findByScreeningId(forceCanceled.getId());
                for(SmartData smartData : smartDataList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    smartData.setStatus(forceCanceled.getStatus());
                    // view 레파지 토리에 save
                    smartDataRepository.save(smartData);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCompleted_then_UPDATE_3(@Payload ReservationCompleted reservationCompleted) {
        try {
            if (reservationCompleted.isMe()) {
                // view 객체 조회
                List<SmartData> smartDataList = smartDataRepository.findByScreeningId(reservationCompleted.getScreeningId());
                for (SmartData smartData : smartDataList) {
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    smartData.setStatus(reservationCompleted.getStatus());
                    // view 레파지 토리에 save
                    smartDataRepository.save(smartData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}