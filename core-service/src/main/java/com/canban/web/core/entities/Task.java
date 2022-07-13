package com.canban.web.core.entities;

import com.canban.web.core.enums.Priority;
import com.canban.web.core.enums.State;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "tasks")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Task extends Event{

    @Column(name = "state")
    private State state;

    @Column(name = "priority")
    private Priority priority;

    @Column(name = "canbanName")
    private String canbanName;


}
