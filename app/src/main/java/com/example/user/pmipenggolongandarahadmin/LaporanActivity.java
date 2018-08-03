package com.example.user.pmipenggolongandarahadmin;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.pmipenggolongandarahadmin.mAdapters.TabFragmentLaporanAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LaporanActivity extends AppCompatActivity {
    private DatabaseReference reference;

    private ViewPager pager;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);

        getSupportActionBar().setTitle("Laporan Stok Darah");
        pager = (ViewPager)findViewById(R.id.pager);
        tabs = (TabLayout)findViewById(R.id.tabs);

        pager.setAdapter(new TabFragmentLaporanAdapter(getSupportFragmentManager()));
        tabs.setTabTextColors(getResources().getColor(R.color.colorPrimaryDark), getResources().getColor(android.R.color.white));

        tabs.setupWithViewPager(pager);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        reference = FirebaseDatabase.getInstance().getReference("pendonor_darah/");
    }
}
