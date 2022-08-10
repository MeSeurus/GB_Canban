package com.canban.web.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Set;


@Data
@Builder
@Schema(description = "Модель канбан-доски")
public class KanbanBoardDetailsRs {

    @Schema(description = "Id канбан-доски", required = true)
    private Long id;

    @Schema(description = "Имя канбан-доски", required = true)
    private String name;

    @Schema(description = "Создатель канбан-доски", required = true)
    private String createdBy;

}
