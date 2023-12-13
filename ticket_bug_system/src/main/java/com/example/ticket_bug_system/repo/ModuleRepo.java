package com.example.ticket_bug_system.repo;


import com.example.ticket_bug_system.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 07-10-2023
 */
@Repository
public interface ModuleRepo extends JpaRepository<Module, String> {
}
