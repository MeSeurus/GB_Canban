package com.canban.web.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@MappedSuperclass
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public abstract class AbstractEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    protected Long id;

    @Column(name = "title")
    @NotNull
    protected String title;

    @Column(name = "content")
    protected String content;

    @Column(name = "username")
    protected String username;

    @Column(name = "begin_date")
    @NotNull
    protected LocalDateTime beginDate; //дата начала события

    @Column(name = "end_date")
    @NotNull
    protected LocalDateTime endDate; //дата окончания события

    @CreationTimestamp
    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;
}