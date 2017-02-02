package com.yt.captions.scraper.model;

/**
 * Created by LulzimG on 28/01/17.
 */
public class Text
{
    private String content;

    private String dur;

    private String start;

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getDur ()
    {
        return dur;
    }

    public void setDur (String dur)
    {
        this.dur = dur;
    }

    public String getStart ()
    {
        return start;
    }

    public void setStart (String start)
    {
        this.start = start;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [content = "+content+", dur = "+dur+", start = "+start+"]";
    }
}