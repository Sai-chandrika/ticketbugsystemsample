package com.example.ticket_bug_system.request;

import com.example.ticket_bug_system.dto.UserModuleDto;
import com.example.ticket_bug_system.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 13-10-2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TicketRequest {
    private String id;
    private String title;
    private String description;
    private TicketStatus status;
    private String userModuleDto;
}
