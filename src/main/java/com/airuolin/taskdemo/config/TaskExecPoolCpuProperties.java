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
@ConfigurationProperties(prefix = "task-exec-pool-cpu")
public class TaskExecPoolCpuProperties {

    private int coreSize = 8;
    private int queueCapacity = Integer.MAX_VALUE;
    private int maxSize = Integer.MAX_VALUE;
    private int keepAlive = 60;
    private String threadNamePrefix = "task-exec-pool-cpu-";
}
