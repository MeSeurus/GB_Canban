package com.canban.web.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class KanbanBoardDetailsRq {

    @Schema(description = "Название канбан-доски", required = true)
    private String title;

}
