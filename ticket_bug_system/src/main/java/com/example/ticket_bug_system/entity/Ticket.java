package com.example.ticket_bug_system.entity;

import com.example.ticket_bug_system.dto.BaseEntity;
import com.example.ticket_bug_system.enums.TicketStatus;
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
public class Ticket extends BaseEntity {
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private TicketStatus status;
    @ManyToOne(targetEntity = UserModule.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private UserModule userModule=new UserModule();
//    @OneToOne(targetEntity = UserDetail.class, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
//    private UserDetail userDetail=new UserDetail();
//    @OneToOne(targetEntity = Module.class, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
//    private Module module=new Module();
    }
