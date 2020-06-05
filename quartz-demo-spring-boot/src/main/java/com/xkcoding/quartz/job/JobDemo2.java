package com.xkcoding.quartz.job;

import com.xkcoding.quartz.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 测试定时任务 2
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-06-05 11:11
 */
@Slf4j
public class JobDemo2 implements Job {
    @Autowired
    private DemoService demoService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        demoService.echo("JobDemo2");
    }
}
