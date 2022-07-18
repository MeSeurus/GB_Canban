package com.canban.web.core.enums;

public enum State {
    CREATED ("Создано"),
    IN_PROGRESS ("В работе"),
    COMPLETE ("Выполнено");

    private String value;

    State(String value){
        this.value = value;
    }
    public String getValue(){ return value;}
}
