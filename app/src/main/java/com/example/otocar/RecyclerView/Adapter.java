package com.example.otocar.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otocar.Data.Model.DataMobil;
import com.example.otocar.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder> implements View.OnClickListener {

    ArrayList<DataMobil> dataholder;
    ItemClicklistener clickListener;

    public Adapter(ArrayList<DataMobil> dataholder, ItemClicklistener clickListener) {
        this.dataholder = dataholder;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_mobil,parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.img.setImageResource(dataholder.get(position).getImage());
        holder.judul.setText(dataholder.get(position).getJudul());
        holder.harga.setText(dataholder.get(position).getHarga());


        holder.itemView.setOnClickListener(v -> {
            clickListener.onItemClick(dataholder.get(position));

        });
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }


    @Override
    public void onClick(View v) {}

    class viewholder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView judul, harga;

        public viewholder (@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            judul = itemView.findViewById(R.id.judul);
            harga = itemView.findViewById(R.id.harga);
        }
    }
    public interface ItemClicklistener{
       void onItemClick(DataMobil dataMobil);
    }
}
