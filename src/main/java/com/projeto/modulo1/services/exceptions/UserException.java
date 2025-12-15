package com.projeto.modulo1.services.exceptions;

public class UserException extends Exception {
    private final String message;

    private int status = 400;

    public UserException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
