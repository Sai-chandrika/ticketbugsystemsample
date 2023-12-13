package com.example.ticket_bug_system.service.impl;

import com.example.ticket_bug_system.entity.UserDetail;
import com.example.ticket_bug_system.enums.RoleType;
import com.example.ticket_bug_system.repo.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 27-10-2023
 */

@Service
@PropertySource("classpath:insert_default_user")
public class DefaultInsertData {
      @Autowired
      ResourceLoader resourceLoader;
      @Autowired
      JdbcTemplate jdbcTemplate;
      @Autowired
      BCryptPasswordEncoder bCryptPasswordEncoder;
      @Autowired
      UserRepo userRepo;

      @PostConstruct
      public void importDataFromFile() {
            Optional<UserDetail> optionalAppUser = userRepo.findAllByRole(RoleType.ADMIN).stream().findAny();
            if (optionalAppUser.isPresent()) {
                  userRepo.save(optionalAppUser.get());
            }
            if (userRepo.findAllByRole(RoleType.ADMIN).isEmpty()) {
                  try {
                        Resource resource = resourceLoader.getResource("classpath:insert_default_user");
                        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
                        String sql = new String(bytes, StandardCharsets.UTF_8);
                        String encodedPassword = sql.split("#super-admin-password=")[0];
                        jdbcTemplate.execute(sql.split("#super-admin-password=")[0]);
                        Optional<UserDetail> appUser = userRepo.findAll().stream().filter(f -> f.getRole().name().equals(RoleType.ADMIN.name())).findAny();
                        if (appUser.isPresent()) {
                              appUser.get().setPassword(bCryptPasswordEncoder.encode(encodedPassword));
                              userRepo.save(appUser.get());
                        }
                  } catch (Exception io) {
                        io.printStackTrace();
                  }
            }
      }
}
