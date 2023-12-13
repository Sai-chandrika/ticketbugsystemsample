package com.example.ticket_bug_system.service.impl;

import com.example.ticket_bug_system.dto.GenericResponse;
import com.example.ticket_bug_system.dto.SignInResponce;
import com.example.ticket_bug_system.dto.UserDto;
import com.example.ticket_bug_system.dto.UserLogin;
import com.example.ticket_bug_system.entity.UserDetail;
import com.example.ticket_bug_system.enums.RoleType;
import com.example.ticket_bug_system.exception_handler.IdNotFoundException;
import com.example.ticket_bug_system.exception_handler.InvalidDataException;
import com.example.ticket_bug_system.repo.UserRepo;
import com.example.ticket_bug_system.request.UserRequest;
import com.example.ticket_bug_system.security.JwtTokenUtils;
import com.example.ticket_bug_system.security.RedisToken;
import com.example.ticket_bug_system.service.UserService;
import com.example.ticket_bug_system.validation.Validation;
import com.nimbusds.jose.JOSEException;
import com.opencsv.CSVReader;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 06-10-2023
 */
@Service
public class UserServicImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    BCryptPasswordEncoder bcryptpasswordencoder;
    @Autowired
    JwtTokenUtils jwtTokenUtils;
    @Autowired
    RedisToken redisToken;
    @Autowired
    StringRedisTemplate redisTemplate;


    @Override
    public GenericResponse saveUser(UserRequest userRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail user = (UserDetail) authentication.getPrincipal();
        UserDetail userDetail;
            if (userRequest.getId() != null) {
                return updateUser(userRequest);
            } else {
                if (userRequest.getEmail() == null || !Validation.isValidEmailPattern(userRequest.getEmail())) {
                    throw new InvalidDataException("Invalid email pattern");
                }
                if (userRequest.getPassword() == null || !Validation.isValidPassword(userRequest.getPassword())) {
                    throw new InvalidDataException("Invalid password pattern");
                }
                if (userRequest.getMobileNo() == null || !Validation.isValidMobileNumber(userRequest.getMobileNo())) {
                    throw new InvalidDataException("Invalid mobile pattern");
                }
                            userDetail = new UserDetail();
                            userDetail.setName(Validation.properTextCase(userRequest.getName()));
                            userDetail.setEmail(userRequest.getEmail());
                            userDetail.setPassword(bcryptpasswordencoder.encode(userRequest.getPassword()));
                            userDetail.setMobileNo(userRequest.getMobileNo());
                            userDetail.setRole(userRequest.getRole());
                            userDetail.setCreatedBy(user.getId());
                            userDetail.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
                            userRepo.save(userDetail);
                            return new GenericResponse(HttpStatus.OK.value(), " user save successfully :) ", entityToDto(userDetail));
            }
        }
//@Override
//    public GenericResponse logoutPage(HttpServletRequest request, HttpServletResponse response) {
//    final String authHeader = request.getHeader("Authorization");
//    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//        return new GenericResponse(HttpStatus.OK.value(), "Token Not existed");
//    }
//    UserDetail user = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        redisTemplate.delete(authHeader);
//        return new GenericResponse(HttpStatus.OK.value(), "Logout successfully");
//    }


    public GenericResponse updateUser(UserRequest userRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail user = (UserDetail) authentication.getPrincipal();
        UserDetail userDetail;
       Optional<UserDetail> userOptional = userRepo.findById(userRequest.getId());
       if(userOptional.isEmpty()){
           throw new IdNotFoundException("user not found given this id");
       }
            userDetail = new UserDetail();
            if(userRequest.getName()!=null) {
                userDetail.setName(Validation.properTextCase(userRequest.getName()));
            }
            if(userRequest.getEmail()!=null) {
                if(Boolean.TRUE.equals(Validation.isValidEmailPattern(userRequest.getEmail()))) {
                    userDetail.setEmail(userRequest.getEmail());
                }else throw new InvalidDataException("email pattern is not match");
            }
            if(userRequest.getPassword()!=null) {
                if(Boolean.TRUE.equals(Validation.isValidPassword(userRequest.getPassword()))) {
                    userDetail.setPassword(bcryptpasswordencoder.encode(userRequest.getPassword()));
                }else throw new InvalidDataException("password pattern is not match");
            }
            if(userRequest.getMobileNo()!=null){
                if(Boolean.TRUE.equals(Validation.isValidMobileNumber(userRequest.getMobileNo()))) {
                    userDetail.setMobileNo(userRequest.getMobileNo());
                }else throw new InvalidDataException("mobile pattern is not match");
            }
            if (userRequest.getRole() != null) {
                userDetail.setRole(userDetail.getRole());
            }
            userDetail.setUpdatedBy(user.getName() + " " + user.getId());
            userDetail.setUpdatedOn(LocalDateTime.now());
            userRepo.save(userDetail);
            return new GenericResponse(HttpStatus.OK.value(), " user updated successfully :) ", entityToDto(userDetail));
    }




    private UserDto entityToDto(UserDetail userDetail) {
        UserDto dto = new UserDto();
        dto.setName(userDetail.getName());
        dto.setEmail(userDetail.getEmail());
        dto.setMobileNo(userDetail.getMobileNo());
        dto.setRole(userDetail.getRole());
        return dto;
    }

    @Override
    public GenericResponse login(UserLogin request) throws JOSEException {
        UserDetail optional = userRepo.findByEmail(request.getEmail());
        if (optional==null) {
            throw new IdNotFoundException("given app_user not found");
        }
        System.out.println(bcryptpasswordencoder.encode(request.getPassword())+ "****************************************");
        if (bcryptpasswordencoder.matches(request.getPassword(), optional.getPassword())) {
            SignInResponce responce = new SignInResponce(optional);
            responce.setToken(jwtTokenUtils.getToken(optional));
            redisToken.addTokenToBlacklist(optional.getEmail(),responce.getToken());
            return new GenericResponse(HttpStatus.OK.value(), "login successfully", responce);
        } else throw new InvalidDataException("password wrong..........");
    }

    @Override
    public GenericResponse signUp(UserDetail request) {
        return null;
    }
}
