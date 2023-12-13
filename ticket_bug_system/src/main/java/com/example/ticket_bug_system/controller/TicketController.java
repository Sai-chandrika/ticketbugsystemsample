package com.example.ticket_bug_system.controller;

import com.example.ticket_bug_system.dto.GenericResponse;
import com.example.ticket_bug_system.request.TicketRequest;
import com.example.ticket_bug_system.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 07-10-2023
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public GenericResponse save(TicketRequest request){
        return ticketService.saveTicket(request);
    }

    @GetMapping("get/all/modules/users/tickets")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public GenericResponse getAll(){
        return ticketService.getAllTicket();
    }

}
