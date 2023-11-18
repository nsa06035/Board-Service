package com.example.jpa_bulletin_board.exception;

public class DuplicateNickNameException extends RuntimeException {
    public DuplicateNickNameException(String message) {
        super(message);
    }
}
