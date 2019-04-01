package com.example.animalapp.SearchByBird;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.animalapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class BirdHeightFragment extends Fragment implements View.OnClickListener {
    ArrayList<String> filterItems = new ArrayList<>();


    public BirdHeightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bird_height, container, false);
        Bundle bundle = this.getArguments();
        TextView passed_detail = view.findViewById(R.id.filter_view);
        if (bundle != null) {
            filterItems = bundle.getStringArrayList("filter");
            StringBuilder filter = new StringBuilder();
            for (String filterItem :
                    filterItems) {
                Log.i("PASSED VALUES: ", filterItem);
                List<String> splitFilterItem = Arrays.asList(filterItem.split(":"));
                filter.append(splitFilterItem.get(0)).append(":").append(splitFilterItem.get(1));
            }
            passed_detail.setText("Filter: " + filter);
        }


        Button bird_height_option_one = (Button) view.findViewById(R.id.bird_height_option_1);
        Button bird_height_option_two = (Button) view.findViewById(R.id.bird_height_option_2);
        Button bird_height_option_three = (Button) view.findViewById(R.id.bird_height_option_3);

        bird_height_option_one.setOnClickListener(this);
        bird_height_option_two.setOnClickListener(this);
        bird_height_option_three.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {

        Bundle bundle = this.getArguments();
        if (bundle == null) {
            Log.i("NULL BUNDLE", "NOT WORKED");
            bundle = new Bundle();
        }

        int i = v.getId();
        if (i == R.id.bird_height_option_1) {
            filterItems.add("Height" + ":" + "<15cm");
            bundle.putStringArrayList("filter", filterItems);
            Navigation.findNavController(v).navigate(R.id.action_birdHeightFragment_to_birdHeadColourFragment,bundle);

        }else if (i == R.id.bird_height_option_2) {
            filterItems.add("Height" + ":" + ">15cm" +","+"<30cm");
            bundle.putStringArrayList("filter", filterItems);
            Navigation.findNavController(v).navigate(R.id.action_birdHeightFragment_to_birdHeadColourFragment,bundle);

        }else if (i == R.id.bird_height_option_3) {
            filterItems.add("Height" + ":" + ">30cm");
            bundle.putStringArrayList("filter", filterItems);
            Navigation.findNavController(v).navigate(R.id.action_birdHeightFragment_to_birdHeadColourFragment,bundle);

        }

    }
}
