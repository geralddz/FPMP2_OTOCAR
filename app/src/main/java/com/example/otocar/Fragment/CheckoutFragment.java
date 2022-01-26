package com.example.otocar.Fragment;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.otocar.Data.Database.MyApp;
import com.example.otocar.Data.Model.Payment;
import com.example.otocar.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckoutFragment extends Fragment {

    private EditText innama, inalamat, innope;
    private Spinner inbayar;
    private Button cekout;
    SharedPreferences preferences;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CheckoutFragment() {
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
    public static CheckoutFragment newInstance(String param1, String param2) {
        CheckoutFragment fragment = new CheckoutFragment();
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
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        String [] values =
                {"Bank BCA","Bank BNI","Bank Mandiri","Bank BRI","Bank BTN","Bank Muamalat","Bank Jenius",};
        Spinner spinner = view.findViewById(R.id.spiner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.item_spinner, values);
        adapter.setDropDownViewResource(R.layout.dropdown);
        spinner.setAdapter(adapter);

        preferences = this.getActivity().getSharedPreferences("User", 0);
        TextView mobil1 = view.findViewById(R.id.mobil);
        mobil1.setText(preferences.getString("judul",""));

        TextView price1 = view.findViewById(R.id.price);
        price1.setText(preferences.getString("harga",""));

        innama = view.findViewById(R.id.etnama);
        inalamat = view.findViewById(R.id.etalamat);
        innope = view.findViewById(R.id.etnohp);
        inbayar = view.findViewById(R.id.spiner);
        cekout = view.findViewById(R.id.btcekout);

        innama.setText(preferences.getString("Nama",""),TextView.BufferType.EDITABLE);
        inalamat.setText(preferences.getString("Alamat", ""),TextView.BufferType.EDITABLE);
        innope.setText(preferences.getString("No. HP",""),TextView.BufferType.EDITABLE);

        cekout.setOnClickListener(v -> {
            String nama = innama.getText().toString();
            String alamat = inalamat.getText().toString();
            String nope = innope.getText().toString();
            String bayar = inbayar.getSelectedItem().toString();

            Payment payment = new Payment(nama, alamat,nope, mParam1, mParam2, bayar);
            payment.setNama(nama);
            payment.setAlamat(alamat);
            payment.setNope(nope);
            payment.setMobil(mParam1);
            payment.setPrice(mParam2);
            payment.setBayar(bayar);

            if (nama.length() == 0 ) {
                innama.setError("Nama Tidak Boleh Kosong");
            }
            else if (alamat.length() == 0) {
                inalamat.setError("Alamat Tidak Boleh Kosong");
            }
            else if (nope.length() == 0){
                innope.setError("No. HP Tidak Boleh Kosong");
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(cekout.getContext());
                builder.setTitle("Checkout")
                        .setMessage("Apakah data diri anda sudah Benar ?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            MyApp.db.paymentDAO().tambah(payment);
                            Toast.makeText(getActivity(), "Checkout Berhasil", Toast.LENGTH_SHORT).show();
                            innama.setText("");
                            inalamat.setText("");
                            innope.setText("");
                            mobil1.setText("");
                            price1.setText("");
                            inbayar.setSelection(0);
                            HistoryFragment fragment = new HistoryFragment();
                            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                        }).setNegativeButton("No", (dialog, which) -> dialog.cancel()).show();
            }

        });

        return view;


    }
}