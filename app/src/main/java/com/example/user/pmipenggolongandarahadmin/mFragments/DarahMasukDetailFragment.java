package com.example.user.pmipenggolongandarahadmin.mFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.user.pmipenggolongandarahadmin.R;
import com.example.user.pmipenggolongandarahadmin.mAdapters.stokKeluarDetailAdapter;
import com.example.user.pmipenggolongandarahadmin.mAdapters.stokMasukDetailAdapter;
import com.example.user.pmipenggolongandarahadmin.mDatas.PendonorDarah;
import com.example.user.pmipenggolongandarahadmin.mDatas.StokDarah;
import com.example.user.pmipenggolongandarahadmin.mDatas.StokDarahKeluar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DarahMasukDetailFragment extends Fragment {
    private DatabaseReference reference;
    private StokDarah stokDarah;
    private List<PendonorDarah> pendonorDarahList;
    private stokMasukDetailAdapter stokDetailAdapter;
    private ListView darahMasukDetail;
    private String golonganDarah;

    public DarahMasukDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_darah_masuk_detail, container, false);

        darahMasukDetail = view.findViewById(R.id.lv_darahMasukDetail);
        golonganDarah = getArguments().getString("gol_dar");
        reference = FirebaseDatabase.getInstance().getReference("stok_darah");

        pendonorDarahList = new ArrayList<>();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pendonorDarahList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(snapshot.child("golongan_darah").getValue().equals(golonganDarah)){
                        for(DataSnapshot darahKeluarSnapshot : snapshot.child("tambah_stok_pendonor").getChildren()){
                            PendonorDarah pendonorDarah = darahKeluarSnapshot.getValue(PendonorDarah.class);

                            pendonorDarahList.add(pendonorDarah);
                        }
                    }
                }
                stokDetailAdapter = new stokMasukDetailAdapter(getContext(), pendonorDarahList);
                darahMasukDetail.setAdapter(stokDetailAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

}
