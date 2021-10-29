# Spring task and job

Components to rapidly build tasks and jobs based on Spring Boot

* **Task**
```java
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
```

* **Job - task with scheduling**

```java
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
```

***Note***

```markdown
spring.task.execution - one thread pool
spring.task.scheduling - another thread pool
```



