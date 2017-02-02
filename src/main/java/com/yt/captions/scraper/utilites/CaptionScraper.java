package com.yt.captions.scraper.utilites;

import com.google.gson.Gson;
import com.yt.captions.scraper.model.Caption;
import com.yt.captions.scraper.model.Text;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LulzimG on 29/01/17.
 */
@Component
public class CaptionScraper {
    private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public List<Caption> getCaptions(String videoId) throws Exception {
        log.info("Started getting captions for video : " + videoId);
        String document = Content.get("http://www.youtube.com/get_video_info?&video_id=" + videoId);

        Map<String, String> list = getLinks(document);

        return addToConcreteType(list);
    }

    private List<Caption> addToConcreteType(Map<String, String> list) throws Exception {
        List<Caption> captions = new ArrayList<>();

        for (String s1 : list.keySet()) {
            String s2 = Content.get(list.get(s1));

            JSONObject soapDatainJsonObject = XML.toJSONObject(s2);
            Caption caption = new Gson().fromJson(soapDatainJsonObject.toString(), Caption.class);
            caption.setLang(s1);
            captions.add(caption);
        }

        for (Caption caption : captions) {
            if (caption.getTranscript() != null) {
                for (Text text : caption.getTranscript().getText()) {
                    String content = text.getContent();
                    String parsed = Jsoup.parse(content).text();
                    text.setContent(parsed);
                }
            }
        }

        return captions;
    }

    private Map<String, String> getLinks(String document) {
        String s = document.substring(document.indexOf("caption_tracks"), document.length() - 1);

        String[] split = s.split("u%3D");


        Map<String, String> list = new HashMap<>();
        for (String s1 : split) {
            try {
                s1 = URLDecoder.decode(s1, "UTF-8");
                s1 = URLDecoder.decode(s1, "UTF-8");
                s1 = URLDecoder.decode(s1, "UTF-8");
                s1 = URLDecoder.decode(s1, "UTF-8");

                String s2 = s1.substring(0, s1.indexOf("&lang=") + String.valueOf("&lang=").length() + 10);

                List<NameValuePair> params = URLEncodedUtils.parse(new URI(s2), "UTF-8");

                String lang = "";

                for (NameValuePair param : params) {
                    if (param.getName().equals("lang")) {
                        String langValue = param.getValue();
                        if (param.getValue().contains(",")) {
                            langValue = param.getValue().substring(0, param.getValue().indexOf(','));
                        }
                        s2 = s2.substring(0, s2.length() - (10 - langValue.length()));
                        lang = param.getValue();
                    }
                }
                if (s2.length() > 100) {
                    list.put(lang, s2);
                }
            } catch (Exception e) {
                log.error("Error : " + e.getMessage());
            }
        }

        return list;
    }

}
