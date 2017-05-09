package com.pl.spider.processor;

import com.pl.spider.bean.Company;
import com.pl.spider.bean.Job;
import com.pl.spider.bean.Sites;
import com.pl.spider.utils.SpiderCommon;
import com.pl.spider.utils.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/21.
 */
@Component
public class JobProcessor extends BaseProcessor implements PageProcessor{
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(JobProcessor.class.getName());
    public void process(Page page) {
        String pageUrl  = page.getUrl().toString();
        if(pageUrl.contains("https://www.zhipin.com/c101010100/?query=")||pageUrl.contains("&page=")
                ||pageUrl.contains("?page=")){
            listProcessor(page);
        }else{
            deatilProcessor(page);
        }
    }

    private void deatilProcessor(Page page) {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Document document = page.getHtml().getDocument();
        Element articleMain = document.select("div#main").first();
        if(articleMain==null){
            logger.error("正文容器解析失败");
            return ;
        }
        Map<String,Object> extras = getExtraMap(page.getRequest());
        Company company = getCompany(document);

        int siteId = Integer.parseInt(extras.get("siteId")+"");
        Sites sites = getSite(siteId);

        Job job = getJob(document,company,sites,page);
        putFiled(page,job);

    }

    /**
     * 获取job实体
     * @param document
     * @param company
     * @param sites
     * @return
     */
    private Job getJob(Document document, Company company, Sites sites,Page page) {
        Job job = new Job();
        Element jobNameEl = document.select("div.job-primary>div.info-primary>div.name").first();
        if(jobNameEl!=null){
            TextNode textNode = jobNameEl.textNodes().get(0);
           String jobName = textNode.text().trim();
           if(!StringUtils.isNullOrEmpty(jobName)){
               job.setJobName(jobName);
           }
        }

        Element pEl = document.select("div.job-primary>div.info-primary>p").first();
        if(pEl!=null){
            List<TextNode> mList = pEl.textNodes();
            if(mList.size()>0){
                String education = mList.get(2).text().trim();
                if(!StringUtils.isNullOrEmpty(education)){
                    job.setEducation(education);
                }

                String experience = mList.get(1).text().trim();
                if(!StringUtils.isNullOrEmpty(experience)){
                    job.setExperience(experience);
                }

                String jobPlace = mList.get(0).text().trim();
                if(!StringUtils.isNullOrEmpty(jobPlace)){
                    job.setJobPlace(jobPlace);
                }
            }
        }

        Element jobContentEl = document.select("div.detail-content>div.job-sec>div.text").first();
        if(jobContentEl!=null){
            String jobContent = jobContentEl.text().trim();
            if(!StringUtils.isNullOrEmpty(jobContent)){
                job.setJobContent(jobContent);
            }
        }



        Element jobTypeEl = document.select("div.job-primary>div.info-primary>div.job-tags").first();
        if(jobTypeEl!=null){
            Elements elements = jobTypeEl.children();
            if(elements.size()>0){
                StringBuilder sb = new StringBuilder();
                for(Element element:elements){
                    String jobType = element.text().trim();
                    if(!StringUtils.isNullOrEmpty(jobType)){
                        sb.append(jobType+" ");
                    }
                }
                job.setJobType(sb.toString());
            }

        }

        job.setJobUrl(page.getUrl().toString());

        Element publishDateEl = document.select("div.job-primary>div.info-primary>div.job-author>span.time").first();
        if(jobNameEl!=null){
            String publishDate = publishDateEl.text().trim();
            if(!StringUtils.isNullOrEmpty(publishDate)){
                job.setPublishDate(publishDate);
            }
        }

        Element salaryEl = document.select("div.job-primary>div.info-primary>div.name>span.badge").first();
        if(jobNameEl!=null){
            String salary = salaryEl.text().trim();
            if(!StringUtils.isNullOrEmpty(salary)){
                job.setSalary(salary);
            }
        }
        job.setCompany(company);
        job.setSite(sites);
        return job;
    }


    /**
     * 获取company实体
     * @param document
     * @return
     */
    private Company getCompany(Document document) {
        Element companyInfoEl = document.select("div.info-comapny").first();
        if(companyInfoEl!=null){
            Company company = new Company();
            Element companyNameEl = companyInfoEl.select("div.info-comapny>p").first();
            if(companyNameEl!=null){
                String companyName = companyNameEl.text().trim();
                if(!StringUtils.isNullOrEmpty(companyName)){
                    company.setCompanyName(companyName);
                }
            }
            Element companyPlaceEl = null;
            if(companyPlaceEl!=null){
                String companyPlace = companyPlaceEl.text().trim();
                if(!StringUtils.isNullOrEmpty(companyPlace)){
                    company.setCompanyPlace(companyPlace);
                }
            }

            Element pEl = companyInfoEl.select("div.info-comapny>p").last();
            if(pEl!=null){
                List<TextNode> mLsit = pEl.textNodes();
                if(mLsit.size()>0){
                    String companyScale = mLsit.get(mLsit.size()-1).text().trim();
                    if(!StringUtils.isNullOrEmpty(companyScale)){
                        company.setCompanyScale(companyScale);
                    }

                    String companyType = mLsit.get(0).text().trim();
                    if(!StringUtils.isNullOrEmpty(companyType)){
                        company.setCompanyType(companyType);
                    }
                }
            }


            Element companySummaryEl = companyInfoEl.select("div.info-comapny>h3.name").first();
            if(companySummaryEl!=null){
                String companySummary = companySummaryEl.text().trim();
                if(!StringUtils.isNullOrEmpty(companySummary)){
                    company.setCompanySummary(companySummary);
                }
            }

            Company newCompany = getCompany(company);
            return newCompany;
        }
       return null;
    }

    private void listProcessor(Page page) {
        Document document = page.getHtml().getDocument();
        Element articleContainer = document.select("div.job-list>ul").first();
        if(articleContainer==null){
            logger.error("列表容器解析失败");
            return ;
        }
        Elements articleEls = articleContainer.select("ul>li");
        Map<String,Object> extras = null;
        for(Element article:articleEls){
            extras = getExtraMap(page.getRequest());
            Element aEl = article.select("li>a").first();
            String href = aEl.attr("href");
            if(!StringUtils.isNullOrEmpty(href)){
                if(href.startsWith("/")){
                    href = "https://www.zhipin.com"+href;
                }
                addRequestUrl(href,page,extras);
            }
        }

        Element nextPage = document.select("div.page>a.next").first();
        if(nextPage!=null){
           extras = getExtraMap(page.getRequest());
           int limitPageNum = Integer.parseInt(extras.get("endPage")+"");
           String href = nextPage.attr("href");
           if(!StringUtils.isNullOrEmpty(href)){
               int nextPageNum = Integer.parseInt(SpiderCommon.getRegexWord(href,"page=(\\d{1,3})"));
               if(nextPageNum<=limitPageNum){
                 addUrl(href,page,extras);
               }
           }

        }

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[]arr){

    }
}
