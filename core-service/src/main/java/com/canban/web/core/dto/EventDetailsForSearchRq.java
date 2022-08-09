package com.canban.web.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EventDetailsForSearchRq {

    @Schema(description = "Полное или частичное название ивента", required = false)
    private String titlePart;

    @Schema(description = "Максимальная дата начала ивента", required = false)
    private String maxBeginDate;

    @Schema(description = "Минимальная дата начала ивента", required = false)
    private String minBeginDate;

    @Schema(description = "Минимальная дата окончания ивента", required = false)
    private String maxEndDate;

    @Schema(description = "Минимальная дата окончания ивента", required = false)
    private String minEndDate;



}
