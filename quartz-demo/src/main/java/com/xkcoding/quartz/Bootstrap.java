package com.xkcoding.quartz;

import com.xkcoding.quartz.job.JobDemo1;
import com.xkcoding.quartz.job.JobDemo2;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-06-05 11:03
 */
@SpringBootApplication
public class Bootstrap implements CommandLineRunner {
    @Autowired
    private Scheduler scheduler;

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
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
