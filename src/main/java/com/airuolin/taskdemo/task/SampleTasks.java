package com.airuolin.taskdemo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author chrischen
 */
@Slf4j
@Component
@EnableAsync
public class SampleTasks {

    @Async("cpuThreadPoolTaskExecutor")
    public void runAsync(String param) {
        log.warn("runAsync() with param = {}", param);
        //try to throw exception
        Integer.parseInt("---");
    }

    @Async
    public Future<String> firstTask(String param) {
        log.warn("firstTask() with param = {}", param);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(param);
    }

    @Async
    public Future<String> secondTask(String param) {
        log.warn("secondTask() with param = {}", param);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(param);
    }

}
