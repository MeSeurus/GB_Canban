package com.canban.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Перечисления статуса")
public enum State {
    CREATED ("Создано"),
    IN_PROGRESS ("В работе"),
    COMPLETE ("Выполнено");

    @Schema(description = "Название статуса", required = true, example = "В работе")
    private String value;

    State(String value){
        this.value = value;
    }
    public String getValue(){ return value;}

}