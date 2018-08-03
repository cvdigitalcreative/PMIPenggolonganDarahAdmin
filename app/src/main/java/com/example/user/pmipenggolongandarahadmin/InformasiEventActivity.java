package com.example.user.pmipenggolongandarahadmin;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.user.pmipenggolongandarahadmin.mAdapters.EventListAdapter;
import com.example.user.pmipenggolongandarahadmin.mDatas.EventPMI;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InformasiEventActivity extends AppCompatActivity {
    private DatabaseReference reference;
    private EventListAdapter eventListAdapter;
    private EventPMI eventPMI;
    private List<EventPMI> eventPMIs;
    private ListView lvEvent;
    private Button btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_event);

        btnTambah = (Button)findViewById(R.id.btn_tambahInfo);

        getSupportActionBar().setTitle("Infomasi Event");

        lvEvent = (ListView)findViewById(R.id.lvEvent);

        tambahClick();

        initData();
    }

    private void tambahClick() {
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(InformasiEventActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.info_event_tambah, null);

                final EditText etTanggal = (EditText)mView.findViewById(R.id.et_tanggal);
                final EditText etTempat = (EditText)mView.findViewById(R.id.et_tempat);
                final EditText etJam1 = (EditText)mView.findViewById(R.id.et_jam1);
                final EditText etJam2 = (EditText)mView.findViewById(R.id.et_jam2);
                Button btn_Tambah = (Button)mView.findViewById(R.id.btn_Tambah);
                Button btnKembali = (Button)mView.findViewById(R.id.btn_Kembali);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();

                dialog.show();
                dialog.setCanceledOnTouchOutside(false);

                etJam1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Calendar mcurrentTime = Calendar.getInstance();
                        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                        int minute = mcurrentTime.get(Calendar.MINUTE);
                        TimePickerDialog mTimePicker;
                        mTimePicker = new TimePickerDialog(InformasiEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                etJam1.setText( selectedHour + ":" + selectedMinute);
                            }
                        }, hour, minute, true);//Yes 24 hour time
                        mTimePicker.setTitle("Select Time");
                        mTimePicker.show();
                    }
                });

                etJam2.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Calendar mcurrentTime = Calendar.getInstance();
                        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                        int minute = mcurrentTime.get(Calendar.MINUTE);
                        TimePickerDialog mTimePicker;
                        mTimePicker = new TimePickerDialog(InformasiEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                etJam2.setText( selectedHour + ":" + selectedMinute);
                            }
                        }, hour, minute, true);//Yes 24 hour time
                        mTimePicker.setTitle("Select Time");
                        mTimePicker.show();
                    }
                });


                btn_Tambah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String Tgl = etTanggal.getText().toString();
                        final String Tmp = etTempat.getText().toString();
                        final String jam = etJam1.getText().toString() + "-" + etJam2.getText().toString();

                        DatabaseReference reference;

                        reference = FirebaseDatabase.getInstance().getReference("event_pmi/");

                        EventPMI eventPMI = new EventPMI(Tgl,Tmp,jam);

                        String adminId = reference.push().getKey();
                        reference.child(adminId).setValue(eventPMI);
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
        eventPMIs = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("event_pmi/");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventPMIs.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    eventPMI = snapshot.getValue(EventPMI.class);
                    eventPMIs.add(eventPMI);
                }

                eventListAdapter = new EventListAdapter(getApplicationContext(), eventPMIs, InformasiEventActivity.this);
                lvEvent.setAdapter(eventListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
