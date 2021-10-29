package com.airuolin.taskdemo.job;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

/**
 * @author chrischen
 */
@Slf4j
@Component
public class ScheduledJobs implements SchedulingConfigurer {

    @Autowired
    @Qualifier("threadPoolTaskScheduler")
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Setter
    private String cron = "0/50 * * * * ?";
    @Setter
    private volatile String logic;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(threadPoolTaskScheduler);
        taskRegistrar.addCronTask(() -> {
            int activeCount = threadPoolTaskScheduler.getActiveCount();
            log.info("active number of threads in the pool = {}", activeCount);
            log.info("---> job start, thread name = {}", Thread.currentThread().getName());
            //business logic
            log.warn("logic = {}", logic);
            log.info("---> job end");
        }, cron);

        taskRegistrar.addCronTask(() -> {
            int activeCount = threadPoolTaskScheduler.getActiveCount();
            log.info("active number of threads in the pool = {}", activeCount);
            log.info("---> thread name = {}", Thread.currentThread().getName());
        }, "0/30 * * * * ?");
    }
}
