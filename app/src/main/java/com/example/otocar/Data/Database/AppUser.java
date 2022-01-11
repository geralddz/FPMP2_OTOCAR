package com.example.otocar.Data.Database;

import android.app.Application;

import androidx.room.Room;

public class AppUser extends Application {

    private static AppUser INSTANCE;
    public static UserDatabase db;

    public static AppUser getInstance(){
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        db = Room.databaseBuilder(this,
                UserDatabase.class,"database_user")
                .allowMainThreadQueries().build();
        INSTANCE = this;
    }

    public UserDatabase getDb(){
        return db;
    }

}
