package com.canban.api.core;

public enum Priority {
    HIGH("Высокий"),
    NORMAL("Нормальный"),
    LOW("Низкий");

    private String value;

    Priority(String value){
        this.value = value;
    }
    public String getValue(){return value;}

}

