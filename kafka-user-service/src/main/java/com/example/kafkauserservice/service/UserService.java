package com.example.kafkauserservice.service;

import com.example.kafkauserservice.domain.User;
import com.example.kafkauserservice.dto.SignUpRequestDto;
import com.example.kafkauserservice.dto.UserSignedUpEvent;
import com.example.kafkauserservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void signup(SignUpRequestDto request) {
        // 회원가입한 사용자 정보 DB에 저장
        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();

        User savedUser = userRepository.save(user);

        // 카프카에 메시지 전송
        UserSignedUpEvent event = UserSignedUpEvent.from(savedUser);

        this.kafkaTemplate.send("user.signed-up", toJsonString(event));
    }

    private String toJsonString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(object);
    }
}
