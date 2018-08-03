package com.example.user.pmipenggolongandarahadmin.mAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.pmipenggolongandarahadmin.R;
import com.example.user.pmipenggolongandarahadmin.mDatas.StokDarah;

import java.util.List;

/**
 * Created by USER on 15/08/2017.
 */

public class StokListAdapter extends BaseAdapter {
    private Context context;
    private List<StokDarah> stokList;

    public StokListAdapter(Context context, List<StokDarah> stokList) {
        this.context = context;
        this.stokList = stokList;
    }

    @Override
    public int getCount() {
        return stokList.size();
    }

    @Override
    public Object getItem(int i) {
        return stokList.get(i);
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
            view = inflater.inflate(R.layout.list_stok_darah, viewGroup, false);
        }

        TextView golDar = (TextView)view.findViewById(R.id.golDar);
        TextView jumlah = (TextView)view.findViewById(R.id.jum_golDar);

        golDar.setText(stokDarah.getGolongan_darah());
        jumlah.setText(stokDarah.getJumlah());

        return view;
    }
}
