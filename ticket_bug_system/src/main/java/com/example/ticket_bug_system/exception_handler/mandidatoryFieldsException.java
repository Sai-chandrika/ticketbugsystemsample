package com.example.ticket_bug_system.exception_handler;

/**
 * @author chandrika.g
 * user
 * @ProjectName ticket_bug_system
 * @since 13-10-2023
 */
public class mandidatoryFieldsException extends RuntimeException{
    public mandidatoryFieldsException(String message) {
        super(message);
    }
}
