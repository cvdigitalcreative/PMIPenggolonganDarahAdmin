package com.example.user.pmipenggolongandarahadmin.mFragments;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.pmipenggolongandarahadmin.R;
import com.example.user.pmipenggolongandarahadmin.mAdapters.StokListAdapter;
import com.example.user.pmipenggolongandarahadmin.mAdapters.StokPendonorListAdapter;
import com.example.user.pmipenggolongandarahadmin.mDatas.PendonorDarah;
import com.example.user.pmipenggolongandarahadmin.mDatas.StokDarah;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GolPendonorFragment extends Fragment {
    private ListView lvStok;
    private List<PendonorDarah> stokPendonorList;
    private StokPendonorListAdapter stokPendonorListAdapter;
    private PendonorDarah pendonorDarah;
    private DatabaseReference reference;
    private String golongan_darah;

    public GolPendonorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gol_pendonor, container, false);

        lvStok = (ListView)view.findViewById(R.id.lvStokPendonor);
        golongan_darah = getArguments().getString("golDar");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    private void initData() {
        stokPendonorList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("pendonor_darah");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                stokPendonorList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(snapshot.child("gol_darah").getValue().equals(golongan_darah)){
                        pendonorDarah = snapshot.getValue(PendonorDarah.class);
                        stokPendonorList.add(pendonorDarah);
                    }
                }

                stokPendonorListAdapter = new StokPendonorListAdapter(getContext(), stokPendonorList);
                lvStok.setAdapter(stokPendonorListAdapter);

                lvStok.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TextView nama = view.findViewById(R.id.stok_nama);
                        TextView gol = view.findViewById(R.id.stok_gol);

                        final String stok_nama = nama.getText().toString();
                        final String stok_gol = gol.getText().toString();

                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                        View mView = layoutInflater.inflate(R.layout.stok_darah_keluar, null);

                        Button btnYa= mView.findViewById(R.id.btn_Ya);
                        Button btnTidak = mView.findViewById(R.id.btn_Tidak);
                        final EditText nama_edit = mView.findViewById(R.id.et_nama);
                        final EditText umur_edit = mView.findViewById(R.id.et_umur);
                        final EditText harga_edit = mView.findViewById(R.id.et_harga);

                        mBuilder.setView(mView);
                        final AlertDialog dialog = mBuilder.create();

                        dialog.show();
                        dialog.setCanceledOnTouchOutside(false);

                        btnYa.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final String nama_text = nama_edit.getText().toString();
                                final String umur_text = umur_edit.getText().toString();
                                final String harga_text = harga_edit.getText().toString();

                                final DatabaseReference reference;

                                reference = FirebaseDatabase.getInstance().getReference();

                                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for(DataSnapshot snapshot : dataSnapshot.child("pendonor_darah").getChildren()){
                                            String key = snapshot.getKey();
                                            if(snapshot.child("nama").getValue().equals(stok_nama) && snapshot.child("gol_darah").getValue().equals(stok_gol)){
                                                PendonorDarah pendonorDarah = snapshot.getValue(PendonorDarah.class);
                                                reference.child("pendonor_darah").child(key).getRef().removeValue();

                                                for(DataSnapshot stokSnapshot : dataSnapshot.child("stok_darah").getChildren()){
                                                    String keyAmbil = stokSnapshot.getRef().push().getKey();

                                                    if(stok_gol.equals(stokSnapshot.child("golongan_darah").getValue())){
                                                        int jumlah = Integer.parseInt(stokSnapshot.child("stok_darahkeluar").getValue(String.class))+1;
                                                        int kurang = Integer.parseInt(stokSnapshot.child("jumlah").getValue(String.class))-1;

                                                        stokSnapshot.child("ambil_stok_pendonor").child(keyAmbil).child("nama").getRef().setValue(nama_text);
                                                        stokSnapshot.child("ambil_stok_pendonor").child(keyAmbil).child("umur").getRef().setValue(umur_text);
                                                        stokSnapshot.child("ambil_stok_pendonor").child(keyAmbil).child("harga_darah").getRef().setValue(harga_text);
                                                        stokSnapshot.child("stok_darahkeluar").getRef().setValue(String.valueOf(jumlah));
                                                        stokSnapshot.child("jumlah").getRef().setValue(String.valueOf(kurang));

                                                    }
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                dialog.dismiss();
                            }
                        });

                        btnTidak.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
