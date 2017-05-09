package com.pl.spider.dao;

import com.pl.spider.bean.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/22.
 */
@Component
public class CompanyDao {
    @Autowired
    @Resource(name = "spiderJdbcTemplate")
    private JdbcTemplate template;

    public void addCompany(Company company){
        String sql = "insert into company (companyName,companyScale,companyType," +
                "companyPlace,companySummary) values(?,?,?,?,?)";
        template.update(sql,new Object[]{company.getCompanyName(),company.getCompanyScale(),
                company.getCompanyType(),company.getCompanyPlace(),company.getCompanySummary()});
    }

    public Company getCompany(Company company){
        String sql = "select id from company where companyName=? and companyScale=? and companyType=?";
        List<Map<String,Object>> mList = template.queryForList(sql,company.getCompanyName(),company.getCompanyScale(),company.getCompanyType());
        if(mList.size()==0){
            return null;
        }

        Map<String,Object> map = mList.get(0);
        company.setCompanyId(Integer.parseInt(map.get("id")+""));
        return company;
    }

}
