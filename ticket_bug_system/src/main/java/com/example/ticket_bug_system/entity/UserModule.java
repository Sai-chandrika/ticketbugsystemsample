package com.example.ticket_bug_system.entity;

import com.example.ticket_bug_system.dto.BaseEntity;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
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
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"module_id", "user_detail_id"})})
public class UserModule extends BaseEntity {
    @ManyToOne(targetEntity = Module.class, cascade = {CascadeType.MERGE})
    private Module module=new Module();
    @ManyToOne(targetEntity = UserDetail.class, cascade = {CascadeType.MERGE})
    private UserDetail userDetail=new UserDetail();
}
