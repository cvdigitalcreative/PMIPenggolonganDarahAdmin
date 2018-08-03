package com.example.user.pmipenggolongandarahadmin.mFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.pmipenggolongandarahadmin.R;
import com.example.user.pmipenggolongandarahadmin.mAdapters.LaporanAdapter;
import com.example.user.pmipenggolongandarahadmin.mAdapters.StokKeluarAdapter;
import com.example.user.pmipenggolongandarahadmin.mAdapters.StokMasukAdapter;
import com.example.user.pmipenggolongandarahadmin.mDatas.StokDarah;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StokDarahMasukFragment extends Fragment {
    private DatabaseReference reference;
    private StokDarah stokDarah;
    private List<StokDarah> stokDarahList;
    private StokMasukAdapter stokMasukAdapter;
    private ListView darahMasuk;

    public StokDarahMasukFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stok_darah_masuk, container, false);

        final TextView tvTanggal = view.findViewById(R.id.tv_tanggal);
        final TextView tvJumlah = view.findViewById(R.id.tv_Jumlah);
        darahMasuk = view.findViewById(R.id.lv_darahMasuk);

        reference = FirebaseDatabase.getInstance().getReference("stok_darah/");

        stokDarahList = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int jumlah = 0;
                stokDarahList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    stokDarah = snapshot.getValue(StokDarah.class);

                    stokDarahList.add(stokDarah);

                    if("A-".equals(stokDarah.getGolongan_darah())){
                        String golDarAmin = stokDarah.getStok_darahmasuk();
                        int Amin = Integer.parseInt(golDarAmin);
                        jumlah = jumlah + Amin;
                    }
                    if ("A+".equals(stokDarah.getGolongan_darah())){
                        String golDarAplus = stokDarah.getStok_darahmasuk();
                        int Aplus = Integer.parseInt(golDarAplus);
                        jumlah = jumlah + Aplus;
                    }
                    if ("B-".equals(stokDarah.getGolongan_darah())){
                        String golDarBmin = stokDarah.getStok_darahmasuk();
                        int Bmin = Integer.parseInt(golDarBmin);
                        jumlah = jumlah + Bmin;
                    }
                    if ("B+".equals(stokDarah.getGolongan_darah())){
                        String golDarBplus = stokDarah.getStok_darahmasuk();
                        int Bplus = Integer.parseInt(golDarBplus);
                        jumlah = jumlah + Bplus;
                    }
                    if ("AB-".equals(stokDarah.getGolongan_darah())){
                        String golDarABmin = stokDarah.getStok_darahmasuk();
                        int ABmin = Integer.parseInt(golDarABmin);
                        jumlah = jumlah + ABmin;
                    }
                    if ("AB+".equals(stokDarah.getGolongan_darah())){
                        String golDarABplus = stokDarah.getStok_darahmasuk();
                        int ABplus = Integer.parseInt(golDarABplus);
                        jumlah = jumlah + ABplus;
                    }
                    if ("O-".equals(stokDarah.getGolongan_darah())){
                        String golDarOmin = stokDarah.getStok_darahmasuk();
                        int Omin = Integer.parseInt(golDarOmin);
                        jumlah = jumlah + Omin;
                    }
                    if ("O+".equals(stokDarah.getGolongan_darah())){
                        String golDarOplus = stokDarah.getStok_darahmasuk();
                        int Oplus = Integer.parseInt(golDarOplus);
                        jumlah = jumlah + Oplus;
                    }
                }

                stokMasukAdapter = new StokMasukAdapter(getContext(), stokDarahList);
                darahMasuk.setAdapter(stokMasukAdapter);

                darahMasuk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TextView tvGol = view.findViewById(R.id.tv_gol_darah);
                        String golDar = tvGol.getText().toString();

                        Bundle bundle = new Bundle();
                        bundle.putString("gol_dar", golDar);

                        DarahMasukDetailFragment stokMasukDetail = new DarahMasukDetailFragment();
                        stokMasukDetail.setArguments(bundle);
                        getFragmentManager().beginTransaction().replace(R.id.darahMasuk_Container, stokMasukDetail).commit();
                    }
                });

                String Currentdate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                tvTanggal.setText(Currentdate);
                tvJumlah.setText(String.valueOf(jumlah));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;

    }
}
