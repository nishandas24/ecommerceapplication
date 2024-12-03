package com.example.ecommerce.service.serviceImpl;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.reponse.Response;
import com.example.ecommerce.repository.UserJpaRepository;
import com.example.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;


    @Override
    public Response userRegistration(UserDto request) {
        return null;
    }
}
