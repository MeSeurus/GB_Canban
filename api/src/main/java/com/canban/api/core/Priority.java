package com.canban.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Перечисления приоритета")
public enum Priority {
    HIGH("Высокий"),
    NORMAL("Нормальный"),
    LOW("Низкий");

    @Schema(description = "Название приоритета", required = true, example = "HIGH")
    private String value;

    Priority(String value){
        this.value = value;
    }
    public String getValue(){return value;}

}

