package com.canban.web.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Schema(description = "Модель деталей события")
@Data
@AllArgsConstructor
public class EventDetails {

    @Schema(description = "Название события", required = true)
    private String title;

    @Schema(description = "Описание события", required = true)
    private String content;

    @Schema(description = "Дата назначения события")
    LocalDateTime beginDate; //дата назначения события

}
