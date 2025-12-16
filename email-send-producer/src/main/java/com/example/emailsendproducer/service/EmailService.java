package com.example.emailsendproducer.service;

import com.example.emailsendproducer.dto.EmailSendMessage;
import com.example.emailsendproducer.dto.SendEmailRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class EmailService {
    // Kafka에 넣는 메시지는 Key-Value 형태로 넣을 수도 있고,
    // Key는 생략한 채로 Value만 넣을 수도 있다.
    private final KafkaTemplate<String, String> kafkaTemplate;

    // 직렬화: 자바 객체 -> 문자열
    public void sendEmail(SendEmailRequestDto request) {
        EmailSendMessage message = EmailSendMessage.from(request);

        this.kafkaTemplate.send("email.send", toJsonString(message));
    }

    // 객체를 Json 형태의 String으로 만들어주는 메서드
    private String toJsonString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(object);
    }
}
