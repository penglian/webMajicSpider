package com.pl.spider.config;

import com.pl.spider.bean.Sites;
import com.pl.spider.bean.XmlBean;
import com.pl.spider.dao.SiteDao;
import com.pl.spider.utils.SpiderCommon;
import com.pl.spider.utils.StringUtils;
import com.virjar.dungproxy.client.webmagic.DungProxyDownloader;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/22.
 */
@Component
public class GlobleSpiderService {
    @Autowired
    private SiteDao siteDao;
    public void start(XmlBean xmlBean){
        Sites site = siteDao.getBeanByUrl(xmlBean.getUrl());
        if(site==null){
            site  = new Sites();
            site.setSiteName(xmlBean.getSiteName());
            site.setSiteUrl(xmlBean.getUrl());
            //当前时间
            site.setSpiderDate(SpiderCommon.getCurrentTime());
            siteDao.addSite(site);
            site = siteDao.getBeanByUrl(xmlBean.getUrl());
        }
        Request request = new Request();
        Map<String,Object> extras = new HashedMap();
        extras.put("endPage",xmlBean.getEndPage());
        extras.put("siteName",xmlBean.getSiteName());
        extras.put("siteId",site.getSiteId());
        request.setExtras(extras);
        request.setUrl(xmlBean.getUrl());
        final PageProcessor newsProcessor = (PageProcessor)SpiderApplicationContex.applicationContext.getBean(xmlBean.getProcessor());
        final Pipeline newsPipeline = (Pipeline) SpiderApplicationContex.applicationContext.getBean(xmlBean.getPipeline());
        Spider.create(newsProcessor).addPipeline(newsPipeline).addRequest(request)
                .setDownloader(new DungProxyDownloader()).thread(Integer.parseInt(xmlBean.getThreadNum())).run();
    }
}
