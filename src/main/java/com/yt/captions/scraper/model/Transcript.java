package com.yt.captions.scraper.model;

/**
 * Created by LulzimG on 28/01/17.
 */
public class Transcript
{
    private Text[] text;

    public Text[] getText ()
    {
        return text;
    }

    public void setText (Text[] text)
    {
        this.text = text;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [text = "+text+"]";
    }
}