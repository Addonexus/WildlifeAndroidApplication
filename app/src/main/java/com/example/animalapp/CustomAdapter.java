//package com.example.animalapp;
//
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomAdapter extends ArrayAdapter<DataItem> {
//    private static final String TAG = "CustomAdapter";
//    private Context mContext;
//    int mResource;
//
//    public CustomAdapter(Context context, int resource, ArrayList<DataItem> objects) {
//        super(context, resource, objects);
//        mContext = context;
//        mResource = resource;
//    }
//
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        String resIdThumbnail = getItem(position).getResIdThumbnail();
//        String reserveName = getItem(position).getReserveName();
//        String shortDesc = getItem(position).getShortDesc();
//        String type = getItem(position).getType();
//        DataItem dataItem = new DataItem(resIdThumbnail,reserveName,shortDesc,type);
//        LayoutInflater inflater =  LayoutInflater.from(mContext);
//        convertView = inflater.inflate(mResource,parent,false);
//        TextView tvReserveName = (TextView) convertView.findViewById(R.id.textView1);
//        TextView tvType = (TextView) convertView.findViewById(R.id.textView2);
//        TextView tvShortDesc = (TextView) convertView.findViewById(R.id.textView3);
//        tvReserveName.setText(reserveName);
//        tvShortDesc.setText(shortDesc);
//        tvType.setText(type);
//        return convertView;
//    }
//}
