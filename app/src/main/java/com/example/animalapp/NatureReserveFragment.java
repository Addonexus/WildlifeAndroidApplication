package com.example.animalapp;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.animalapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */


public class NatureReserveFragment extends Fragment {
    List<DataItem> listData;

    public NatureReserveFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_nature_reserves, container,false);
        ListView lvData = (ListView) view.findViewById(R.id.list);

        DataItem reserve1 = new DataItem("1","reserve 1","Park","Enter description here");
        DataItem reserve2 = new DataItem("1","reserve 1","Park","Enter description here");
        DataItem reserve3 = new DataItem("1","reserve 1","Park","Enter description here");
        DataItem reserve4 = new DataItem("1","reserve 1","Park","Enter description here");
        DataItem reserve5 = new DataItem("1","reserve 1","Park","Enter description here");
        DataItem reserve6 = new DataItem("1","reserve 1","Park","Enter description here");
        DataItem reserve7 = new DataItem("1","reserve 1","Park","Enter description here");
        DataItem reserve8 = new DataItem("1","reserve 1","Park","Enter description here");

        ArrayList<DataItem> data = new ArrayList<>();
        data.add(reserve1);
        data.add(reserve2);
        data.add(reserve3);
        data.add(reserve4);
        data.add(reserve5);
        data.add(reserve6);
        data.add(reserve7);
        data.add(reserve8);

        CustomAdapter adapter = new CustomAdapter(getContext(),R.layout.fragment_nature_reserves,data);
        lvData.setAdapter(adapter);

        return view;



    }








}
