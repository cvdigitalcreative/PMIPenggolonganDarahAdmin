package com.example.user.pmipenggolongandarahadmin.mAdapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.pmipenggolongandarahadmin.R;
import com.example.user.pmipenggolongandarahadmin.mDatas.PendonorDarah;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by USER on 15/08/2017.
 */

public class PendonorListAdapter extends BaseAdapter {
    private Context context;
    private List<PendonorDarah> pendonorDarahList;
    private Activity mContext;

    public PendonorListAdapter(Context context, List<PendonorDarah> pendonorDarahList, Activity mContext) {
        this.context = context;
        this.pendonorDarahList = pendonorDarahList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return pendonorDarahList.size();
    }

    @Override
    public Object getItem(int i) {
        return pendonorDarahList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final PendonorDarah pendonorDarah = (PendonorDarah)getItem(i);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_pendonor_darah, viewGroup, false);
        }

        TextView nama = (TextView)view.findViewById(R.id.nama);
        TextView alamat = (TextView)view.findViewById(R.id.alamat);
        TextView gol_darah = (TextView)view.findViewById(R.id.gol_darah);
        TextView no_hp = (TextView)view.findViewById(R.id.no_hp);
        TextView tanggal_daftar = (TextView)view.findViewById(R.id.tanggal_daftar);
        Button btnEdit = (Button)view.findViewById(R.id.btn_edit);
        Button btnHapus = (Button)view.findViewById(R.id.btn_hapus);

        nama.setText(pendonorDarah.getNama());
        gol_darah.setText(pendonorDarah.getGol_darah());
        alamat.setText(pendonorDarah.getAlamat());
        no_hp.setText(pendonorDarah.getNo_hp());
        tanggal_daftar.setText(pendonorDarah.getTanggal_daftar());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                LayoutInflater layoutInflater = mContext.getLayoutInflater();
                View mView = layoutInflater.inflate(R.layout.edit_pendonor, null);

                final EditText etNama = (EditText)mView.findViewById(R.id.et_nama);
                final Spinner sGolDar = (Spinner) mView.findViewById(R.id.s_golDarah);
                final EditText etAlamat = (EditText)mView.findViewById(R.id.et_alamat);
                final EditText etHP = (EditText)mView.findViewById(R.id.et_hp);
                final EditText etTanggal = (EditText)mView.findViewById(R.id.et_tanggal);

                Button btnSimpan = (Button)mView.findViewById(R.id.btn_Simpan);
                Button btnKembali = (Button)mView.findViewById(R.id.btn_Kembali);

                int position = 0;

                if(pendonorDarah.getGol_darah().equals("A-")){
                    position = 1;
                }

                else if(pendonorDarah.getGol_darah().equals("A+")){
                    position = 2;
                }
                else if(pendonorDarah.getGol_darah().equals("B-")){
                    position = 3;
                }
                else if(pendonorDarah.getGol_darah().equals("B+")){
                    position = 4;
                }
                else if(pendonorDarah.getGol_darah().equals("AB-")){
                    position = 5;
                }
                else if(pendonorDarah.getGol_darah().equals("AB+")){
                    position = 6;
                }
                else if(pendonorDarah.getGol_darah().equals("O-")){
                    position = 7;
                }
                else if(pendonorDarah.getGol_darah().equals("O+")){
                    position = 8;
                }


                etNama.setText(pendonorDarah.getNama());
                sGolDar.setSelection(position);
                etAlamat.setText(pendonorDarah.getAlamat());
                etHP.setText(pendonorDarah.getNo_hp());
                etTanggal.setText(pendonorDarah.getTanggal_daftar());


                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();

                dialog.show();
                dialog.setCanceledOnTouchOutside(false);

                btnSimpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String nama = etNama.getText().toString();
                        final String gol_dar = sGolDar.getSelectedItem().toString();
                        final String alamat = etAlamat.getText().toString();
                        final String hp = etHP.getText().toString();
                        final String tanggal = etTanggal.getText().toString();

                        DatabaseReference reference;

                        reference = FirebaseDatabase.getInstance().getReference("pendonor_darah/");

                        reference.orderByChild("nama").equalTo(pendonorDarah.getNama()).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                dataSnapshot.child("nama").getRef().setValue(nama);
                                dataSnapshot.child("gol_darah").getRef().setValue(gol_dar);
                                dataSnapshot.child("alamat").getRef().setValue(alamat);
                                dataSnapshot.child("no_hp").getRef().setValue(hp);
                                dataSnapshot.child("tanggal_lahir").getRef().setValue(tanggal);
                                dialog.dismiss();
                                //Toast.makeText(context.getApplicationContext(), dataSnapshot.child("tempat").getValue(String.class), Toast.LENGTH_SHORT).show();

                                //Log.w("Masuk childAdd", jam);
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
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

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                LayoutInflater layoutInflater = mContext.getLayoutInflater();
                View mView = layoutInflater.inflate(R.layout.hapus_pendonor, null);

                Button btnYa= (Button)mView.findViewById(R.id.btn_Ya);
                Button btnTidak = (Button)mView.findViewById(R.id.btn_Tidak);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();

                dialog.show();
                dialog.setCanceledOnTouchOutside(false);

                btnYa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        DatabaseReference reference;

                        reference = FirebaseDatabase.getInstance().getReference("pendonor_darah/");

                        reference.orderByChild("nama").equalTo(pendonorDarah.getNama()).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                dataSnapshot.child("alamat").getRef().removeValue();
                                dataSnapshot.child("berat_badan").getRef().removeValue();
                                dataSnapshot.child("gol_darah").getRef().removeValue();
                                dataSnapshot.child("jenis_kelamin").getRef().removeValue();
                                dataSnapshot.child("nama").getRef().removeValue();
                                dataSnapshot.child("no_hp").getRef().removeValue();
                                dataSnapshot.child("pekerjaan").getRef().removeValue();
                                dataSnapshot.child("tanggal_daftar").getRef().removeValue();
                                dataSnapshot.child("tanggal_lahir").getRef().removeValue();
                                dataSnapshot.child("tinggi_badan").getRef().removeValue();
                                dialog.dismiss();
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });

                btnTidak.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });


        return view;
    }
}
