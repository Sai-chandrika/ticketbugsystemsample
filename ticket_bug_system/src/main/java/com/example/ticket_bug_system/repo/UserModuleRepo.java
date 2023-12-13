package com.example.ticket_bug_system.repo;

import com.example.ticket_bug_system.entity.UserModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 13-10-2023
 */
@Repository
public interface UserModuleRepo extends JpaRepository<UserModule, String> {


    List<UserModule> findAllByUserDetailId(String userDto);
}
