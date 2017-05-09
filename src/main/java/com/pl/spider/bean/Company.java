package com.pl.spider.bean;

/**
 * Created by Administrator on 2017/4/22.
 */
public class Company {
    private int companyId;
    private String companyName;
    private String companyScale;
    private String companyType;

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyScale(String companyScale) {
        this.companyScale = companyScale;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public void setCompanySummary(String companySummary) {
        this.companySummary = companySummary;
    }

    public void setCompanyPlace(String companyPlace) {
        this.companyPlace = companyPlace;
    }

    private String companySummary;
    private String companyPlace;

    public int getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyScale() {
        return companyScale;
    }

    public String getCompanyType() {
        return companyType;
    }

    public String getCompanySummary() {
        return companySummary;
    }

    public String getCompanyPlace() {
        return companyPlace;
    }
}
