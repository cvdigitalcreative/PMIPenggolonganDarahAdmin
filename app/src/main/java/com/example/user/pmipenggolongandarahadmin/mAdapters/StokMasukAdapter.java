package com.example.user.pmipenggolongandarahadmin.mAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.pmipenggolongandarahadmin.R;
import com.example.user.pmipenggolongandarahadmin.mDatas.StokDarah;

import java.util.List;

/**
 * Created by USER on 16/09/2017.
 */

public class StokMasukAdapter extends BaseAdapter{
    private Context context;
    private List<StokDarah> stokDarahList;

    public StokMasukAdapter(Context context, List<StokDarah> stokDarahList) {
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
        StokDarah stokDarah = (StokDarah)getItem(i);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_darah_masuk, viewGroup, false);

            TextView tvGol = view.findViewById(R.id.tv_gol);
            TextView tvGol_Dar = view.findViewById(R.id.tv_gol_darah);

            tvGol.setText("Gol darah "+stokDarah.getGolongan_darah()+" : "+stokDarah.getStok_darahmasuk());
            tvGol_Dar.setText(stokDarah.getGolongan_darah());
        }

        return view;
    }
}
