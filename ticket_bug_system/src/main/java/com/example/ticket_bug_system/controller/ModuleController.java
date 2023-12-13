package com.example.ticket_bug_system.controller;

import com.example.ticket_bug_system.dto.GenericResponse;
import com.example.ticket_bug_system.request.ModuleRequest;
import com.example.ticket_bug_system.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 07-10-2023
 */
@RestController
@RequestMapping("/module")
public class ModuleController {

@Autowired
    ModuleService moduleService;
//@PreAuthorize("hasAuthority('ADMIN')")
@PostMapping("/save")
public GenericResponse save(@RequestBody ModuleRequest request){
    return moduleService.save(request);
}
@GetMapping("/encrypt/to/decrypt/{decrypt}")
    public GenericResponse encryptToDecrypt(String decrypt){
    return moduleService.encryptToDecrypt(decrypt);
}

    @GetMapping("/decrypt/to/encrypt/{encrypt}")
    public GenericResponse decryptToencrypted(String encrypt){
        return moduleService.decryptToencrypted(encrypt);
    }
}
