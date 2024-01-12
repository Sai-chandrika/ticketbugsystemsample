package com.example.ticket_bug_system.controller;

import com.example.ticket_bug_system.dto.GenericResponse;
import com.example.ticket_bug_system.dto.UserLogin;
import com.example.ticket_bug_system.entity.UserDetail;
import com.example.ticket_bug_system.request.UserRequest;
import com.example.ticket_bug_system.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system (1) (1)
 * @since 10-01-2024
 */
@WebMvcTest(UserController.class)
//@RunWith(MockitoJUnitRunner.class)
class UserControllerTest {

@MockBean
UserService userService;
@Autowired
    UserController userController;
    MockMvc mockMvc;
    UserRequest userRequest = new UserRequest();
    UserDetail userDetail = new UserDetail();
    UserLogin login = new UserLogin();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }


    @Test
    void save() throws Exception {
        GenericResponse genericResponse = new GenericResponse(HttpStatus.OK.value(),"save successfully");
        userService.saveUser(userRequest);
        when(userService.saveUser(any(UserRequest.class))).thenReturn(genericResponse);
        mockMvc.perform(post("/user/save").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(genericResponse))).andExpect(status().isOk());
    }

    @Test
    void signUp() throws Exception {
        GenericResponse genericResponse = new GenericResponse("sign-up successfully");
        when(userService.signUp(userDetail)).thenReturn(genericResponse);
        mockMvc.perform(post("/user/signUp").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetail)).content(status().isOk().toString()));
    }

    @Test
    void login() throws Exception {
        GenericResponse genericResponse = new GenericResponse("sign-up successfully");
        when(userService.login(login)).thenReturn(genericResponse);
        mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetail)).content(status().isOk().toString()));
    }
}