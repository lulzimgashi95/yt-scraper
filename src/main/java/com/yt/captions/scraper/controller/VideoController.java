package com.yt.captions.scraper.controller;

import com.yt.captions.scraper.exception.NothingFoundException;
import com.yt.captions.scraper.model.Page;
import com.yt.captions.scraper.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LulzimG on 30/01/17.
 */
@RestController
@RequestMapping("videos")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/results", params = {"q"}, method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<Page> search(@RequestParam("q") String keyword) throws Exception {

        Page videos = videoService.getVideos(keyword);

        if (videos != null) {
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } else {
            throw new NothingFoundException("Videos not found");
        }
    }

    @RequestMapping(value = "/results", params = {"q", "sp"}, method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<Page> filterSearch(@RequestParam("q") String keyword,
                                             @RequestParam("sp") String sp) throws Exception {

        Page videos = videoService.getFilteredVideos(keyword, sp);

        if (videos != null) {
            return new ResponseEntity<>(videos, HttpStatus.OK);
        } else {
            throw new NothingFoundException("Videos not found");
        }
    }


}
