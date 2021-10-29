package com.airuolin.taskdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author chrischen
 */
@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "task-exec-pool-default")
public class TaskExecPoolDefaultProperties {

    private int coreSize = 20;
    private int queueCapacity = 300;
    private int maxSize = 200;
    private int keepAlive = 60;
    private String threadNamePrefix = "task-exec-pool-default-";

}
