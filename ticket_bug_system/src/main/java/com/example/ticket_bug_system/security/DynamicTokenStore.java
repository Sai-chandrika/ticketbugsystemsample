package com.example.ticket_bug_system.security;

import java.time.LocalDateTime;

/**
 * @author chandrika
 * user
 * @ProjectName otp-sending-users
 * @since 29-09-2023
 */
public class DynamicTokenStore {
    private DynamicTokenStore() {
    }

    public static LocalDateTime tokenCreationTime;
}
