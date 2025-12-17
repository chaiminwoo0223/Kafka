package com.example.kafkaemailservice.service;

import com.example.kafkaemailservice.repository.EmailRepository;
import com.example.kafkaemailservice.domain.EmailLog;
import com.example.kafkaemailservice.event.UserSignedUpEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignedUpEventConsumer {
    private final EmailRepository emailRepository;

    @KafkaListener(
            topics = "user.signed-up",
            groupId = "email-service",
            concurrency = "3"
    )
    public void consume(String message) throws InterruptedException {
        UserSignedUpEvent event = UserSignedUpEvent.fromJson(message);

        // 실제 이메일 발송 로직은 생략
        String receiverEmail = event.email();
        String subject = event.name() + "님, 회원가입을 축하드립니다!";
        Thread.sleep(3000);
        System.out.println("이메일 발송 완료");

        // 이메일 발송 로그를 DB에 저장
        EmailLog emailLog = EmailLog.builder()
                .receiverUserId(event.userId())
                .receiverEmail(receiverEmail)
                .subject(subject)
                .build();

        emailRepository.save(emailLog);
    }
}
