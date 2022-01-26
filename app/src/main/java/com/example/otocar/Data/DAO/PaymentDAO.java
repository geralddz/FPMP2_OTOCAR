package com.example.otocar.Data.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.otocar.Data.Model.Payment;

import java.util.List;

@Dao
public interface PaymentDAO {
    @Insert
    public void tambah(Payment payment);

    @Update
    public void update(Payment payment);

    @Delete
    public void delete(Payment payment);

    @Query("SELECT * FROM Payment")
    public List<Payment> getPayment();

    @Query("SELECT * FROM Payment WHERE pid LIKE :PID LIMIT 1")
    Payment findById(int PID);


}
