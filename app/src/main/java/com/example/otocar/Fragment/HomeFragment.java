package com.example.otocar.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.otocar.RecyclerView.Adapter;
import com.example.otocar.Data.Model.DataMobil;
import com.example.otocar.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements Adapter.ItemClicklistener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<DataMobil> dataholder;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder = new ArrayList<>();

        DataMobil ob1 = new DataMobil(R.drawable.fortuner,"Toyota Fortuner","Rp 521.600.000,-");
        dataholder.add(ob1);

        DataMobil ob2 = new DataMobil(R.drawable.avanza,"Toyota Avanza","Rp 228.300.000,-");
        dataholder.add(ob2);

        DataMobil ob3 = new DataMobil(R.drawable.inova,"Toyota Innova","Rp 355.400.000,-");
        dataholder.add(ob3);

        DataMobil ob4 = new DataMobil(R.drawable.sienta,"Toyota Sienta","Rp 260.100.000,-");
        dataholder.add(ob4);

        DataMobil ob5 = new DataMobil(R.drawable.rush,"Toyota Rush","Rp 317.500.000,-");
        dataholder.add(ob5);

        DataMobil ob6 = new DataMobil(R.drawable.jazz,"Honda Jazz","Rp 284.700.000,-");
        dataholder.add(ob6);

        DataMobil ob7 = new DataMobil(R.drawable.rangerover,"Range Rover","Rp 1.544.000.000,-");
        dataholder.add(ob7);

        recyclerView.setAdapter(new Adapter(dataholder,this));

        return view;
    }

    @Override
    public void onItemClick(DataMobil dataMobil) {

        Fragment fragment = PaymentFragment.newInstance(dataMobil.getJudul(), dataMobil.getHarga());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, "payment_fragment");
        transaction.addToBackStack(null);
        transaction.commit();

    }
}