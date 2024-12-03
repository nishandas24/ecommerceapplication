package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.reponse.Response;

public interface UserService {
    Response userRegistration(UserDto request);
}
