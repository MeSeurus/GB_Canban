package com.canban.auth.entity.security;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users_activation_codes")
public class UserAwaitActivation {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "secret_code")
    private String secretCode;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
