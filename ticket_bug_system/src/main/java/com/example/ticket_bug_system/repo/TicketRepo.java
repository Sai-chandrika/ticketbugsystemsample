package com.example.ticket_bug_system.repo;

import com.example.ticket_bug_system.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 07-10-2023
 */
@Repository
public interface TicketRepo extends JpaRepository<Ticket, String> {

    List<Ticket> findAllByUserModuleModuleId(String moduleId);
}
