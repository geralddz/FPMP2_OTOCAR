package com.example.otocar.Data.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.otocar.Data.Model.Payment;

import java.util.List;

@Dao
public interface PaymentDAO {
    @Insert
    public void tambah(Payment payment);

    @Query("SELECT * FROM Payment")
    public List<Payment> getPayment();

}
