package com.example.user.pmipenggolongandarahadmin.mAdapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.user.pmipenggolongandarahadmin.InformasiEventActivity;
import com.example.user.pmipenggolongandarahadmin.R;
import com.example.user.pmipenggolongandarahadmin.mDatas.EventPMI;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.List;

/**
 * Created by USER on 15/08/2017.
 */

public class EventListAdapter extends BaseAdapter{
    private Context context;
    private List<EventPMI> eventPMIs;
    private Activity mContext;

    public EventListAdapter(Context context, List<EventPMI> eventPMIs, Activity mContext) {
        this.context = context;
        this.eventPMIs = eventPMIs;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return eventPMIs.size();
    }

    @Override
    public Object getItem(int i) {
        return eventPMIs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final EventPMI eventPMI = (EventPMI)getItem(i);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_event_pmi, viewGroup, false);
        }

        TextView tgl = (TextView)view.findViewById(R.id.tanggal_event);
        TextView tmp = (TextView)view.findViewById(R.id.tempat_event);
        TextView jam = (TextView)view.findViewById(R.id.jam_event);
        Button btnEdit = (Button)view.findViewById(R.id.btn_edit);
        Button btnHapus = (Button)view.findViewById(R.id.btn_hapus);

        tgl.setText(eventPMI.getTanggal());
        tmp.setText(eventPMI.getTempat());
        jam.setText(eventPMI.getJam());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                LayoutInflater layoutInflater = mContext.getLayoutInflater();
                View mView = layoutInflater.inflate(R.layout.informasi_event, null);

                final EditText etTanggal = (EditText)mView.findViewById(R.id.et_tanggal);
                final EditText etTempat = (EditText)mView.findViewById(R.id.et_tempat);
                final EditText etJam = (EditText)mView.findViewById(R.id.et_jam);
                Button btnSimpan = (Button)mView.findViewById(R.id.btn_Simpan);
                Button btnKembali = (Button)mView.findViewById(R.id.btn_Kembali);

                etTanggal.setText(eventPMI.getTanggal());
                etTempat.setText(eventPMI.getTempat());
                etJam.setText(eventPMI.getJam());

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();

                dialog.show();
                dialog.setCanceledOnTouchOutside(false);

                btnSimpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String Tgl = etTanggal.getText().toString();
                        final String Tmp = etTempat.getText().toString();
                        final String jam = etJam.getText().toString();

                        DatabaseReference reference;

                        reference = FirebaseDatabase.getInstance().getReference("event_pmi/");

                        reference.orderByChild("tanggal").equalTo(eventPMI.getTanggal()).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                String tempatData = dataSnapshot.child("tempat").getValue(String.class);
                                String jamData = dataSnapshot.child("jam").getValue(String.class);
                                if(tempatData.equals(eventPMI.getTempat()) && jamData.equals(eventPMI.getJam())){
                                    dataSnapshot.child("tanggal").getRef().setValue(Tgl);
                                    dataSnapshot.child("tempat").getRef().setValue(Tmp);
                                    dataSnapshot.child("jam").getRef().setValue(jam);
                                    dialog.dismiss();
                                }
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
                View mView = layoutInflater.inflate(R.layout.info_event_hapus, null);

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

                        reference = FirebaseDatabase.getInstance().getReference("event_pmi/");

                        reference.orderByChild("tanggal").equalTo(eventPMI.getTanggal()).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                String tempatData = dataSnapshot.child("tempat").getValue(String.class);
                                String jamData = dataSnapshot.child("jam").getValue(String.class);
                                if(tempatData.equals(eventPMI.getTempat()) && jamData.equals(eventPMI.getJam())){
                                    dataSnapshot.child("tanggal").getRef().removeValue();
                                    dataSnapshot.child("tempat").getRef().removeValue();
                                    dataSnapshot.child("jam").getRef().removeValue();
                                    dialog.dismiss();
                                }
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
