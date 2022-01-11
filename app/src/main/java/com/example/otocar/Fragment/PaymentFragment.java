package com.example.otocar.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.otocar.Data.Model.Payment;
import com.example.otocar.Home;
import com.example.otocar.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentFragment extends Fragment {

    private EditText inid, innama, inalamat, innope, inmobil, inharga, inbayar;
    private Button cekout;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentFragment newInstance(String param1, String param2) {
        PaymentFragment fragment = new PaymentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        TextView mobil1 = view.findViewById(R.id.mobil);
        mobil1.setText(mParam1);

        TextView price1 = view.findViewById(R.id.price);
        price1.setText(mParam2);

        innama = view.findViewById(R.id.etnama);
        inalamat = view.findViewById(R.id.etalamat);
        innope = view.findViewById(R.id.etnohp);
        inmobil = view.findViewById(R.id.etmobil);
        inharga = view.findViewById(R.id.etharga);
        inbayar = view.findViewById(R.id.etbayar);
        cekout = view.findViewById(R.id.btcekout);

        cekout.setOnClickListener(v -> {
            String nama = innama.getText().toString();
            String alamat = inalamat.getText().toString();
            String nope = innope.getText().toString();
            String mobil = inmobil.getText().toString();
            String harga = inharga.getText().toString();
            String bayar = inbayar.getText().toString();

            Payment payment = new Payment(nama, alamat,nope, mobil, harga, bayar);
            payment.setNama(nama);
            payment.setAlamat(alamat);
            payment.setNope(nope);
            payment.setMobil(mobil);
            payment.setPrice(harga);
            payment.setBayar(bayar);

            Home.payDAO.tambah(payment);
            Toast.makeText(getActivity(), "Checkout Berhasil", Toast.LENGTH_SHORT).show();
            innama.setText("");
            inalamat.setText("");
            innope.setText("");
            inmobil.setText("");
            inharga.setText("");
            inbayar.setText("");

            HistoryFragment fragment = new HistoryFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();


        });

        return view;


    }
}