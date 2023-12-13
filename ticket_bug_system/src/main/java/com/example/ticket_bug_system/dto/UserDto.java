package com.example.ticket_bug_system.dto;
import com.example.ticket_bug_system.entity.UserDetail;
import com.example.ticket_bug_system.enums.RoleType;
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
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private String mobileNo;
    private RoleType role;
    private UserDetail createdBy;
//    private List<ModuleDto> moduleList = new ArrayList<>();
}
