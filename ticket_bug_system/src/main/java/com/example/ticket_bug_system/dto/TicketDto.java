package com.example.ticket_bug_system.dto;

import com.example.ticket_bug_system.enums.TicketStatus;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class TicketDto {
    private String id;
    private String title;
    private String description;
    private TicketStatus status;
    private UserModuleDto userModuleDto;
}
