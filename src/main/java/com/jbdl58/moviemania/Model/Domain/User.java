package com.jbdl58.moviemania.Model.Domain;

import com.jbdl58.moviemania.Model.Enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    private String id;

    @NotBlank
    @Max(100)
    @Min(15)
    private Integer age;

    @Column(unique = true, nullable = false)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @NotBlank
    private String email;

    @Size(max = 10)
    @Column(unique = true, nullable = false)
    @NotBlank
    private String mobile;

    @Size(max=100)
    @NotBlank
    private String name;

    @Size(max=10, min = 5)
    @Column(unique = true, nullable = false)
    @NotBlank
    private String password;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Role role;

    @CreationTimestamp
    private Date creationDate;
    
}
