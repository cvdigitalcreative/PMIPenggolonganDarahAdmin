package com.example.user.pmipenggolongandarahadmin.mAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.pmipenggolongandarahadmin.R;
import com.example.user.pmipenggolongandarahadmin.mDatas.StokDarah;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 16/08/2017.
 */

public class LaporanAdapter extends BaseAdapter {
    private Context context;
    private List<StokDarah> stokDarahList;

    public LaporanAdapter(Context context, List<StokDarah> stokDarahList) {
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
        String golDarAmin = "";
        String golDarAplus = "";
        String golDarBmin = "";
        String golDarBplus = "";
        String golDarABmin = "";
        String golDarABplus = "";
        String golDarOmin = "";
        String golDarOplus = "";

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_stok_darah_keluar, viewGroup, false);

            TextView tvTanggal = view.findViewById(R.id.tv_tanggal);
            TextView tvJumlah = view.findViewById(R.id.tv_Jumlah);
            String Currentdate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

            for(int urut=0; urut<stokDarahList.size(); urut++){
                if("A-".equals(stokDarahList.get(i).getGolongan_darah())){
                    golDarAmin = stokDarahList.get(i).getStok_darahkeluar();
                }
                if ("A+".equals(stokDarahList.get(i).getGolongan_darah())){
                    golDarAplus = stokDarahList.get(i).getStok_darahkeluar();
                }
                if ("B-".equals(stokDarahList.get(i).getGolongan_darah())){
                    golDarBmin = stokDarahList.get(i).getStok_darahkeluar();
                }
                if ("B+".equals(stokDarahList.get(i).getGolongan_darah())){
                    golDarBplus = stokDarahList.get(i).getStok_darahkeluar();
                }
                if ("AB-".equals(stokDarahList.get(i).getGolongan_darah())){
                    golDarABmin = stokDarahList.get(i).getStok_darahkeluar();
                }
                if ("AB+".equals(stokDarahList.get(i).getGolongan_darah())){
                    golDarABplus = stokDarahList.get(i).getStok_darahkeluar();
                }
                if ("O-".equals(stokDarahList.get(i).getGolongan_darah())){
                    golDarOmin = stokDarahList.get(i).getStok_darahkeluar();
                }
                if ("O+".equals(stokDarahList.get(i).getGolongan_darah())){
                    golDarOplus = stokDarahList.get(i).getStok_darahkeluar();
                }
            }

            int Amin = Integer.parseInt(golDarAmin);
            int Aplus = Integer.parseInt(golDarAplus);
            int Bmin = Integer.parseInt(golDarBmin);
            int Bplus = Integer.parseInt(golDarBplus);
            int ABmin = Integer.parseInt(golDarABmin);
            int ABplus = Integer.parseInt(golDarABplus);
            int Omin = Integer.parseInt(golDarOmin);
            int Oplus = Integer.parseInt(golDarOplus);
            int jumlah = Amin + Aplus + Bmin + Bplus + ABmin + ABplus + Omin + Oplus;


            String totJumlah = String.valueOf(jumlah);


            tvTanggal.setText(Currentdate);
            tvJumlah.setText(totJumlah);
        }

        return view;
    }
}
