package com.example.user.pmipenggolongandarahadmin.mFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.pmipenggolongandarahadmin.R;

public class StokMasukDarahFragment extends Fragment {
    public StokMasukDarahFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stok_masuk_darah, container, false);

        getFragmentManager().beginTransaction().replace(R.id.darahMasuk_Container, new StokDarahMasukFragment()).commit();

        return view;
    }
}
