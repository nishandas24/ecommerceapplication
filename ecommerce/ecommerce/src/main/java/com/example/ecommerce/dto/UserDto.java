package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private String email;
    private Integer roleId;
    private MultipartFile profileImage;
    private Boolean isActive;
    private String password;
}
