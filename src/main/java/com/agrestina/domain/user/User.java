package com.agrestina.domain.user;


import com.agrestina.dto.user.RegisterRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

    @Column(nullable = false)
    private boolean active;

    public void disabled(){
        this.active = false;
    }

    public void activate(){
        this.active = true;
    }

    public User(RegisterRequestDTO dto){
        this.name = dto.name();
        this.login = dto.login();
        this.password = dto.password();
        this.userRole = dto.userRole();
    }
}