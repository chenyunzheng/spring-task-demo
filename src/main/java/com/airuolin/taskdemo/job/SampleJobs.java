package com.airuolin.taskdemo.job;

import com.airuolin.taskdemo.task.SampleTasks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author chrischen
 */
@Slf4j
@Component
@EnableScheduling
public class SampleJobs {

    @Resource
    private SampleTasks sampleAsyncTask;

    @Scheduled(cron = "0/2 * * * * ?")
    public void firstJob() {
        log.warn("hello, firstJob()");
        Future<String> china = sampleAsyncTask.firstTask("china");
        Future<String> world = sampleAsyncTask.secondTask("world");
        String result = "";
        try {
            String first = china.get();
            String second = world.get();
            result = first + "_" + second;
        } catch (InterruptedException | ExecutionException e) {
            log.error("Exception occurs in firstJob()", e);
        }
        log.warn("firstJob() completed, result = {}", result);
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void secondJob() {
        log.warn("hello, secondJob() and try to run task in another thread pool by async");
        sampleAsyncTask.runAsync("hello, async");
    }
}
