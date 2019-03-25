package com.example.animalapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.animalapp.R;

import java.util.Map;

import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class NatureReserveFragment extends Fragment implements View.OnClickListener{


    public NatureReserveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Nature Reserves");

        View view = inflater.inflate(R.layout.fragment_nature_reserves, container, false);
        Button change_co_btn = (Button) view.findViewById(R.id.change_LatLong_values);
        change_co_btn.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;

        int i = v.getId();
        if (i == R.id.change_LatLong_values) {
            Bundle bundle =  new Bundle();
            bundle.putDouble("Longitude", 51.46067865);
            bundle.putDouble("Latitude", -3.1755209);
            MapFragment map = new MapFragment();
            map.setArguments(bundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, map);
            transaction.addToBackStack(null);
            transaction.commit();


        }

    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
