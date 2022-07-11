package com.planner.web.api.core;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Модель события")
public class EventDto {
    @Schema(description = "ID события", required = true, example = "1")
    private Long id;
    @Schema(description = "Название события", required = true, example = "Создать программу Hello World")
    private String title;

    @Schema(description = "Описание события", required = true)
    private String content;

    @Schema(description = "День недели", required = true, example = "5")
    private Integer weekday;

    @Schema(description = "Пользователь ответственный за событие", required = true, example = "user1")
    private String nickname;

    /**
     * Вот тут вопрос, событие на один день или на несколько?
     * Если на один, то смысла не имеет, можно дату создания запоминать просто
     */
    @Schema(description = "Дата начала события", required = true)
    private LocalDateTime eventStart;

    @Schema(description = "Дата окончания события", required = true)
    private LocalDateTime eventEnd;

    public EventDto(Long id, String title, String content, Integer weekday, String nickname, LocalDateTime eventStart, LocalDateTime eventEnd) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.weekday = weekday;
        this.nickname = nickname;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public EventDto(Long id, String title, String content, Integer weekday) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.weekday = weekday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getEventStart() {
        return eventStart;
    }

    public void setEventStart(LocalDateTime eventStart) {
        this.eventStart = eventStart;
    }

    public LocalDateTime getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(LocalDateTime eventEnd) {
        this.eventEnd = eventEnd;
    }

    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", weekday='" + weekday + '\'' +
                ", nickname='" + nickname + '\'' +
                ", eventStart=" + eventStart +
                ", eventEnd=" + eventEnd +
                '}';
    }
}
