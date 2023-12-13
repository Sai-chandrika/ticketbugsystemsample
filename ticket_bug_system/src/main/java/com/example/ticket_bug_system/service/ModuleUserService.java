package com.example.ticket_bug_system.service;

import com.example.ticket_bug_system.dto.GenericResponse;
import com.example.ticket_bug_system.request.UserModuleRequest;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 13-10-2023
 */
public interface ModuleUserService {
    GenericResponse ssaveModuleToUser(UserModuleRequest userModuleREquest);
}
