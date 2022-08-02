package com.canban.api.analytics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Schema(description = "Модель события для сервиса аналитики")
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    @Schema(description = "Пользователи, которые имеют доступ к событию", required = true, example = "user2, user3")
    private Set<String> users;

}
