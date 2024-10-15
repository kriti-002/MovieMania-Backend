package com.demo.MovieMania.Model.Domain;

import com.demo.MovieMania.Model.Domain.Enums.Role;
import com.demo.MovieMania.Model.Response.UserLoginResponse;
import com.demo.MovieMania.Model.Response.UserResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(18)
    @Max(100)
    @NotNull(message = "Age should not be null.")
    private Integer age;

    @NotNull(message = "Email should not be null.")
    @Email(message = "Email should follow strict format.")
    @Column(name = "email", unique=true, length = 100)
    private String email;

    @NotNull(message = "Password should not be null.")
    @Column(unique=true)
    private String password;

    @NotNull(message = "Mobile number should not be null.")
    @Column(name = "mobile" ,columnDefinition = "varchar(10)", unique=true)
    private String mobile;

    @NotNull(message = "Please provide your name.")
    @Column(name = "name" ,columnDefinition = "varchar(100)")
    private String name;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role" ,columnDefinition = "varchar(30) default 'USER'")
    private Role role;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> favourite = new ArrayList<>();

    public UserResponse toResponse(String message){
        return UserResponse.builder()
                .age(age)
                .email(email)
                .mobile(mobile)
                .role(role)
                .password(password)
                .name(name)
                .message(message)
                .favourite(favourite != null ? favourite.stream().map(Movie::toResponse).toList() : Collections.emptyList())
                .build();
    }

    public static UserResponse toResponse(User u){
        return UserResponse.builder()
                .message("Done!")
                .name(u.getName())
                .password(u.getPassword())
                .role(u.getRole())
                .mobile(u.getMobile())
                .email(u.getEmail())
                .age(u.getAge())
                .favourite(u.getFavourite() != null ? u.getFavourite().stream().map(Movie::toResponse).toList() : Collections.emptyList())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
