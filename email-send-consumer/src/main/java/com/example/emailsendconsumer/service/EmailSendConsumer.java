package com.example.emailsendconsumer.service;

import com.example.emailsendconsumer.dto.EmailSendMessage;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

@Service
public class EmailSendConsumer {

    @KafkaListener(
            topics = "email.send",
            groupId = "email-send-group", // 컨슈머 그룹 이름
            concurrency = "3" // 멀티 쓰레드를 활용해 병렬적으로 처리할 파티션의 개수
    )
    public void consume(String message) {
        System.out.println("Kafka로부터 받아온 메시지: " + message);

        EmailSendMessage emailSendMessage = EmailSendMessage.fromJson(message);

        // 잘못된 이메일 주소일 경우 실패 가정
        if (emailSendMessage.to().contains("fail")) {
            System.out.println("잘못된 이메일 주소로 인한 발송 실패");
            throw new RuntimeException("잘못된 이메일 주소로 인한 발송 실패");
        }

        // ... 실제 이메일 발송 로직은 생략 ...
        try {
            Thread.sleep(3000); // 이메일 발송을 하는 데 3초가 걸린다고 가정
        } catch (InterruptedException e) {
            throw new RuntimeException("이메일 발송 실패");
        }

        System.out.println("실제 이메일 발송 로직은 생략");
    }
}
