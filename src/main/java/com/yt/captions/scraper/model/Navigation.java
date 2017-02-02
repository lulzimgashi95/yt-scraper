package com.yt.captions.scraper.model;

/**
 * Created by LulzimG on 31/01/17.
 */
public class Navigation {
    private String number;
    private String code;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        return this.getNumber().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.getNumber().equals(((Navigation) obj).getNumber());
    }
}
