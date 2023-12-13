package com.example.ticket_bug_system.service;

import com.example.ticket_bug_system.dto.GenericResponse;
import com.example.ticket_bug_system.request.ModuleRequest;
import lombok.SneakyThrows;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 07-10-2023
 */
public interface ModuleService {
    GenericResponse save(ModuleRequest request);

    @SneakyThrows
    GenericResponse decryptToencrypted(String password);

    @SneakyThrows
    GenericResponse encryptToDecrypt(String passWord);
}
