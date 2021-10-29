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
@ConfigurationProperties("task-schedule-pool-default")
public class TaskSchedulePoolDefaultProperties {

    private int size = 1;
    private String threadNamePrefix = "task-schedule-pool-default-";

}
