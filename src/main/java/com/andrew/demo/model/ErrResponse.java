package com.andrew.demo.model;

public class ErrResponse{

    final transient int errCode;
    final transient String message;

    public ErrResponse(int errCode, String message) {
        this.errCode = errCode;
        this.message = message;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getMessage() {
        return message;
    }

}
