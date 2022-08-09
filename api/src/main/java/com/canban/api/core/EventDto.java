package com.canban.api.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Schema(description = "Модель события")
@Getter
@Setter
@ToString
public class EventDto {
    @Schema(description = "ID события", required = true, example = "1")
    private Long id;

    @Schema(description = "Название события", required = true, example = "Создать программу Hello World")
    @JsonProperty("text")
    private String title;

    @Schema(description = "Описание события", required = true)
    private String content;

    @Schema(description = "Пользователь ответственный за событие", required = true, example = "user1")
    private String username;

    @Schema(description = "Дата начала события", required = true)
    @JsonProperty("start_date")
    private LocalDateTime beginDate;

    @Schema(description = "Дата окончания события", required = true)
    @JsonProperty("end_date")
    private LocalDateTime endDate;

    private Set<String> users;

    public EventDto(Long id,
                    String title,
                    String content,
                    String username,
                    LocalDateTime beginDate,
                    LocalDateTime endDate,
                    Set<String> users
    ) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.users = users;

    }

    public EventDto(Long id, String title, String content, String username, LocalDateTime beginDate, LocalDateTime endDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public EventDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}