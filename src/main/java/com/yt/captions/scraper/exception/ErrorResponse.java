package com.yt.captions.scraper.exception;

/**
 * Created by LulzimG on 02/02/17.
 */
public class ErrorResponse {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
