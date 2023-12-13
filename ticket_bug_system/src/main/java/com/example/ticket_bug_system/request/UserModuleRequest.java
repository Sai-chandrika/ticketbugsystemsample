package com.example.ticket_bug_system.request;
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
public class UserModuleRequest {
    private String id;
    private String userDto;
    private String moduleDto;
}
