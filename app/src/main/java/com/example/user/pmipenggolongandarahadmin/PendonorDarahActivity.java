package com.example.user.pmipenggolongandarahadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.user.pmipenggolongandarahadmin.mAdapters.PendonorListAdapter;
import com.example.user.pmipenggolongandarahadmin.mDatas.PendonorDarah;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PendonorDarahActivity extends AppCompatActivity {
    private PendonorDarah pendonorDarah;
    private DatabaseReference reference;
    private PendonorListAdapter pendonorListAdapter;
    private List<PendonorDarah> pendonorDarahList;
    private ListView lvpendonorDarah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendonor_darah);

        lvpendonorDarah = (ListView)findViewById(R.id.lv_pendonorDarah);
        getSupportActionBar().setTitle("Pendonor Darah");

        initData();
    }

    private void initData() {
        pendonorDarahList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("pendonor_darah/");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pendonorDarahList.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    pendonorDarah = snapshot.getValue(PendonorDarah.class);
                    pendonorDarahList.add(pendonorDarah);
                }

                pendonorListAdapter = new PendonorListAdapter(getApplicationContext(), pendonorDarahList, PendonorDarahActivity.this);
                lvpendonorDarah.setAdapter(pendonorListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
