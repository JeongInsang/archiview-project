package com.ssafy.archiview.service.user;

import com.ssafy.archiview.dto.user.UserDto;
import com.ssafy.archiview.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    @Override
    public void userAdd(UserDto.AddRequestDto requestDto) {
        System.out.println(requestDto.toString());
        repository.save(requestDto.toEntity());
    }
}
