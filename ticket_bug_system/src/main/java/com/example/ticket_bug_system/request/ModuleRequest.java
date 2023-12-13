package com.example.ticket_bug_system.request;

import com.example.ticket_bug_system.dto.TicketDto;
import com.example.ticket_bug_system.enums.ModuleStatus;
import com.example.ticket_bug_system.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 07-10-2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ModuleRequest {
    private String id;
    private String name;
    private Priority priority;
    private ModuleStatus status;
    private List<String> ticketsList = new ArrayList<>();
}
