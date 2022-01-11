package com.example.otocar.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otocar.Data.Model.Payment;
import com.example.otocar.R;

import java.util.List;

public class Adapterpay extends RecyclerView.Adapter<Adapterpay.viewholder> implements View.OnClickListener {

    List<Payment> list;

    public Adapterpay(List<Payment> list) {
        this.list = list;
    }


    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public Adapterpay.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_transaksi,parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterpay.viewholder holder, int position) {
        holder.nama.setText(list.get(position).getNama());
        holder.alamat.setText(list.get(position).getAlamat());
        holder.nope.setText(list.get(position).getNope());
        holder.mobil.setText(list.get(position).getMobil());
        holder.price.setText(list.get(position).getPrice());
        holder.bayar.setText(list.get(position).getBayar());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView nama, alamat, nope, mobil, price, bayar;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tvnama);
            alamat = itemView.findViewById(R.id.tvalamat);
            nope = itemView.findViewById(R.id.tvnope);
            mobil = itemView.findViewById(R.id.tvmobil);
            price = itemView.findViewById(R.id.tvharga);
            bayar = itemView.findViewById(R.id.tvbayar);
        }
    }
}
