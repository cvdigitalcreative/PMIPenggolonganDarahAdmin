package com.example.user.pmipenggolongandarahadmin.mAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.pmipenggolongandarahadmin.R;
import com.example.user.pmipenggolongandarahadmin.mDatas.PendonorDarah;

import java.util.List;

/**
 * Created by USER on 15/08/2017.
 */

public class TambahPendonorListAdapater extends BaseAdapter {
    private Context context;
    private List<PendonorDarah> pendonorDarahList;

    public TambahPendonorListAdapater(Context context, List<PendonorDarah> pendonorDarahList) {
        this.context = context;
        this.pendonorDarahList = pendonorDarahList;
    }

    @Override
    public int getCount() {
        return pendonorDarahList.size();
    }

    @Override
    public Object getItem(int i) {
        return pendonorDarahList.get(i);
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
            view = inflater.inflate(R.layout.list_tambah_pendonor, viewGroup, false);
        }

        TextView nama = (TextView)view.findViewById(R.id.nama);
        TextView alamat = (TextView)view.findViewById(R.id.alamat);
        TextView golDarah = (TextView)view.findViewById(R.id.gol_darah);
        TextView jenisKelamin = (TextView)view.findViewById(R.id.jenis_kelamin);
        TextView Pekerjaan = (TextView)view.findViewById(R.id.pekerjaan);
        TextView noHp = (TextView)view.findViewById(R.id.no_hp);
        TextView tanggalLahir = (TextView)view.findViewById(R.id.tanggal_lahir);
        TextView beratBadan = (TextView)view.findViewById(R.id.berat_badan);
        TextView tinggiBadan = (TextView)view.findViewById(R.id.tinggi_badan);

        nama.setText(pendonorDarah.getNama());
        alamat.setText(pendonorDarah.getAlamat());
        golDarah.setText(pendonorDarah.getGol_darah());
        jenisKelamin.setText(pendonorDarah.getJenis_kelamin());
        Pekerjaan.setText(pendonorDarah.getPekerjaan());
        noHp.setText(pendonorDarah.getNo_hp());
        tanggalLahir.setText(pendonorDarah.getTanggal_lahir());
        beratBadan.setText(pendonorDarah.getBerat_badan());
        tinggiBadan.setText(pendonorDarah.getTinggi_badan());

        return view;
    }
}
