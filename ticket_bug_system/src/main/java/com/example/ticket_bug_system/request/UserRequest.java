package com.example.ticket_bug_system.request;

import com.example.ticket_bug_system.enums.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UserRequest {
    private String id;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotBlank(message = "password is mandatory")
    private String password;
    @NotBlank(message = "mobileNo is mandatory")
    private String mobileNo;
    @NotNull(message = "role is mandatory")
    private RoleType role;
//      private List<String> moduleList = new ArrayList<>();
}
