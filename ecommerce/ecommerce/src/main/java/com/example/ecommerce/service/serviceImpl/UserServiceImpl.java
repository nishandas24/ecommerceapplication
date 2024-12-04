package com.example.ecommerce.service.serviceImpl;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.model.UserMaster;
import com.example.ecommerce.reponse.Response;
import com.example.ecommerce.repository.RoleJpaRepository;
import com.example.ecommerce.repository.UserJpaRepository;
import com.example.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${fileUploadPath}")
    private String fileUploadPath;

    @Value("${spring.mail.username}")
    private String email;

    LocalDate localDate=LocalDate.now();
    String date2=localDate.toString();
    File directory = new File(fileUploadPath + date2);
    boolean isCreated = directory.mkdirs();
    private String file_path = directory.getPath();

    private final UserJpaRepository userJpaRepository;
    private final RoleJpaRepository roleJpaRepository;
    private final JavaMailSender javaMailSender;

    @Override
    public Response userRegistration(UserDto request) {
        Response response=new Response();
        try {
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            UserMaster userMaster=new UserMaster();
            String extension= FilenameUtils.getExtension(request.getProfileImage().getOriginalFilename());

            if (Boolean.TRUE.equals(userJpaRepository.existsByEmail(request.getEmail()))){
                response.setStatus(0);
                response.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                response.setMessage("User name is not unique");

                return response;
            }

            if (Boolean.TRUE.equals(userJpaRepository.existsByPhoneNumber(request.getPhoneNumber()))) {
                response.setStatus(0);
                response.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                response.setMessage("phone number is not unique");

                return response;
            }

            if (request.getPhoneNumber().matches("^(\\+91|91)?[6-9]\\d{9}$")){
                response.setStatus(0);
                response.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                response.setMessage("phone number is not valid");

                return response;
            }

            String filename = request.getProfileImage().getOriginalFilename();
            Path path = Paths.get(file_path + filename);
            Files.copy(request.getProfileImage().getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);

            userMaster.setFirstName(request.getFirstName());
            userMaster.setEmail(request.getEmail());
            userMaster.setPhoneNumber(request.getPhoneNumber());
            userMaster.setRoleId(roleJpaRepository.findById(Integer.valueOf(request.getRoleId())).get());
            //userMaster.setPassword(passwordEncoder.encode(request.getPassword()));
            userMaster.setProfileImage(String.valueOf(path));
            userMaster.setIsActive(true);

            simpleMailMessage.setFrom(email);
            simpleMailMessage.setSubject("confirmation of registration");
            simpleMailMessage.setText("<h1>Registration Successful</h1>");
            simpleMailMessage.setTo(request.getEmail());
            javaMailSender.send(simpleMailMessage);

            userJpaRepository.save(userMaster);
            response.setStatus(0);
            response.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
            response.setMessage("phone number is not valid");

        }
        catch (Exception e)
        {
            response.setStatus(0);
            response.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
            response.setMessage(e.getMessage());
        }

        return response;
    }
}
