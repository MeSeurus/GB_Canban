package com.canban.auth.entity.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users_activation_codes")
public class UserAwaitActivation {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "secret_code")
    private String secretCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "codetype")
    private CodeType codeType;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public UserAwaitActivation(String username, String secretCode, CodeType codeType) {
        this.username = username;
        this.secretCode = secretCode;
        this.codeType = codeType;
    }

}
