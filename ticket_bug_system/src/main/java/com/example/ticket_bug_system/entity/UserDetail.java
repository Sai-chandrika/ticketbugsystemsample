package com.example.ticket_bug_system.entity;

import com.example.ticket_bug_system.dto.BaseEntity;
import com.example.ticket_bug_system.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 06-10-2023
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDetail extends BaseEntity {
    private String name;
    private String email;
    private String password;
    private String mobileNo;
    @Enumerated(EnumType.STRING)
    private RoleType role;
//    @OneToMany(fetch = FetchType.EAGER,targetEntity = Module.class,cascade = {CascadeType.MERGE ,CascadeType.PERSIST})
//    private List<Module> moduleList = new ArrayList<>();
//    @OneToMany(fetch = FetchType.EAGER,targetEntity = Ticket.class,cascade = {CascadeType.MERGE ,CascadeType.PERSIST})
//    private List<Ticket> ticketList = new ArrayList<>();
}
