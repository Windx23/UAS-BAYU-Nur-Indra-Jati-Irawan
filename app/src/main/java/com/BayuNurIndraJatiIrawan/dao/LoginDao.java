package com.BayuNurIndraJatiIrawan.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.BayuNurIndraJatiIrawan.entity.LoginEntity;

import java.util.List;

@Dao
public interface LoginDao {
    @Insert
    void insert(LoginEntity loginEntity);

    @Query("SELECT * FROM login_table")
    List<LoginEntity> getAllLogins();

    @Query("SELECT * FROM login_table WHERE username = :username LIMIT 1")
    LoginEntity findByUsername(String username);

    @Delete
    void delete(LoginEntity loginEntity);
}