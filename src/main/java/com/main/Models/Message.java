package com.main.Models;

public class Message {

    private  String mgsCode;
    private  String message;
    private  Integer status;

    public Message(String mgsCode, String message) {
        this.mgsCode = mgsCode;
        this.message = message;
    }

    public Message() {
    }

    public Message(String mgsCode, String message, Integer errorStatus) {
        this.mgsCode = mgsCode;
        this.message = message;
        this.status = errorStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer errorStatus) {
        this.status = errorStatus;
    }

    public String getMgsCode() {
        return mgsCode;
    }


    public void setMgsCode(String mgsCode) {
        this.mgsCode = mgsCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
