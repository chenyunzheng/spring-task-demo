package com.airuolin.taskdemo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author chrischen
 */
@Slf4j
@Configuration
@EnableAsync
public class TaskAsyncConfig implements AsyncConfigurer {

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> {
            log.error("异步任务执行异常：方法[{}]，参数[{}]", method.getName(), params);
            log.error(ex.getMessage(), ex);
        };
    }
}
