package com.example.user.pmipenggolongandarahadmin.mAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.pmipenggolongandarahadmin.R;
import com.example.user.pmipenggolongandarahadmin.mDatas.PendonorDarah;
import com.example.user.pmipenggolongandarahadmin.mDatas.StokDarah;

import java.util.List;

/**
 * Created by USER on 16/09/2017.
 */

public class stokMasukDetailAdapter extends BaseAdapter {
    private Context context;
    private List<PendonorDarah> pendonorDarahs;

    public stokMasukDetailAdapter(Context context, List<PendonorDarah> pendonorDarahs) {
        this.context = context;
        this.pendonorDarahs = pendonorDarahs;
    }

    @Override
    public int getCount() {
        return pendonorDarahs.size();
    }

    @Override
    public Object getItem(int i) {
        return pendonorDarahs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PendonorDarah pendonorDarah = (PendonorDarah)getItem(i);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_darah_masuk_detail, viewGroup, false);

            TextView nama = view.findViewById(R.id.stok_nama);
            TextView gol = view.findViewById(R.id.stok_gol);
            TextView alm = view.findViewById(R.id.stok_alamat);
            TextView no_hp = view.findViewById(R.id.stok_no_hp);
            TextView tgl = view.findViewById(R.id.stok_tanggal);

            nama.setText("Nama Pendonor : "+pendonorDarah.getNama());
            gol.setText("Golongan Darah : "+pendonorDarah.getGol_darah());
            alm.setText("Alamat : "+pendonorDarah.getAlamat());
            no_hp.setText("No HP : "+pendonorDarah.getNo_hp());
            tgl.setText("Tanggal : "+pendonorDarah.getTanggal_daftar());
        }

        return view;
    }
}
