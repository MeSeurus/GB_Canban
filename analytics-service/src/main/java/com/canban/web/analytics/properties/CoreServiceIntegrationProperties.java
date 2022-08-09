package com.canban.web.analytics.properties;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.core-service")
@Data
public class CoreServiceIntegrationProperties {
    private String url;

    @ConstructorBinding
    @ConfigurationProperties(prefix = "integrations.core-service.timeouts")
    @Data
    public static class CoreServiceTimeoutsProperties {
        private Integer read;
        private Integer write;
        private Integer connection;
    }



}
