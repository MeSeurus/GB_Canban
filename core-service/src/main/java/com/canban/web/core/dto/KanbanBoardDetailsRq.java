package com.canban.web.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "Модель для создания канбан-доски")
public class KanbanBoardDetailsRq {

    @Schema(description = "Название канбан-доски", required = true)
    private String title;

}
