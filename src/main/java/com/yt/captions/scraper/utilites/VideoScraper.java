package com.yt.captions.scraper.utilites;

import com.yt.captions.scraper.model.Navigation;
import com.yt.captions.scraper.model.Page;
import com.yt.captions.scraper.model.Sort;
import com.yt.captions.scraper.model.VideoInfo;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by LulzimG on 30/01/17.
 */
@Component
public class VideoScraper {

    public Page getVideos(String keyword) throws Exception {
        String s = Content.get("https://www.youtube.com/results?" + keyword);
        Page page = new Page();

        getElements(s, page);

        return page;
    }

    private void getElements(String s, Page page) throws URISyntaxException {
        Elements ol = Jsoup.parse(s).select("div#results").select("ol").select("ol.item-section")
                .select("div.yt-lockup.yt-lockup-tile.yt-lockup-video.vve-check.clearfix").select("div.yt-lockup-dismissable")
                .select("span.video-time,img,a.yt-uix-tile-link,ul.yt-lockup-meta-info");

        List<VideoInfo> videoInfo = generateVideoInfos(ol);

        HashSet<Navigation> pages = generatePages(s);

        List<Sort> sorts = generateSorts(s);

        page.setVideos(videoInfo);
        page.setPages(pages);
        page.setSort(sorts);
    }

    private List<VideoInfo> generateVideoInfos(Elements videos) {
        List<VideoInfo> videoInfos = new ArrayList<>();
        VideoInfo videoInfo = null;
        for (Element vid : videos) {
            if (vid.tagName().equals("img")) {
                videoInfo = new VideoInfo();

                if (vid.attributes().get("src").contains("http")) {
                    videoInfo.setImage(vid.attributes().get("src"));
                } else {
                    videoInfo.setImage(vid.attributes().get("data-thumb"));
                }
            } else if (vid.tagName().equals("span")) {
                videoInfo.setTime(vid.text());
            } else if (vid.tagName().equals("a")) {
                String id = vid.attributes().get("href").replace("/watch?v=", "");

                videoInfo.setTitle(vid.text());
                videoInfo.setId(id);
            } else if (vid.tagName().equals("ul")) {
                Elements allElements = vid.getAllElements().select("li");

                videoInfo.setUploadTime(allElements.get(0).text());
                videoInfo.setViews(allElements.get(1).text());

                videoInfos.add(videoInfo);
            }
        }
        return videoInfos;
    }

    private HashSet<Navigation> generatePages(String s) throws URISyntaxException {
        Elements pages = Jsoup.parse(s).select("a.yt-uix-button.vve-check.yt-uix-sessionlink.yt-uix-button-default.yt-uix-button-size-default");

        HashSet<Navigation> navigations = new HashSet<>();

        for (Element page : pages) {
            Navigation navigation = new Navigation();

            Attributes attributes = page.attributes();
            String href = attributes.get("href");
            List<NameValuePair> params = URLEncodedUtils.parse(new URI(href), "UTF-8");

            String code = "";
            for (NameValuePair param : params) {
                if (param.getName().equals("sp")) {
                    code = param.getValue();
                }
            }

            String number = attributes.get("aria-label").replace("Go to page ", "");

            navigation.setCode(code);
            navigation.setNumber(number);

            navigations.add(navigation);
        }

        return navigations;
    }

    private List<Sort> generateSorts(String s) throws URISyntaxException {
        Elements elements = Jsoup.parse(s).select("div.filter-col");

        Elements li = null;
        for (Element element : elements) {
            String text = element.child(0).text();
            if (text.equals("Sort by")) {
                li = element.select("a.filter.yt-uix-sessionlink");
            }
        }

        List<Sort> sorts = new ArrayList<>();

        if (li != null) {
            for (Element element : li) {
                Sort sort = new Sort();

                String name = element.attributes().get("aria-label").replace("Sort by ", "");
                String href = element.attributes().get("href");
                List<NameValuePair> params = URLEncodedUtils.parse(new URI(href), "UTF-8");
                String code = "";
                for (NameValuePair param : params) {
                    if (param.getName().equals("sp")) {
                        code = param.getValue();
                    }
                }
                sort.setName(name);
                sort.setCode(code);
                sorts.add(sort);
            }
        }
        return sorts;
    }
}
