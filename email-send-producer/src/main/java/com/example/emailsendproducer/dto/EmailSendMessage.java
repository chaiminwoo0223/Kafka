package com.example.emailsendproducer.dto;

public record EmailSendMessage(
        String from, // 발신자 이메일
        String to, // 수신자 이메일
        String subject, // 이메일 제목
        String body // 이메일 본문
) {
    public static EmailSendMessage from(SendEmailRequestDto request) {
        return new EmailSendMessage(
                request.from(),
                request.to(),
                request.subject(),
                request.body()
        );
    }
}
