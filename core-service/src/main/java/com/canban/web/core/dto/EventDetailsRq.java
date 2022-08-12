package com.canban.web.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Schema(description = "Модель деталей события")
@Data
@AllArgsConstructor
public class EventDetailsRq {

    @Schema(description = "Название события", required = true)
    @JsonProperty("text")
    private String title;

    @Schema(description = "Описание события", required = true)
    private String content;

    @Schema(description = "Дата начала события")
    @JsonProperty("start_date")
    private LocalDateTime beginDate;

    @Schema(description = "Дата окончания события")
    @JsonProperty("end_date")
    private LocalDateTime endDate;

    @Schema(description = "Пользователи, добавленные к событию")
    @JsonProperty("added_users")
    private Set<String> addedUsers;
}