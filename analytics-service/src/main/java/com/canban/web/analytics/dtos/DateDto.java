package com.canban.web.analytics.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Модель для запроса аналитики")
public class DateDto {

    @Schema(description = "Время начала поиска", required = true)
    private LocalDateTime startDate;

}
