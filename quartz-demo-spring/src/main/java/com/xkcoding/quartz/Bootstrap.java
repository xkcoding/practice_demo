package com.xkcoding.quartz;

import com.xkcoding.quartz.job.JobDemo1;
import com.xkcoding.quartz.job.JobDemo2;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-06-05 11:03
 */
@Configuration
@ComponentScan(basePackages = "com.xkcoding.quartz")
public class Bootstrap {

    public static void main(String[] args) throws SchedulerException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Bootstrap.class);
        ((AnnotationConfigApplicationContext) applicationContext).start();
        Scheduler scheduler = (StdScheduler) applicationContext.getBean("scheduler");
        initQuartzJobs(scheduler);
    }

    public static void initQuartzJobs(Scheduler scheduler) throws SchedulerException {
        // JobDemo1
        JobDetail job1 = JobBuilder.newJob(JobDemo1.class).withIdentity(JobDemo1.class.getName()).build();
        SimpleScheduleBuilder demo1Scheduler = SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(2);
        SimpleTrigger trigger1 = TriggerBuilder.newTrigger().withIdentity(JobDemo1.class.getName()).withSchedule(demo1Scheduler).build();

        scheduler.scheduleJob(job1, trigger1);

        // JobDemo2
        JobDetail job2 = JobBuilder.newJob(JobDemo2.class).withIdentity(JobDemo2.class.getName()).build();
        SimpleScheduleBuilder demo2Scheduler = SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(5);
        SimpleTrigger trigger2 = TriggerBuilder.newTrigger().withIdentity(JobDemo2.class.getName()).withSchedule(demo2Scheduler).build();

        scheduler.scheduleJob(job2, trigger2);
    }
}
