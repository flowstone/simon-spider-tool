package me.xueyao.util;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author Simon.Xue
 * @date 11/25/20 3:25 PM
 **/
public class SinaBlogArticleProcess implements PageProcessor {

    public static final String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_1487828712_0_\\d+\\.html";

    public static final String URL_POST = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";

    private Site site = Site.me().setSleepTime(3000)
            .setDomain("blog.sina.com.cn");
    @Override
    public void process(Page page) {

        if (page.getUrl().regex(URL_LIST).match()) {
            page.addTargetRequests(page.getHtml().xpath("///div[@class=\"articleList\"]").links().regex(URL_POST).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
        } else {
            page.putField("title", page.getHtml().xpath("//div[@class='articalTitle']/h2"));
            page.putField("content", page.getHtml().xpath("//div[@id='articlebody']//div[@class='articalContent']"));
            page.putField("date", page.getHtml()
                    .xpath("//div[@id='articlebody']//span[@class='time SG_txtc']")
                    .regex("\\((.*)\\)"));
        }


    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new SinaBlogArticleProcess())
                //.addPipeline(new ConsolePipeline())
                .addUrl("http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html")
                .run();
    }
}
