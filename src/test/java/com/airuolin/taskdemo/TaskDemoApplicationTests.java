package com.airuolin.taskdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;

@SpringBootTest
class TaskDemoApplicationTests {

    @Autowired
    @Qualifier("defaultThreadPoolTaskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private ThreadPoolTaskExecutor cpuThreadPoolTaskExecutor;

    @Test
    void defaultThreadPoolTaskExecutor() {
        threadPoolTaskExecutor.submit(() -> {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    void cpuThreadPoolTaskExecutor() {
        cpuThreadPoolTaskExecutor.execute(() -> {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    void threadPoolTaskScheduler() {

    }

}
