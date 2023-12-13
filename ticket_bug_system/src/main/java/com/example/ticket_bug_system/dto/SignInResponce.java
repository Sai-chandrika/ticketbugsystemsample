package com.example.ticket_bug_system.dto;

import com.example.ticket_bug_system.entity.UserDetail;
import com.example.ticket_bug_system.enums.RoleType;
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
public class SignInResponce {
    private String id;
    private String name;
    private String email;
    private String password;
    private String mobileNo;
    private RoleType role;
    private String token;

    public SignInResponce(UserDetail user) {
        this.id=user.getId();
        this.name = user.getName();
        this.email=user.getEmail();
        this.mobileNo=user.getMobileNo();
        this.role=user.getRole();
    }
}
