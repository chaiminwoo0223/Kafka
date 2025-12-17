package com.example.kafkauserservice.dto;

public record SignUpRequestDto(
        String name,
        String email,
        String password
) {
}
