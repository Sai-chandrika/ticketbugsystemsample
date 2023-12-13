package com.example.ticket_bug_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 12-10-2023
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserModuleDto {
    private String id;
    private UserDto userDto=new UserDto();
    private  ModuleDto moduleDto=new ModuleDto();
}
