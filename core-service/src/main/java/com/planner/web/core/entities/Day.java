package com.planner.web.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "days")
@AllArgsConstructor
@NoArgsConstructor
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cur_date")
    private LocalDate date;

    @Column(name = "weekday")
    private Integer weekday;

    @OneToMany(mappedBy = "day", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Event> events;

    public Day(Long id, LocalDate date, Integer weekday) {
        this.id = id;
        this.date = date;
        this.weekday = weekday;
    }
    
    public Day(Long id, LocalDate date){
        this.id = id;
        this.date = date;
        this.weekday = date.getDayOfWeek().getValue();
    }

    public Day(LocalDate date){
        this.date = date;
        this.weekday = date.getDayOfWeek().getValue();
    }
}
