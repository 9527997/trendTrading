package cn.mss.trend.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.hutool.core.date.DateUtil;
import cn.mss.trend.pojo.Index;
import cn.mss.trend.service.IndexDataService;
import cn.mss.trend.service.IndexService;

public class IndexDataSyncJob extends QuartzJobBean{
	@Autowired
    private IndexService indexService;
 
    @Autowired
    private IndexDataService indexDataService;
     
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("定时启动：" + DateUtil.now());
        List<Index> indexes = indexService.fresh();
        for (Index index : indexes) {
             indexDataService.fresh(index.getCode());
        }
        System.out.println("定时结束：" + DateUtil.now());
 
    }
}
