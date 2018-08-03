package com.example.user.pmipenggolongandarahadmin.mAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.pmipenggolongandarahadmin.mFragments.StokDarahKeluarFragment;
import com.example.user.pmipenggolongandarahadmin.mFragments.StokDarahMasukFragment;
import com.example.user.pmipenggolongandarahadmin.mFragments.StokKeluarDarahFragment;
import com.example.user.pmipenggolongandarahadmin.mFragments.StokMasukDarahFragment;

/**
 * Created by USER on 16/08/2017.
 */

public class TabFragmentLaporanAdapter extends FragmentStatePagerAdapter {
    String[] title = new String[]{
            "Stok Darah Masuk", "Stok Darah Keluar"
    };

    public TabFragmentLaporanAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new StokMasukDarahFragment();
                break;
            case 1:
                fragment = new StokKeluarDarahFragment();
                break;
            default:
                fragment = null;
                break;
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return title[position];
    }

    @Override
    public int getCount(){
        return title.length;
    }
}
