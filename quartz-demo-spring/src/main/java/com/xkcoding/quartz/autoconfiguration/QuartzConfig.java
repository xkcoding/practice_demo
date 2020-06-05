package com.xkcoding.quartz.autoconfiguration;

import lombok.SneakyThrows;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 配置类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-06-05 11:18
 */
@Configuration
public class QuartzConfig {
    @Autowired
    private CustomJobFactory customJobFactory;

    @SneakyThrows
    @Bean
    public Scheduler scheduler() {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 自定义 JobFactory 使得在 Quartz Job 中可以使用 @Autowired
        scheduler.setJobFactory(customJobFactory);
        scheduler.start();
        return scheduler;
    }

}
