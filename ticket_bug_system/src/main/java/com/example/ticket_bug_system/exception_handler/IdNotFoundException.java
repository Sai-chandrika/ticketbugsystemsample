package com.example.ticket_bug_system.exception_handler;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 07-10-2023
 */
public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String message) {
        super(message);
    }
}
