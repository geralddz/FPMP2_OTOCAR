package com.example.otocar.RecyclerView;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otocar.Data.Database.MyApp;
import com.example.otocar.Data.Model.Payment;
import com.example.otocar.R;

import java.util.List;

public class Adapterpay extends RecyclerView.Adapter<Adapterpay.viewholder> implements View.OnClickListener {

    private List<Payment> dataList;
    private ItemClicklistener listener;

    public void setData(List<Payment> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            Payment data = dataList.get(i);
            int position = findPosition(data);
            if (position == -1) {
                this.dataList.add(data);
                notifyItemInserted(this.dataList.size() - 1);
            } else {
                this.dataList.remove(position);
                this.dataList.add(position, data);
                notifyItemChanged(position);
            }
        }
    }

    private int findPosition(Payment data) {
        int position = -1;
        if (!this.dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                if (this.dataList.get(i).getPid() == data.getPid()) {
                    position = i;
                }
            }
        }

        return position;
    }
    public void removeData(Payment data) {
        if (this.dataList.isEmpty()) {
            return;
        }

        for (int i = 0; i < dataList.size(); i++) {
            if (this.dataList.get(i).getPid() == data.getPid()) {
                this.dataList.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    public Adapterpay(List<Payment> list, ItemClicklistener listener) {
        this.dataList = list;
        this.listener = listener;
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
        holder.bind(dataList.get(position),listener);

        holder.itemView.setOnClickListener(v -> {
           listener.onItemClick(dataList.get(position));

        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface ItemClicklistener {
        void onItemClick(Payment payment);
    }

    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nama, alamat, nope, mobil, price, bayar;
        private ImageView hapus;
        private Payment payment;
        private ItemClicklistener listener;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tvnama);
            alamat = itemView.findViewById(R.id.tvalamat);
            nope = itemView.findViewById(R.id.tvnope);
            mobil = itemView.findViewById(R.id.tvmobil);
            price = itemView.findViewById(R.id.tvharga);
            bayar = itemView.findViewById(R.id.tvbayar);
            hapus = itemView.findViewById(R.id.btn_hapus);

            hapus.setOnClickListener(this);

        }

        public void bind(Payment payment, ItemClicklistener listener) {
            this.payment = payment;
            this.listener = listener;

            nama.setText(payment.getNama());
            alamat.setText(payment.getAlamat());
            nope.setText(payment.getNope());
            mobil.setText(payment.getMobil());
            price.setText(payment.getPrice());
            bayar.setText(payment.getBayar());
        }

        @Override
        public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(hapus.getContext());
                builder.setTitle("Peringatan !!! ")
                        .setMessage("Apakah Anda Ingin Menghapus Data Ini ? ")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            MyApp.getInstance().getDb().paymentDAO().delete(payment);
                            int ID = dataList.get(getAdapterPosition()).getPid();
                            payment.setPid(ID);
                            dataList.remove(getPosition());
                            notifyDataSetChanged();
                            Toast.makeText(itemView.getContext(), "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                        }).setNegativeButton("No", (dialog, which) -> dialog.cancel()).show();

        }
    }
}
