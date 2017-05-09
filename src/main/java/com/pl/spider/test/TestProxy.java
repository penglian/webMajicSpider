package com.pl.spider.test;

import com.pl.spider.processor.BaseProcessor;
import com.virjar.dungproxy.client.webmagic.DungProxyDownloader;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by Administrator on 2017/4/25.
 */
public class TestProxy extends BaseProcessor implements PageProcessor{

    public void process(Page page) {

    }

    public Site getSite() {
        return site;
    }

    public static void main(String []arr){
        Spider.create(new TestProxy()).addUrl("")
                .setDownloader(new DungProxyDownloader()).thread(5).run();
    }
}
