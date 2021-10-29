package com.airuolin.taskdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chrischen
 */
@Configuration
public class ThreadPoolExecutorConfig {

    /**
     * I/O型线程池
     */
    @Bean("defaultThreadPoolTaskExecutor")
    @Primary
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(TaskExecPoolDefaultProperties taskExecPoolDefaultProperties) {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        threadPoolTaskExecutor.setCorePoolSize(taskExecPoolDefaultProperties.getCoreSize());
        //缓冲队列大小
        threadPoolTaskExecutor.setQueueCapacity(taskExecPoolDefaultProperties.getQueueCapacity());
        //最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(taskExecPoolDefaultProperties.getMaxSize());
        //允许线程空闲时间
        threadPoolTaskExecutor.setKeepAliveSeconds(taskExecPoolDefaultProperties.getKeepAlive());
        //允许shutdown时等待任务完成
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //shutdown时等待任务完成最大时间
        threadPoolTaskExecutor.setAwaitTerminationSeconds(taskExecPoolDefaultProperties.getKeepAlive());
        //线程池名称前缀
        threadPoolTaskExecutor.setThreadNamePrefix(taskExecPoolDefaultProperties.getThreadNamePrefix());
        //拒绝任务处理策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    /**
     * 计算型线程池
     */
    @Bean("cpuThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor cpuThreadPoolTaskExecutor(TaskExecPoolCpuProperties taskExecPoolCpuProperties) {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        threadPoolTaskExecutor.setCorePoolSize(taskExecPoolCpuProperties.getCoreSize());
        //缓冲队列大小
        threadPoolTaskExecutor.setQueueCapacity(taskExecPoolCpuProperties.getQueueCapacity());
        //最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(taskExecPoolCpuProperties.getMaxSize());
        //允许线程空闲时间
        threadPoolTaskExecutor.setKeepAliveSeconds(taskExecPoolCpuProperties.getKeepAlive());
        //允许shutdown时等待任务完成
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //shutdown时等待任务完成最大时间
        threadPoolTaskExecutor.setAwaitTerminationSeconds(taskExecPoolCpuProperties.getKeepAlive());
        //线程池名称前缀
        threadPoolTaskExecutor.setThreadNamePrefix(taskExecPoolCpuProperties.getThreadNamePrefix());
        //拒绝任务处理策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

}
