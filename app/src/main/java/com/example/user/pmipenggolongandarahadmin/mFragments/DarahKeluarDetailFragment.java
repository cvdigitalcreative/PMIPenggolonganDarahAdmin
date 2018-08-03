package com.example.user.pmipenggolongandarahadmin.mFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.pmipenggolongandarahadmin.R;
import com.example.user.pmipenggolongandarahadmin.mAdapters.StokKeluarAdapter;
import com.example.user.pmipenggolongandarahadmin.mAdapters.stokKeluarDetailAdapter;
import com.example.user.pmipenggolongandarahadmin.mDatas.StokDarah;
import com.example.user.pmipenggolongandarahadmin.mDatas.StokDarahKeluar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DarahKeluarDetailFragment extends Fragment {
    private DatabaseReference reference;
    private StokDarah stokDarah;
    private List<StokDarahKeluar> stokDarahList;
    private stokKeluarDetailAdapter stokDetailAdapter;
    private ListView darahKeluarDetail;
    private String golonganDarah;

    public DarahKeluarDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_darah_keluar_detail, container, false);

        darahKeluarDetail = view.findViewById(R.id.lv_darahKeluarDetail);

        golonganDarah = getArguments().getString("gol_dar");
        reference = FirebaseDatabase.getInstance().getReference("stok_darah");

        stokDarahList = new ArrayList<>();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                stokDarahList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //Toast.makeText(getActivity().getApplicationContext(), golonganDarah, Toast.LENGTH_SHORT).show();
                    if(snapshot.child("golongan_darah").getValue().equals(golonganDarah)){
                        for(DataSnapshot darahKeluarSnapshot : snapshot.child("ambil_stok_pendonor").getChildren()){
                            StokDarahKeluar stokDarahKeluar = darahKeluarSnapshot.getValue(StokDarahKeluar.class);

                            stokDarahList.add(stokDarahKeluar);
                        }
                    }
                }

                stokDetailAdapter = new stokKeluarDetailAdapter(getContext(), stokDarahList);
                darahKeluarDetail.setAdapter(stokDetailAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
