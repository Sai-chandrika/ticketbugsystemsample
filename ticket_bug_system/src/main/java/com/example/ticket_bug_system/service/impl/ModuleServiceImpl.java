package com.example.ticket_bug_system.service.impl;

import com.example.ticket_bug_system.dto.GenericResponse;
import com.example.ticket_bug_system.dto.ModuleDto;
import com.example.ticket_bug_system.entity.Module;
import com.example.ticket_bug_system.entity.Ticket;
import com.example.ticket_bug_system.entity.UserDetail;
import com.example.ticket_bug_system.exception_handler.IdNotFoundException;
import com.example.ticket_bug_system.repo.ModuleRepo;
import com.example.ticket_bug_system.repo.TicketRepo;
import com.example.ticket_bug_system.request.ModuleRequest;
import com.example.ticket_bug_system.service.ModuleService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 07-10-2023
 */
@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    TicketRepo ticketRepo;
    @Autowired
    ModuleRepo moduleRepo;

    @Override
    public GenericResponse save(ModuleRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail user = (UserDetail) authentication.getPrincipal();
        Module module;
        if (request.getId() != null) {
            return update(request);
        } else {
            module = new Module();
            module.setName(request.getName());
            module.setPriority(request.getPriority());
            module.setStatus(request.getStatus());
            if (!request.getTicketsList().isEmpty()) {
                List<Ticket> ticketList = new ArrayList<>();
                for (String id : request.getTicketsList()) {
                    Optional<Ticket> ticket = ticketRepo.findById(id);
                    if (ticket.isEmpty()) throw new IdNotFoundException("ticket id is not found");
                    ticketList.add(ticket.get());
                }
//                module.setTicketsList(ticketList);
            }
            module.setCreatedBy(user.getId());
            module.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
            moduleRepo.save(module);
            return new GenericResponse(HttpStatus.OK.value(), "save successfully", entityToDto(module));
        }
    }

    @Override
    @SneakyThrows
    public GenericResponse decryptToencrypted(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return new GenericResponse(hashedPassword);
    }

    private ModuleDto entityToDto(Module module) {
        ModuleDto moduleDto = new ModuleDto();
        moduleDto.setId(module.getId());
        moduleDto.setName(module.getName());
        moduleDto.setPriority(module.getPriority());
        moduleDto.setStatus(module.getStatus());
        return moduleDto;
    }


    private GenericResponse update(ModuleRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetail user = (UserDetail) authentication.getPrincipal();
        Module module;
        Optional<Module> moduleOptional = moduleRepo.findById(request.getId());
        if (moduleOptional.isEmpty()) throw new IdNotFoundException("user id is not found");
        module = moduleOptional.get();
        if (request.getName() != null) {
            module.setName(request.getName());
        }
        if (request.getPriority() != null) {
            module.setPriority(request.getPriority());
        }
        if (request.getStatus() != null) {
            module.setStatus(request.getStatus());
        }
        if (!request.getTicketsList().isEmpty()) {
            List<Ticket> ticketList = new ArrayList<>();
            for (String id : request.getTicketsList()) {
                Optional<Ticket> ticket = ticketRepo.findById(id);
                if (ticket.isEmpty()) throw new IdNotFoundException("ticket id is not found");
                ticketList.add(ticket.get());
            }
//            module.setTicketsList(ticketList);
        }
        module.setCreatedBy(user.getId());
        module.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
        moduleRepo.save(module);
        return new GenericResponse(HttpStatus.OK.value(), "update successfully", entityToDto(module));
    }




    @SneakyThrows
    @Override
    public GenericResponse encryptToDecrypt(String passWord) {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        Cipher encryptCipher = Cipher.getInstance("AES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        Cipher decryptCipher = Cipher.getInstance("AES");
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = encryptCipher.doFinal(passWord.getBytes("UTF-8"));
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted Text: " + encryptedText);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = decryptCipher.doFinal(decodedBytes);
        String decryptedText = new String(decryptedBytes, "UTF-8");
        return new GenericResponse(HttpStatus.OK.value(), decryptedText);
    }

    }

