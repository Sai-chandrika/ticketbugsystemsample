package com.example.ticket_bug_system.dto;

import com.example.ticket_bug_system.entity.Ticket;
import com.example.ticket_bug_system.enums.ModuleStatus;
import com.example.ticket_bug_system.enums.Priority;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDto {
    private String id;
    private String name;
    private Priority priority;
    private ModuleStatus status;
    private List<TicketDto> ticketsList = new ArrayList<>();
}
