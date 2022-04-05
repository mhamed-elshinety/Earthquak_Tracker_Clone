package com.example.earthquaketrackerclone.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.earthquaketrackerclone.R;
import com.example.earthquaketrackerclone.data.Constants;
import com.example.earthquaketrackerclone.pojo.EarthquakeModel;

import java.util.ArrayList;

public class MapTabFragment extends Fragment {

    ArrayList<EarthquakeModel> earthquakes;

    //New Instance method "This function is very important to get any data from activity"
    public static MapTabFragment newInstance(ArrayList<EarthquakeModel> earthquakes){
        MapTabFragment fragment = new MapTabFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.KEY_EARTHQUAKES,earthquakes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defineFields();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    //define fragment fields override from PagerFragmentParent
    public void defineFields(){
        this.earthquakes = (ArrayList<EarthquakeModel>) getArguments().getSerializable(Constants.KEY_EARTHQUAKES);
    }

    public void defineViews() {

    }
}
