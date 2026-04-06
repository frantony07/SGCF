package org.ONE.models;

import jakarta.persistence.*;
import org.ONE.models.ENUM.Permission;

@Entity(name = "user_account")
public class User {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(name = "user_name" , nullable = false , length = 35 , unique = true)
    private String userName;

    @Column(name = "user_password" , nullable = false , length = 35)
    private String userPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "permission", nullable = false)
    private Permission permission;

    public User(String userName, String userPassword, Permission permission) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.permission = permission;
    }

    public User() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
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
}
