package local;

import local.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MyPageViewHandler {


    @Autowired
    private MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRequested_then_CREATE_1 (@Payload Requested requested) {
        try {
            if (requested.isMe()) {
                // view 객체 생성
                MyPage myPage = new MyPage();
                // view 객체에 이벤트의 Value 를 set 함
                myPage.setScreeningId(requested.getId());
                myPage.setCustNm(requested.getCustNm());
                myPage.setHospitalNm(requested.getHospitalNm());
                myPage.setStatus(requested.getStatus());
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
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
                List<MyPage> myPageList = myPageRepository.findByScreeningId(canceled.getId());
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setStatus(canceled.getStatus());
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
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
                List<MyPage> myPageList = myPageRepository.findByScreeningId(forceCanceled.getId());
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setStatus(forceCanceled.getStatus());
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
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
                List<MyPage> myPageList = myPageRepository.findByScreeningId(reservationCompleted.getScreeningId());
                for (MyPage myPage : myPageList) {
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setStatus(reservationCompleted.getStatus());
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}