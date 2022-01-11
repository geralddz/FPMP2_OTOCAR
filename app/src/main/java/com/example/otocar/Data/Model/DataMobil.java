package com.example.otocar.Data.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Mobil")
public class DataMobil {

    @PrimaryKey(autoGenerate = true)
    private int mid = 0;

    @ColumnInfo(name = "gambarmobil")
    private int image;

    @ColumnInfo(name = "namamobil")
    private String judul;

    @ColumnInfo(name = "hargamobil")
    private String harga;

    public DataMobil(int image, String judul, String harga) {
        this.image = image;
        this.judul = judul;
        this.harga = harga;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
