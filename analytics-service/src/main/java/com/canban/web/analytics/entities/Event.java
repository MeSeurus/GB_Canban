package com.canban.web.analytics.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) /// ???
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "username")
    private String username;

    @Column(name = "begin_date")
    @NotNull
    private LocalDateTime beginDate; //дата назначения события

    @Column(name = "end_date")
    @NotNull
    private LocalDateTime endDate; //дата назначения события

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
