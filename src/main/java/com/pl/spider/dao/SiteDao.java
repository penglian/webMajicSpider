package com.pl.spider.dao;

import com.pl.spider.bean.Sites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/22.
 */
@Component
public class SiteDao {
    @Autowired
    @Resource(name = "spiderJdbcTemplate")
    private JdbcTemplate template;

    public void addSite(Sites site){
        String sql = "insert into sites (siteName,siteUrl,spideDate) values(?,?,?)";
        template.update(sql,new Object[]{site.getSiteName(),site.getSiteUrl(),site.getSpiderDate()});
    }

    public Sites getBeanByUrl(String url) throws DataAccessException {
        Sites site = new Sites();
        String sql = "select id,siteName,siteUrl,spideDate from sites where siteUrl=?";
        List<Map<String,Object>>mList = template.queryForList(sql,url);
        if(mList.size()==0){
             return null;
        }
        Map<String,Object> map = mList.get(0);
        site.setSiteId(Integer.parseInt(map.get("id")+""));
        site.setSpiderDate((String)map.get("siteName"));
        site.setSiteUrl((String)map.get("siteUrl"));
        site.setSiteName((String)map.get("spideDate"));
        return site;
    }

    public Sites getBeanById(int siteId){
        Sites site = new Sites();
        String sql = "select id,siteName,siteUrl,spideDate from sites where id=?";
        List<Map<String,Object>>mList = template.queryForList(sql,siteId);
        if(mList.size()==0){
            return null;
        }
        Map<String,Object> map = mList.get(0);
        site.setSiteId(Integer.parseInt(map.get("id")+""));
        site.setSpiderDate((String)map.get("siteName"));
        site.setSiteUrl((String)map.get("siteUrl"));
        site.setSiteName((String)map.get("spideDate"));
        return site;
    }



}
