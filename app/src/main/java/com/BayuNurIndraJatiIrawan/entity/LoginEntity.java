package com.BayuNurIndraJatiIrawan.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "login_table")
public class LoginEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private String password;

    public LoginEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
