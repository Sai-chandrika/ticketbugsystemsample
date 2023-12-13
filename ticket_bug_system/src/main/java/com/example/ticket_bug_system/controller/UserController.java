package com.example.ticket_bug_system.controller;

import com.example.ticket_bug_system.dto.GenericResponse;
import com.example.ticket_bug_system.dto.UserLogin;
import com.example.ticket_bug_system.entity.UserDetail;
import com.example.ticket_bug_system.request.UserRequest;
import com.example.ticket_bug_system.service.UserService;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 07-10-2023
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

//    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public GenericResponse save(@RequestBody @Valid UserRequest userRequest){
        return userService.saveUser(userRequest);
    }

    public GenericResponse signUp(@RequestBody UserDetail request){
        return userService.signUp(request);
    }
    @PostMapping("/login")
    public GenericResponse login(@RequestBody UserLogin request) throws JOSEException {
        return userService.login(request);
    }
//    @PostMapping("/logout")
//    public GenericResponse logout(HttpServletRequest request, HttpServletResponse response) {
//        return userService.logoutPage(request, response);
//    }
}
