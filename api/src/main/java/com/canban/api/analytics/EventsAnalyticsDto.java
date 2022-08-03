package com.canban.api.analytics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Schema(description = "Модель события для сервиса аналитики")
public class EventsAnalyticsDto {

    @Schema(description = "ID события", required = true, example = "1")
    private Long eventId;

    @Schema(description = "Название события", required = true, example = "Create program")
    private String eventTitle;

    @Schema(description = "Имя пользователя владельца события", required = true, example = "user1")
    private String eventUsername;

    @Schema(description = "Дата начала события", required = true, example = "2022-06-24 01:00:00")
    private LocalDateTime eventBeginDate;

    @Schema(description = "Дата окончания события", required = true, example = "2022-06-24 18:00:00")
    private LocalDateTime eventEndDate;

    public EventsAnalyticsDto() {
    }

    public EventsAnalyticsDto(Long eventId, String eventTitle, String eventUsername, LocalDateTime eventBeginDate, LocalDateTime eventEndDate) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventUsername = eventUsername;
        this.eventBeginDate = eventBeginDate;
        this.eventEndDate = eventEndDate;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventUsername() {
        return eventUsername;
    }

    public void setEventUsername(String eventUsername) {
        this.eventUsername = eventUsername;
    }

    public LocalDateTime getEventBeginDate() {
        return eventBeginDate;
    }

    public void setEventBeginDate(LocalDateTime eventBeginDate) {
        this.eventBeginDate = eventBeginDate;
    }

    public LocalDateTime getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(LocalDateTime eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

}
