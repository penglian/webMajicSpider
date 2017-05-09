package com.pl.spider.bean;

/**
 * Created by Administrator on 2017/4/22.
 * XML的实体类
 */
public class XmlBean {
    private String processor;
    private String pipeline;
    private String url;
    private String siteName;
    private String endPage;
    private String threadNum;

    public void setThreadNum(String threadNum) {
        this.threadNum = threadNum;
    }

    public String getThreadNum() {

        return threadNum;
    }

    public void setEndPage(String endPage) {
        this.endPage = endPage;
    }

    public String getEndPage() {
        return endPage;
    }

    public String getProcessor() {
        return processor;
    }

    public String getPipeline() {
        return pipeline;
    }

    public String getUrl() {
        return url;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setPipeline(String pipeline) {
        this.pipeline = pipeline;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
