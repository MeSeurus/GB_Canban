package com.canban.web.core.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@MappedSuperclass
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public abstract class AbstractEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "content")
    String content;

    @Column(name = "username")
    String username;

    @Column(name = "begin_date")
    LocalDateTime beginDate; //дата назначения события

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;


    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
