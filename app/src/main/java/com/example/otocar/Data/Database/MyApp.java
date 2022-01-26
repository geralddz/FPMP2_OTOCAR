package com.example.otocar.Data.Database;

import android.app.Application;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class MyApp extends Application {

    private static MyApp INSTANCE;
    public static MyDatabase db;

    public static MyApp getInstance(){
        return INSTANCE;
    }

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS 'Payment' ('pid' INTEGER NOT NULL, 'Nama' TEXT,'Alamat' TEXT, 'No HP' TEXT, 'Mobil' TEXT, 'Harga' TEXT, 'Pembayaran' TEXT, PRIMARY KEY('pid'))");
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        db = Room.databaseBuilder(this,
                MyDatabase.class,"database_user")
                .addMigrations(MIGRATION_1_2)
                .allowMainThreadQueries()
                .build();
        INSTANCE = this;
    }

    public MyDatabase getDb(){
        return db;
    }

}
