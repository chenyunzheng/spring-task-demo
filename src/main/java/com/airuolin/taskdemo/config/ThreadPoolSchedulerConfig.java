package com.airuolin.taskdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chrischen
 */
@Slf4j
@Configuration
public class ThreadPoolSchedulerConfig {

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(TaskSchedulePoolDefaultProperties taskSchedulePoolDefaultProperties) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        //实际为核心线程数
        threadPoolTaskScheduler.setPoolSize(taskSchedulePoolDefaultProperties.getSize());
        //线程池名称前缀
        threadPoolTaskScheduler.setThreadNamePrefix(taskSchedulePoolDefaultProperties.getThreadNamePrefix());
        threadPoolTaskScheduler.setErrorHandler(t -> log.error("任务调度发生异常", t));
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);
        threadPoolTaskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskScheduler.initialize();
        return threadPoolTaskScheduler;
    }

}
