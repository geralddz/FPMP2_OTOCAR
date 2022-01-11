package com.example.otocar.Data.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.otocar.Data.DAO.UserDAO;
import com.example.otocar.Data.Model.UserEntity;

@Database(entities = {UserEntity.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDAO userDAO();
}