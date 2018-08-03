package com.example.user.pmipenggolongandarahadmin.mFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.pmipenggolongandarahadmin.R;
import com.example.user.pmipenggolongandarahadmin.mAdapters.StokListAdapter;
import com.example.user.pmipenggolongandarahadmin.mDatas.StokDarah;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StokFragment extends Fragment {
    private ListView lvStok;
    private List<StokDarah> stokList;
    private StokListAdapter stokAdapter;
    private StokDarah stokDarah;
    private DatabaseReference reference;

    public StokFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stok, container, false);

        lvStok = (ListView)view.findViewById(R.id.lvStok);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        stokList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("stok_darah/");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                stokList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    stokDarah = snapshot.getValue(StokDarah.class);
                    stokList.add(stokDarah);
                }

                stokAdapter = new StokListAdapter(getContext(), stokList);
                lvStok.setAdapter(stokAdapter);

                lvStok.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TextView gol = view.findViewById(R.id.golDar);

                        String Gol = gol.getText().toString();

                        //Toast.makeText(getActivity().getApplicationContext(), Gol, Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putString("golDar", Gol);

                        GolPendonorFragment golPendonorFragment = new GolPendonorFragment();
                        golPendonorFragment.setArguments(bundle);

                        getFragmentManager().beginTransaction().replace(R.id.frame_container, golPendonorFragment).commit();
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
