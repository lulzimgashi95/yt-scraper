package com.yt.captions.scraper.service;

import com.yt.captions.scraper.model.Caption;
import com.yt.captions.scraper.utilites.CaptionScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LulzimG on 29/01/17.
 */
@Service("captionService")
public class CaptionServiceImp implements CaptionService {

    @Autowired
    private CaptionScraper captionScraper;

    public List<Caption> getVideoCaptions(String videoId) throws Exception {
        return captionScraper.getCaptions(videoId);
    }

}
