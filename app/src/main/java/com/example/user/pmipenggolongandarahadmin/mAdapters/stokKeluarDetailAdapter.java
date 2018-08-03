package com.example.user.pmipenggolongandarahadmin.mAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.pmipenggolongandarahadmin.R;
import com.example.user.pmipenggolongandarahadmin.mDatas.StokDarah;
import com.example.user.pmipenggolongandarahadmin.mDatas.StokDarahKeluar;

import java.util.List;

/**
 * Created by USER on 16/09/2017.
 */

public class stokKeluarDetailAdapter extends BaseAdapter {
    private Context context;
    private List<StokDarahKeluar> stokDarahList;

    public stokKeluarDetailAdapter(Context context, List<StokDarahKeluar> stokDarahList) {
        this.context = context;
        this.stokDarahList = stokDarahList;
    }

    @Override
    public int getCount() {
        return stokDarahList.size();
    }

    @Override
    public Object getItem(int i) {
        return stokDarahList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        StokDarahKeluar stokDarah = (StokDarahKeluar) getItem(i);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_darah_keluar_detail, viewGroup, false);

            TextView tvNama = view.findViewById(R.id.tv_namaKeluarDetail);
            TextView tvUmur = view.findViewById(R.id.tv_umurKeluarDetail);
            TextView tvHarga = view.findViewById(R.id.tv_hargaKeluarDetail);

            tvNama.setText("Nama : "+stokDarah.getNama());
            tvUmur.setText("Umur : "+stokDarah.getUmur());
            tvHarga.setText("Harga : "+stokDarah.getHarga());
        }

        return view;
    }
}
