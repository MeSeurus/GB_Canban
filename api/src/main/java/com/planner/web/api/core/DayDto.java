package com.planner.web.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Модель дня")
public class DayDto {
    @Schema(description = "ID дня", required = true, example = "1")
    private Long id;
    @Schema(description = "Дата", required = true, example = "2022-06-24")
    private LocalDate date;
    @Schema(description = "День недели", required = true, example = "5")
    private Integer weekday;


    public DayDto(Long id, LocalDate date, Integer weekday) {
        this.id = id;
        this.date = date;
        this.weekday = weekday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }

}
