package com.example.emailsendproducer.controller;

import com.example.emailsendproducer.dto.SendEmailRequestDto;
import com.example.emailsendproducer.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody SendEmailRequestDto request) {
        emailService.sendEmail(request);

        return ResponseEntity.ok("이메일 발송 요청 완료");
    }
}
