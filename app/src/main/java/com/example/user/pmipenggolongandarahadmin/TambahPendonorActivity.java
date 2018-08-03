package com.example.user.pmipenggolongandarahadmin;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.user.pmipenggolongandarahadmin.R;
import com.example.user.pmipenggolongandarahadmin.mAdapters.PendonorListAdapter;
import com.example.user.pmipenggolongandarahadmin.mAdapters.TambahPendonorListAdapater;
import com.example.user.pmipenggolongandarahadmin.mDatas.EventPMI;
import com.example.user.pmipenggolongandarahadmin.mDatas.PendonorDarah;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TambahPendonorActivity extends AppCompatActivity {
    private PendonorDarah pendonor;
    private DatabaseReference reference;
    private TambahPendonorListAdapater tambahPendonorListAdapater;
    private List<PendonorDarah> pendonorDarahList;
    private ListView lvtambahPendonor;

    private Button btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pendonor);

        btnTambah = (Button)findViewById(R.id.btn_tambah);

        getSupportActionBar().setTitle("Tambah Pendonor Darah");

        tambahClick();

        lvtambahPendonor= (ListView)findViewById(R.id.lv_tambahPendonor);
        initData();
    }

    private void tambahClick() {
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(TambahPendonorActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.pendonor_darah, null);

                final EditText etNama = (EditText)mView.findViewById(R.id.et_nama);
                final EditText etAlamat = (EditText)mView.findViewById(R.id.et_alamat);
                final Spinner sgolDar = (Spinner) mView.findViewById(R.id.s_golDar);
                final Spinner sJK = (Spinner)mView.findViewById(R.id.s_JK);
                final EditText etPekerjaan = (EditText)mView.findViewById(R.id.et_Pekerjaan);
                final EditText etNHP = (EditText)mView.findViewById(R.id.et_NHP);
                final EditText etTLahir = (EditText)mView.findViewById(R.id.et_TLahir);
                final EditText etBBadan = (EditText)mView.findViewById(R.id.et_BBadan);
                final EditText etTBadan = (EditText)mView.findViewById(R.id.et_TBadan);

                Button btn_Tambah = (Button)mView.findViewById(R.id.btn_Tambah);
                Button btnKembali = (Button)mView.findViewById(R.id.btn_Kembali);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();

                dialog.show();
                dialog.setCanceledOnTouchOutside(false);

                btn_Tambah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String Nma = etNama.getText().toString();
                        final String Alm = etAlamat.getText().toString();
                        final String Gol = sgolDar.getSelectedItem().toString();
                        final String JK = sJK.getSelectedItem().toString();
                        final String P = etPekerjaan.getText().toString();
                        final String NHP = etNHP.getText().toString();
                        final String TLhr = etTLahir.getText().toString();
                        final String BBdn = etBBadan.getText().toString();
                        final String TBdn = etTBadan.getText().toString();
                        final String TDfr = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

                        if(TextUtils.isEmpty(Nma)){
                            Toast.makeText(getApplicationContext(), "Nama belum diisi", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if(TextUtils.isEmpty(Alm)){
                            Toast.makeText(getApplicationContext(), "Alamat belum diisi", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if(Gol.equals("")){
                            Toast.makeText(getApplicationContext(), "Golongan darah belum diisi", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if(JK.equals("")){
                            Toast.makeText(getApplicationContext(), "Jenis kelamin belum diisi", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if(TextUtils.isEmpty(P)){
                            Toast.makeText(getApplicationContext(), "Pekerjaan belum diisi", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if(TextUtils.isEmpty(NHP)){
                            Toast.makeText(getApplicationContext(), "No handphone belum diisi", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if(TextUtils.isEmpty(TLhr)){
                            Toast.makeText(getApplicationContext(), "Tanggal lahir belum diisi", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if(TextUtils.isEmpty(BBdn)){
                            Toast.makeText(getApplicationContext(), "Berat badan belum diisi", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if(TextUtils.isEmpty(TBdn)){
                            Toast.makeText(getApplicationContext(), "Tinggi badan belum diisi", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if(TextUtils.isEmpty(TDfr)){
                            Toast.makeText(getApplicationContext(), "Tanggal daftar belum diisi", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        DatabaseReference reference;

                        reference = FirebaseDatabase.getInstance().getReference();

                        final PendonorDarah pendonorDarah = new PendonorDarah(Nma,Alm,Gol,NHP, JK, P, TDfr, TLhr, BBdn, TBdn);

                        final String adminId = reference.child("pendonor_darah").push().getKey();
                        reference.child("pendonor_darah").child(adminId).setValue(pendonorDarah);

                        reference.child("stok_darah").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                int stok_masukDarah;
                                int jumlah_stok;
                                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    if(snapshot.child("golongan_darah").getValue().equals(Gol)){
                                        String key = snapshot.child("tambah_stok_pendonor").getRef().push().getKey();
                                        snapshot.child("tambah_stok_pendonor").child(key).getRef().setValue(pendonorDarah);

                                        int jumlah = Integer.parseInt(snapshot.child("jumlah").getValue(String.class));
                                        int sum_darahMasuk = Integer.parseInt(snapshot.child("stok_darahmasuk").getValue(String.class));

                                        stok_masukDarah = sum_darahMasuk + 1;
                                        jumlah_stok = jumlah + 1;
                                        snapshot.child("stok_darahmasuk").getRef().setValue(String.valueOf(stok_masukDarah));
                                        snapshot.child("jumlah").getRef().setValue(String.valueOf(jumlah_stok));
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        dialog.dismiss();
                    }
                });

                btnKembali.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void initData() {
        pendonorDarahList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("pendonor_darah/");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pendonorDarahList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    pendonor= snapshot.getValue(PendonorDarah.class);
                    pendonorDarahList.add(pendonor);
                }

                tambahPendonorListAdapater = new TambahPendonorListAdapater(getApplicationContext(), pendonorDarahList);
                lvtambahPendonor.setAdapter(tambahPendonorListAdapater);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
