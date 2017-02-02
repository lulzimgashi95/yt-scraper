package com.yt.captions.scraper.service;

import com.yt.captions.scraper.model.Page;

/**
 * Created by LulzimG on 30/01/17.
 */
public interface VideoService {
    Page getVideos(String keyword) throws Exception;

    Page getFilteredVideos(String keyword,String sp) throws Exception;
}

