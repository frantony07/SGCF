package org.ONE.models;

import jakarta.persistence.*;
import org.ONE.models.ENUM.Permission;

import java.time.LocalDateTime;
@Entity
@Table(name = "user_account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "user_name" , nullable = false , length = 35 , unique = true)
    private String userName;

    @Column(name = "user_password" , nullable = false)
    private String userPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "permission", nullable = false)
    private Permission permission;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "reset_token", length = 6)
    private String resetToken;

    @Column(name = "reset_token_expiry")
    private LocalDateTime resetTokenExpiry;

    public User(String userName, String userPassword, Permission permission, String email) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.permission = permission;
        this.email = email;
    }

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public boolean isEqualPassword(String senha){
        return senha.equals(this.userPassword);
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getResetToken() { return resetToken; }
    public void setResetToken(String resetToken) { this.resetToken = resetToken; }

    public LocalDateTime getResetTokenExpiry() { return resetTokenExpiry; }
    public void setResetTokenExpiry(LocalDateTime resetTokenExpiry) { this.resetTokenExpiry = resetTokenExpiry; }
}
