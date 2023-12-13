package com.example.ticket_bug_system.service;

import com.example.ticket_bug_system.dto.GenericResponse;
import com.example.ticket_bug_system.dto.TicketDto;
import com.example.ticket_bug_system.request.TicketRequest;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 06-10-2023
 */
public interface TicketService {
    GenericResponse saveTicket(TicketRequest dto);

    GenericResponse getAllTicket();
}
