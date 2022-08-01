package com.canban.web.core.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public abstract class AbstractEvent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) /// ???
    @Column(name = "id")
    Long id;

    @Column(name = "title")
    @NotNull
    String title;

    @Column(name = "content")
    String content;

    @Column(name = "username")
    String username;

    @Column(name = "begin_date")
    @NotNull
    LocalDateTime beginDate; //дата начала события

    @Column(name = "end_date")
    @NotNull
    LocalDateTime endDate; //дата окончания события


    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;


    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

}

