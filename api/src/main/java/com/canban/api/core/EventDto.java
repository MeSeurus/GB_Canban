package com.canban.api.core;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "Модель события")
@Getter
@Setter
@ToString
public class EventDto {
    @Schema(description = "ID события", required = true, example = "1")
    private Long id;
    @Schema(description = "Название события", required = true, example = "Создать программу Hello World")
    private String title;

    @Schema(description = "Описание события", required = true)
    private String content;


    @Schema(description = "Пользователь ответственный за событие", required = true, example = "user1")
    private String username;

    /**
     * Вот тут вопрос, событие на один день или на несколько?
     * Если на один, то смысла не имеет, можно дату создания запоминать просто
     */
    @Schema(description = "Дата начала события", required = true)
    private LocalDateTime eventDate;


    public EventDto(Long id, String title, String content, String username, LocalDateTime eventDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.eventDate = eventDate;
    }

    public EventDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }


}
