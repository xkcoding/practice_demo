package com.xkcoding.quartz.autoconfiguration;

import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 自定义 JobFactory
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-06-05 11:35
 */
@Configuration
public class CustomJobFactory implements JobFactory {
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Override
    public Job newJob(TriggerFiredBundle triggerFiredBundle, Scheduler scheduler) throws SchedulerException {
        return newJob(triggerFiredBundle);
    }

    @SneakyThrows
    private Job newJob(TriggerFiredBundle triggerFiredBundle) {
        JobDetail jobDetail = triggerFiredBundle.getJobDetail();
        Class<? extends Job> jobClass = jobDetail.getJobClass();
        Job job = jobClass.newInstance();
        autowireCapableBeanFactory.autowireBean(job);
        return job;
    }
}
