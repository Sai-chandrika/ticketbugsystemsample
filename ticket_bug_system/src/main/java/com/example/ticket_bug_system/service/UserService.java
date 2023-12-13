package com.example.ticket_bug_system.service;

import com.example.ticket_bug_system.dto.GenericResponse;
import com.example.ticket_bug_system.dto.UserLogin;
import com.example.ticket_bug_system.entity.UserDetail;
import com.example.ticket_bug_system.request.UserRequest;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 06-10-2023
 */
public interface UserService {

    GenericResponse saveUser(UserRequest userRequest);

    GenericResponse login(UserLogin request) throws JOSEException;

    GenericResponse signUp(UserDetail request);

//    GenericResponse logoutPage(HttpServletRequest request, HttpServletResponse response);
}
