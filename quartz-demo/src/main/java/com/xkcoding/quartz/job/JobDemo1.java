package com.xkcoding.quartz.job;

import com.xkcoding.quartz.service.DemoService;
import com.xkcoding.quartz.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * <p>
 * 测试定时任务 1
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-06-05 11:11
 */
@Slf4j
public class JobDemo1 implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        DemoService demoService = SpringUtil.getBean(DemoService.class);
        demoService.echo("JobDemo1");
    }
}
