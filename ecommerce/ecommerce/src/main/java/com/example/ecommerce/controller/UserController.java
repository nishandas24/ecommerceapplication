package com.example.ecommerce.controller;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.reponse.Response;
import com.example.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public Response userRegistration(@RequestPart UserDto userDto,
                                     @RequestPart MultipartFile userImage) {
        try {
            Response response = new Response();
            if(userImage!=null) {
                userDto.setProfileImage(userImage);
            }
            return userService.userRegistration(userDto);
        } catch (Exception e) {
            return new Response();
        }
    }
}
