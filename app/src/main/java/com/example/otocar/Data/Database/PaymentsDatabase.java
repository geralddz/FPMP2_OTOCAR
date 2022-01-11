package com.example.otocar.Data.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.otocar.Data.DAO.PaymentDAO;
import com.example.otocar.Data.Model.Payment;


@Database(entities = {Payment.class},version = 1)
public abstract class PaymentsDatabase extends RoomDatabase {

    public abstract PaymentDAO paymentDAO();

}
