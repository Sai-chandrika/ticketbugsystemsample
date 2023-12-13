package com.example.ticket_bug_system.repo;

import com.example.ticket_bug_system.entity.UserDetail;
import com.example.ticket_bug_system.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 07-10-2023
 */
@Repository
public interface UserRepo extends JpaRepository<UserDetail, String> {
      UserDetail findByEmail(String userName);

      UserDetail findByName(String userName);

      UserDetail findByMobileNo(String userName);

    Optional<UserDetail> findAllByRole(RoleType roleType);
}
