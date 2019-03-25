package com.example.animalapp;


import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.animalapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */


public class NatureReserveFragment extends Fragment {
    List<DataItem> listData;


    ListView list;
    String titles[] = {"Cardiff Bay", "Hammyred", "Roath Park", "Llanshien Resevoir"};
    String informations[] = {"THINGS 1....", "THINGS 2....", "THINGS 3....", "THINGS 4...."};
    int images[] = {R.drawable.ic_launcher_background, R.drawable.ic_format_list_bulleted_black_24dp, R.drawable.ic_nature_people_black_24dp, R.drawable.ic_map_black_24dp};


    public NatureReserveFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_nature_reserves, container,false);

        list = view.findViewById(R.id.reserves_list);
//        ListView lvData = (ListView) view.findViewById(R.id.list);
//
//        DataItem reserve1 = new DataItem("1","reserve 1","Park","Enter description here");
//        DataItem reserve2 = new DataItem("1","reserve 1","Park","Enter description here");
//        DataItem reserve3 = new DataItem("1","reserve 1","Park","Enter description here");
//        DataItem reserve4 = new DataItem("1","reserve 1","Park","Enter description here");
//        DataItem reserve5 = new DataItem("1","reserve 1","Park","Enter description here");
//        DataItem reserve6 = new DataItem("1","reserve 1","Park","Enter description here");
//        DataItem reserve7 = new DataItem("1","reserve 1","Park","Enter description here");
//        DataItem reserve8 = new DataItem("1","reserve 1","Park","Enter description here");
//
//        ArrayList<DataItem> data = new ArrayList<>();
//        data.add(reserve1);
//        data.add(reserve2);
//        data.add(reserve3);
//        data.add(reserve4);
//        data.add(reserve5);
//        data.add(reserve6);
//        data.add(reserve7);
//        data.add(reserve8);
//
//        CustomAdapter adapter = new CustomAdapter(getContext(),R.layout.fragment_nature_reserves,data);
//        lvData.setAdapter(adapter);
//        super.getView(position, convertView, parent)

        CustomAdapter adapter = new CustomAdapter(getContext(), titles, images, informations);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//          on click for four items of nature reserves
                if (position == 0) {
                    Log.i("WALKS CLICK", "First one");
                }
                if (position == 1) {
                    Log.i("WALKS CLICK", "Second one");
                }
                if (position == 2) {
                    Log.i("WALKS CLICK", "Third one");
                }
                if (position == 3) {
                    Log.i("WALKS CLICK", "Fourth one");
                }
            }
        });

        return view;



    }
    class CustomAdapter extends ArrayAdapter<String> {
        Context context;
        String myTitles[];
        String myDescriptions[];
        int[] imgs;


        public CustomAdapter(Context context, String[] titles, int[] imgs, String[] descriptions) {
            super(context, R.layout.nature_reserve_item, R.id.reserve_title, titles);

            this.context = context;
            this.imgs = imgs;
            this.myTitles = titles;
            this.myDescriptions = descriptions;
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View reserve_item = inflater.inflate(R.layout.nature_reserve_item, parent, false);
            ImageView images = reserve_item.findViewById(R.id.reserve_logo);
            TextView title = reserve_item.findViewById(R.id.reserve_title);
            TextView description = reserve_item.findViewById(R.id.reserve_information);

            images.setImageResource(imgs[position]);
            title.setText(titles[position]);
            description.setText(myDescriptions[position]);

            return reserve_item;
        }
    }








}
