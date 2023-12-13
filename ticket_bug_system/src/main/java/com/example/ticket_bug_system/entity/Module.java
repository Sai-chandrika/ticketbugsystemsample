package com.example.ticket_bug_system.entity;

import com.example.ticket_bug_system.dto.BaseEntity;
import com.example.ticket_bug_system.enums.ModuleStatus;
import com.example.ticket_bug_system.enums.Priority;
import jakarta.persistence.*;
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
 * @since 06-10-2023
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Module extends BaseEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private ModuleStatus status;
//    @OneToMany(fetch = FetchType.EAGER,targetEntity = Ticket.class,cascade = {CascadeType.MERGE ,CascadeType.PERSIST})
//    @JoinColumn(columnDefinition = "module_id",referencedColumnName = "id")
//    private List<Ticket> ticketsList = new ArrayList<>();
//    @OneToMany(fetch = FetchType.EAGER,targetEntity = UserDetail.class,cascade = {CascadeType.MERGE ,CascadeType.PERSIST})
//    private List<UserDetail> userDetails=new ArrayList<>();
}
