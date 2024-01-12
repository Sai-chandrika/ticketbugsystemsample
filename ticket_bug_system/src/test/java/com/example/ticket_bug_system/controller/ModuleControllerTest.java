package com.example.ticket_bug_system.controller;

import com.example.ticket_bug_system.dto.GenericResponse;
import com.example.ticket_bug_system.entity.Ticket;
import com.example.ticket_bug_system.enums.Priority;
import com.example.ticket_bug_system.request.ModuleRequest;
import com.example.ticket_bug_system.service.ModuleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system (1) (1)
 * @since 11-01-2024
 */
@WebMvcTest(ModuleController.class)
class ModuleControllerTest {
    @MockBean
    ModuleService moduleService;
    ModuleRequest moduleRequest=new ModuleRequest();
    Ticket ticket=new Ticket();
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        moduleRequest.setId("1");
        moduleRequest.setName("module-1");
        moduleRequest.setPriority(Priority.HIGH);
    }

    @Test
    void save() throws Exception {
        GenericResponse genericResponse=new GenericResponse("save successfully");
        when(moduleService.save(moduleRequest)).thenReturn(genericResponse);
        mockMvc.perform(post("/module/save").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(moduleRequest)).content(status().isOk().toString()));
    }

    @Test
    void encryptToDecrypt() {
    }

    @Test
    void decryptToencrypted() {
    }
}