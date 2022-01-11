package com.example.otocar.Data.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Payment")
public class Payment {

        @PrimaryKey (autoGenerate = true)
        @NonNull
        private int pid = 0;

        @ColumnInfo(name = "Nama")
        private String nama;

        @ColumnInfo(name = "Alamat")
        private String alamat;

        @ColumnInfo(name = "No HP")
        private String nope;

        @ColumnInfo(name = "Mobil")
        private String mobil;

        @ColumnInfo(name = "Harga")
        private String price;

        @ColumnInfo(name = "Pembayaran")
        private String bayar;

        public Payment( String nama, String alamat, String nope, String mobil, String price, String bayar) {
            this.nama = nama;
            this.alamat = alamat;
            this.nope = nope;
            this.mobil = mobil;
            this.price = price;
            this.bayar = bayar;
        }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getNope() {
            return nope;
        }

        public void setNope(String nope) {
            this.nope = nope;
        }

        public String getMobil() {
            return mobil;
        }

        public void setMobil(String mobil) {
            this.mobil = mobil;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getBayar() {
            return bayar;
        }

        public void setBayar(String bayar) {
            this.bayar = bayar;
        }
}

