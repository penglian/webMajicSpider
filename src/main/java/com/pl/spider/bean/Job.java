package com.pl.spider.bean;

/**
 * Created by Administrator on 2017/4/22.
 */
public class Job {
    private int jobId;
    private String jobName;
    private String salary;
    private String jobContent;
    private String jobPlace;
    private String jobType;
    private String publishDate;
    private String education;
    private String jobUrl;

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    public void setJobPlace(String jobPlace) {
        this.jobPlace = jobPlace;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setSite(Sites site) {
        this.site = site;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    private String experience;
    private Sites site;

    public int getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public String getSalary() {
        return salary;
    }

    public String getJobContent() {
        return jobContent;
    }

    public String getJobPlace() {
        return jobPlace;
    }

    public String getJobType() {
        return jobType;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getEducation() {
        return education;
    }

    public String getExperience() {
        return experience;
    }

    public Sites getSite() {
        return site;
    }

    public Company getCompany() {
        return company;
    }

    private Company company;
}
