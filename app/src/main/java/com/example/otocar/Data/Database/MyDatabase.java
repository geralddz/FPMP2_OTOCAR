package com.example.otocar.Data.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.otocar.Data.DAO.PaymentDAO;
import com.example.otocar.Data.DAO.UserDAO;
import com.example.otocar.Data.Model.Payment;
import com.example.otocar.Data.Model.UserEntity;

@Database(entities = {UserEntity.class, Payment.class},version = 2)
public abstract class MyDatabase extends RoomDatabase {

    public abstract UserDAO userDAO();
    public abstract PaymentDAO paymentDAO();
}