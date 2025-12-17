package com.example.kafkauserservice.dto;

import com.example.kafkauserservice.domain.User;

public record UserSignedUpEvent(
        Long userId,
        String name,
        String email
) {
    public static UserSignedUpEvent from(User user) {
        return new UserSignedUpEvent(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
