package com.yt.captions.scraper.service;

import com.yt.captions.scraper.model.Caption;

import java.util.List;

/**
 * Created by LulzimG on 29/01/17.
 */
public interface CaptionService {
    List<Caption> getVideoCaptions(String videoId) throws Exception;
}
