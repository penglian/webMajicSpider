package com.pl.spider.dao;

import com.pl.spider.bean.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by penglian on 2017/4/21.
 */
@Component
public class JobDao {
    @Autowired
    @Resource(name = "spiderJdbcTemplate")
    private JdbcTemplate template;

    public boolean getJobByUrl(String url){
        String sql = "select id from job where jobUrl=?";
        List<Map<String,Object>>mList = template.queryForList(sql,url);
        if(mList.size()>0){
           return true;
        }
        return false;
    }

    public void addJob(Job job){
        String sql = "insert into job (jobName,salary,jobContent,jobPlace,jobType,publishDate,jobUrl,education,experience,siteId,companyId)" +
                " values(?,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,new Object[]{job.getJobName(),job.getSalary(),job.getJobContent()
                ,job.getJobPlace(),job.getJobType(),job.getPublishDate(),job.getJobUrl(),
                job.getEducation(),job.getExperience(),job.getSite().getSiteId(),job.getCompany().getCompanyId()});
    }
}
