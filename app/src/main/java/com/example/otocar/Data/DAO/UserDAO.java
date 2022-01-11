package com.example.otocar.Data.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.otocar.Data.Model.UserEntity;

@Dao
public interface UserDAO  {

    @Insert
    void Register(UserEntity users);

    @Query("SELECT * FROM users WHERE username=(:user) and password=(:pass)")
    UserEntity login(String user, String pass);


}
