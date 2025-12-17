package com.example.kafkauserservice.controller;

import com.example.kafkauserservice.dto.SignUpRequestDto;
import com.example.kafkauserservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody SignUpRequestDto request) {
        userService.signup(request);

        return ResponseEntity.ok("회원가입 성공");
    }
}
