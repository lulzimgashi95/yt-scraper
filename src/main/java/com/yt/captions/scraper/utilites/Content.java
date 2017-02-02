package com.yt.captions.scraper.utilites;


import com.yt.captions.scraper.exception.NothingFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by LulzimG on 30/01/17.
 */
public class Content {
    private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static String get(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");


        int responseCode = con.getResponseCode();
        log.info("'GET' to URL : " + url);
        log.info("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String s = response.toString();
        boolean fail = s.contains("fail");
        if (fail) {
            throw new NothingFoundException("Nothing found");
        }
        return s;
    }
}
