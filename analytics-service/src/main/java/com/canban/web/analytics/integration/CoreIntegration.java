package com.canban.web.analytics.integration;

import com.canban.api.analytics.EventsAnalyticsDtoWithList;
import com.canban.api.analytics.TasksAnalyticsDtoWithList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CoreIntegration {

    private final WebClient coreServiceWebClient;

    public EventsAnalyticsDtoWithList getEventsAnalyticsFromCore() {
        return coreServiceWebClient.get()
                .uri("/api/v1/events/all")
                .retrieve()
                .bodyToMono(EventsAnalyticsDtoWithList.class)
                .block();
    }

    public TasksAnalyticsDtoWithList getTasksAnalyticsFromCore() {
        return coreServiceWebClient.get()
                .uri("/api/v1/tasks/all")
                .retrieve()
                .bodyToMono(TasksAnalyticsDtoWithList.class)
                .block();
    }

}
