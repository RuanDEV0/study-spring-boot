package io.github.ruan.springjpa.exception;

public class RegisterDuplicateException extends RuntimeException {
    public RegisterDuplicateException(String message) {
        super(message);
    }
}
