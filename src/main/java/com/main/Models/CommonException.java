package com.main.Models;

public class CommonException extends Exception {
    private  Message message;

    public CommonException(Message message) {
        super(message.getMessage());
        this.message=message;
    }

    public Message getData() {
        return message;
    }
}