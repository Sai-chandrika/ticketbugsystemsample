package com.example.ticket_bug_system.dto;

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
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserLogin {
    private String email;
    private String password;
}
