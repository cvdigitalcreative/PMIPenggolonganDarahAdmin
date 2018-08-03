package com.example.user.pmipenggolongandarahadmin.mAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.pmipenggolongandarahadmin.R;
import com.example.user.pmipenggolongandarahadmin.mDatas.PendonorDarah;
import com.example.user.pmipenggolongandarahadmin.mDatas.StokDarah;

import java.util.List;

/**
 * Created by USER on 13/09/2017.
 */

public class StokPendonorListAdapter extends BaseAdapter{
    private Context context;
    private List<PendonorDarah> pendonorList;

    public StokPendonorListAdapter(Context context, List<PendonorDarah> pendonorList) {
        this.context = context;
        this.pendonorList = pendonorList;
    }


    @Override
    public int getCount() {
        return pendonorList.size();
    }

    @Override
    public Object getItem(int i) {
        return pendonorList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PendonorDarah pendonorDarah = (PendonorDarah) getItem(i);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_stok_pendonor, viewGroup, false);
        }

        TextView nama = view.findViewById(R.id.stok_nama);
        TextView gol = view.findViewById(R.id.stok_gol);
        TextView alm = view.findViewById(R.id.stok_alamat);
        TextView no_hp = view.findViewById(R.id.stok_no_hp);
        TextView tgl = view.findViewById(R.id.stok_tanggal);

        nama.setText(pendonorDarah.getNama());
        gol.setText(pendonorDarah.getGol_darah());
        alm.setText(pendonorDarah.getAlamat());
        no_hp.setText(pendonorDarah.getNo_hp());
        tgl.setText(pendonorDarah.getTanggal_daftar());

        return view;
    }
}
