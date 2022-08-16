package com.canban.web.analytics.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "interval.cron")
@Data
public class SchedulerProperties {

    private String eventAnalytics;
    private String taskAnalytics;

}
