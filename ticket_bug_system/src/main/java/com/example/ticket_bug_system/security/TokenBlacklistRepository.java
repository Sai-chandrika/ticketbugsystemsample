package com.example.ticket_bug_system.security;

/**
 * @author chandrika.g
 * user
 * @ProjectName security_practice_code
 * @since 30-10-2023
 */
public interface TokenBlacklistRepository {
      void addTokenToBlacklist(String user,String token);
      boolean isTokenBlacklisted(String user,String token);
      void removeTokenFromBlacklist(String token);
}
