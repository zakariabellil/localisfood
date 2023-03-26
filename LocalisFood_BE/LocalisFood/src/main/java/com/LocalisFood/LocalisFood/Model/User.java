package com.LocalisFood.LocalisFood.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Data // Create getters and setters
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;

    @NotBlank(message = "Password is required")
    @Size(min = 4, max = 24, message = "Minimum username length: 4 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @Email
    @NotEmpty(message = "Email is required")
    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;


    @ElementCollection(fetch = FetchType.EAGER)
    List<UserRole> UserRoles ;
}
