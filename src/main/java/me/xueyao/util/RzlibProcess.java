package me.xueyao.util;

import me.xueyao.config.NovelConfig;
import me.xueyao.entity.Novel;
import me.xueyao.print.ConsolePipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author Simon.Xue
 * @date 11/25/20 3:53 PM
 **/
@Component
public class RzlibProcess implements PageProcessor {

    public static final String SUFFIX = ".html";

    @Autowired
    private NovelConfig novelConfig;

    private Site site = Site.me().setSleepTime(300)
            .setDomain("www.rzlib.net")
            .setCharset("gbk")
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(SUFFIX).match()) {
            page.addTargetRequests(page.getHtml().xpath("///div[@class=\"ListChapter\"]").links()
                    .regex(novelConfig.getSuffixUrl()).all());

            String url = page.getUrl().toString();
            String[] split = url.split("/");
            String key = split[split.length - 1];
            key = key.substring(0, key.indexOf(".html"));

            page.putField("title", page.getHtml().xpath("//div[@id='chapter_title']/h1/text()"));
            page.putField("content", page.getHtml().xpath("//div[@id='chapter_content']/text()"));
            page.putField("key", key);

            Novel novel = new Novel();
            novel.setNo(Integer.valueOf(key));
            novel.setTitle(page.getHtml().xpath("//div[@id='chapter_title']/h1/text()").toString());
            novel.setContent(page.getHtml().xpath("//div[@id='chapter_content']/text()").toString());

            if (null == novel.getNo()) {
                page.setSkip(true);
            } else {
                page.putField("novel", novel);
            }

        } else {
            page.addTargetRequests(page.getHtml()
                    .xpath("///div[@class=\"ListChapter\"]").links()
                    .regex(novelConfig.getSuffixUrl()).all());
        }


    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new RzlibProcess())
                .addUrl("https://www.rzlib.net/b/2/2195/")
                .thread(1)
                .addPipeline(new ConsolePipeline())
                .run();
    }
}
