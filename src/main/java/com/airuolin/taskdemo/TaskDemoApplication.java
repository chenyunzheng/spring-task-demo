package com.airuolin.taskdemo;

import com.airuolin.taskdemo.job.ScheduledJobs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author chrischen
 */
@Slf4j
@SpringBootApplication
public class TaskDemoApplication implements ApplicationRunner {

    @Resource
    private ScheduledJobs scheduledJob;

    public static void main(String[] args) {
        SpringApplication.run(TaskDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("============ 启动后初始化定时任务 ============");
        //setCron()此时已经不起作用
        scheduledJob.setLogic("chen");
        Thread.sleep(20000);
        log.info("scheduledJob to update");
        //setCron()此时已经不起作用
        scheduledJob.setLogic("chris");
    }
}
