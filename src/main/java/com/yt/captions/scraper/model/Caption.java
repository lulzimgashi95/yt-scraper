package com.yt.captions.scraper.model;

/**
 * Created by LulzimG on 28/01/17.
 */
public class Caption {
    private Transcript transcript;

    private String lang;

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
