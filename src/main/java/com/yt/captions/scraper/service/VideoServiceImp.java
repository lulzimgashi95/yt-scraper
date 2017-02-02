package com.yt.captions.scraper.service;

import com.yt.captions.scraper.model.Page;
import com.yt.captions.scraper.utilites.VideoScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LulzimG on 30/01/17.
 */
@Service("videoService")
public class VideoServiceImp implements VideoService {

    @Autowired
    private VideoScraper videoScraper;

    @Override
    public Page getVideos(String keyword) throws Exception {
        String url = "search_query=" + keyword;
        return videoScraper.getVideos(url);
    }

    @Override
    public Page getFilteredVideos(String keyword, String sp) throws Exception {
        String filter = "q=" + keyword + "&sp=" + sp;
        return videoScraper.getVideos(filter);
    }
}
