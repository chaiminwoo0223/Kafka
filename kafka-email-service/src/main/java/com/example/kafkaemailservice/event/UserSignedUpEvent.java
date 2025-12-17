package com.example.kafkaemailservice.event;

import tools.jackson.databind.ObjectMapper;

public record UserSignedUpEvent(
        Long userId,
        String name,
        String email
) {
    public static UserSignedUpEvent fromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, UserSignedUpEvent.class);
    }
}
