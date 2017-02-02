package com.yt.captions.scraper.controller;

import com.yt.captions.scraper.exception.NothingFoundException;
import com.yt.captions.scraper.model.Caption;
import com.yt.captions.scraper.service.CaptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by LulzimG on 29/01/17.
 */
@RestController
@RequestMapping("/captions")
public class CaptionController {

    @Autowired
    private CaptionService captionService;

    @RequestMapping(value = "/{videoId}", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<List<Caption>> getCaptions(@PathVariable String videoId) throws Exception {
        List<Caption> videoCaptions = captionService.getVideoCaptions(videoId);

        if (videoCaptions != null) {
            return new ResponseEntity<>(videoCaptions, HttpStatus.OK);
        } else {
            throw new NothingFoundException("Captions not found");
        }
    }
}
