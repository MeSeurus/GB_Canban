package com.canban.web.analytics.integration;

import com.canban.api.analytics.EventsAnalyticsDtoWithList;
import com.canban.api.analytics.TasksAnalyticsDtoWithList;
import com.canban.api.exceptions.ServerNotWorkingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CoreIntegration {

    private final WebClient coreServiceWebClient;

    public EventsAnalyticsDtoWithList getEventsAnalyticsFromCore() {
        return coreServiceWebClient.get()
                .uri("/api/v1/events/analytics")
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new ServerNotWorkingException("Core-MS not working")))
                .bodyToMono(EventsAnalyticsDtoWithList.class)
                .block();
    }

    public TasksAnalyticsDtoWithList getTasksAnalyticsFromCore() {
        return coreServiceWebClient.get()
                .uri("/api/v1/tasks/analytics")
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new ServerNotWorkingException("Core-MS not working")))
                .bodyToMono(TasksAnalyticsDtoWithList.class)
                .block();
    }

}
