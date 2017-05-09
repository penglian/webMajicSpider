package com.pl.spider.processor;

import com.pl.spider.bean.Company;
import com.pl.spider.bean.Job;
import com.pl.spider.bean.Sites;
import com.pl.spider.dao.CompanyDao;
import com.pl.spider.dao.JobDao;
import com.pl.spider.dao.SiteDao;
import com.pl.spider.utils.StringUtils;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/21.
 */
@Component
public class BaseProcessor{

    public final Logger logger = LoggerFactory.getLogger(BaseProcessor.class.getName());
    @Autowired
    private JobDao jobDao;
    @Autowired
    private SiteDao siteDao;
    @Autowired
    private CompanyDao companyDao;

    protected Site site = Site.me().setSleepTime(0).setTimeOut(20 * 1000).setCharset("UTF-8").setRetryTimes(3)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
            .setCycleRetryTimes(3);

    /**
     * 判断该条目是否已添加
     */
     protected boolean isAddItem(String href){
         if(!StringUtils.isNullOrEmpty(href)){
             //从数据库中获取数据进行判断
             boolean isAddJob = jobDao.getJobByUrl(href);
             if(isAddJob){
                 logger.error("该条记录已存在!");
                 return true;
             }
         }
         return false;
     }

    protected Map<String, Object> getExtraMap(final Request request) {

        final Map<String, Object> extras = new HashedMap();
        if (request != null) {
            extras.put("siteName", request.getExtra("siteName"));
            extras.put("endPage", request.getExtra("endPage"));
            extras.put("siteId",request.getExtra("siteId"));
        }
        return extras;
    }


    protected void addUrl(final String url, final Page page, final Map<String, Object> extras) {
        if (!StringUtils.isNullOrEmpty(url)) {
            final Request request = new Request();
            request.setUrl(url);
            request.setExtras(extras);
            page.addTargetRequest(request);
            logger.info("页面url:" + url, page.getRequest().getExtras());
        }
    }

    protected void addRequestUrl(String href, Page page, Map<String, Object> extras){
        boolean isAdd  =isAddItem(href);
        if(isAdd){
            logger.error("该条记录已存在");
        }else{
            addUrl(href,page,extras);
        }
    }

    protected void putFiled(Page page, Job job){
        if(job!=null){
            page.putField("job",job);
        }
    }

    protected Sites getSite(int siteId){
        Sites site = siteDao.getBeanById(siteId);
        return site;
    }


    protected Company getCompany(Company company){
        companyDao.addCompany(company);
        Company newCompany = companyDao.getCompany(company);
        return newCompany;
    }

}
