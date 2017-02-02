package com.yt.captions.scraper.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by LulzimG on 29/01/17.
 */
public class Page {
    private List<VideoInfo> videos = new ArrayList<>();
    private HashSet<Navigation> pages = new HashSet<>();
    private List<Sort> sort = new ArrayList<Sort>();

    public List<VideoInfo> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoInfo> videoInfo) {
        this.videos = videoInfo;
    }

    public List<Sort> getSorts() {
        return sort;
    }

    public void setSort(List<Sort> sort) {
        this.sort = sort;
    }

    public HashSet<Navigation> getPages() {
        return pages;
    }

    public void setPages(HashSet<Navigation> pages) {
        this.pages = pages;
    }
}
