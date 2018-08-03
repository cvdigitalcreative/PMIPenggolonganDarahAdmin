package com.example.user.pmipenggolongandarahadmin;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.pmipenggolongandarahadmin.mAdapters.StokListAdapter;
import com.example.user.pmipenggolongandarahadmin.mDatas.StokDarah;
import com.example.user.pmipenggolongandarahadmin.mFragments.StokFragment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StokDarahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stok_darah);

        getSupportActionBar().setTitle("Stok Darah");

        StokFragment stokFragment = new StokFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, stokFragment).commit();
    }

}
