package com.example.emailsendconsumer.dto;

import tools.jackson.databind.ObjectMapper;

public record EmailSendMessage(
        String from, // 발신자 이메일
        String to, // 수신자 이메일
        String subject, // 이메일 제목
        String body // 이메일 본문
) {
    public static EmailSendMessage fromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, EmailSendMessage.class);
    }
}
