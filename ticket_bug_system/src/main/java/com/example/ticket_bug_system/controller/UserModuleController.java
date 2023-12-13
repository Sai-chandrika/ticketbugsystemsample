package com.example.ticket_bug_system.controller;

import com.example.ticket_bug_system.dto.GenericResponse;
import com.example.ticket_bug_system.request.UserModuleRequest;
import com.example.ticket_bug_system.service.ModuleUserService;
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
 * @since 13-10-2023
 */
@RestController
@RequestMapping("/assign/module/to/user")
public class UserModuleController {
    @Autowired
    ModuleUserService moduleUserService;
//    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")

    public GenericResponse assignModuleToUser(@RequestBody UserModuleRequest moduleRequest){
        return moduleUserService.ssaveModuleToUser(moduleRequest);

    }
}
