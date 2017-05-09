package com.pl.spider.main;

import com.pl.spider.bean.XmlBean;
import com.pl.spider.config.GlobleSpiderService;
import com.pl.spider.config.SpiderApplicationContex;
import com.pl.spider.utils.StringUtils;
import com.pl.spider.utils.XmlUtil;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 * 项目的启动函数
 */

public class MainProcessor {
    public static void main(String []arr) throws Exception{
        List<XmlBean> xmlBeans = parseXml();
        GlobleSpiderService globleSpiderService = (GlobleSpiderService) SpiderApplicationContex.applicationContext.getBean("globleSpiderService");
        if(xmlBeans!=null&&xmlBeans.size()>0){
            taskAction(xmlBeans,globleSpiderService);
        }

    }

    private static void taskAction(List<XmlBean> xmlBeans,GlobleSpiderService globleSpiderService) {
        for(XmlBean xmlBean:xmlBeans){
            if(!StringUtils.isNullOrEmpty(xmlBean.getUrl())&&
                    !StringUtils.isNullOrEmpty(xmlBean.getProcessor())&&
                    !StringUtils.isNullOrEmpty(xmlBean.getPipeline())){
                globleSpiderService.start(xmlBean);
            }

         }
    }

    private static List<XmlBean> parseXml() throws Exception{
        List<XmlBean> xmlBeans = new ArrayList<XmlBean>();
        Document document = XmlUtil.readXml("/web_config.xml");
        Element rootEl = document.getRootElement();
        List<Element> childList = rootEl.elements();
        if(childList.size()>0){
            for(Element element:childList){
                XmlBean xmlBean = new XmlBean();
                xmlBean.setProcessor(element.attributeValue("processor"));
                xmlBean.setPipeline(element.attributeValue("pipeline"));
                xmlBean.setSiteName(element.attributeValue("siteName"));
                xmlBean.setUrl(element.attributeValue("url"));
                xmlBean.setEndPage(element.attributeValue("endPage"));
                xmlBean.setThreadNum(element.attributeValue("threadNum"));
                xmlBeans.add(xmlBean);
            }
            return xmlBeans;
        }
        return null;
    }


}
