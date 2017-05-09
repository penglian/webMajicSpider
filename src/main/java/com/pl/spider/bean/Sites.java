package com.pl.spider.bean;

/**
 * Created by Administrator on 2017/4/22.
 */
public class Sites {
    private int siteId;
    private String siteName;
    private String siteUrl;
    private String spiderDate;

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public void setSpiderDate(String spiderDate) {
        this.spiderDate = spiderDate;
    }

    public int getSiteId() {
        return siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public String getSpiderDate() {
        return spiderDate;
    }
}
