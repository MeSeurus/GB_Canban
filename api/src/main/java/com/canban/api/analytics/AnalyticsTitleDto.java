package com.canban.api.analytics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "Модель для вывода названий событий и задач")
@Data
@AllArgsConstructor
public class AnalyticsTitleDto {

    @Schema(description = "Название события/задачи", required = true, example = "Create program")
    private String title;

}
