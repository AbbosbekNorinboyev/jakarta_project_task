package com.example.lesson10tasks.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthUser extends Auditable {
//    @NotBlank(message = "Email bo'sh bo'lmasligi kerak")
//    @Email(message = "Email Email bo'lishi kerak")
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role = "USER";
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder(builderMethodName = "childBuilder")
    public AuthUser(Integer id, LocalDate created_date, LocalDate updated_date,
                    String email, String password, String role, Status status) {
        super(id, created_date, updated_date);
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public enum Status {
        IN_ACTIVE,
        ACTIVE,
        BLOCKED
    }
}
