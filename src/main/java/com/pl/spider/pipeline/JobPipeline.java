package com.pl.spider.pipeline;

import com.pl.spider.bean.Job;
import com.pl.spider.dao.JobDao;
import com.pl.spider.processor.JobProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by Administrator on 2017/4/21.
 */
@Component
public class JobPipeline implements Pipeline{
    private final static Logger logger = LoggerFactory.getLogger(JobProcessor.class.getName());
    @Autowired
    JobDao jobDao;
    public void process(ResultItems resultItems, Task task) {
        Job job = resultItems.get("job");
        jobDao.addJob(job);
        logger.info("信息添加成功");
    }
}
