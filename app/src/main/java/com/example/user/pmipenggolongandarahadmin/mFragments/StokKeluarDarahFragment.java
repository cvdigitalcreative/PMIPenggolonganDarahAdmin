package com.example.user.pmipenggolongandarahadmin.mFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.pmipenggolongandarahadmin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StokKeluarDarahFragment extends Fragment {


    public StokKeluarDarahFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stok_keluar_darah, container, false);

        getFragmentManager().beginTransaction().replace(R.id.darahKeluar_Container, new StokDarahKeluarFragment()).commit();
        return view;
    }

}
