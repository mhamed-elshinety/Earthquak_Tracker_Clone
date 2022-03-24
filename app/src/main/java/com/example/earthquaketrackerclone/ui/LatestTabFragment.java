package com.example.earthquaketrackerclone.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.earthquaketrackerclone.R;
import com.example.earthquaketrackerclone.other.Constants;
import com.example.earthquaketrackerclone.other.PagerFragmentParent;
import com.example.earthquaketrackerclone.pojo.Earthquake;

import java.util.ArrayList;

public class LatestTabFragment extends PagerFragmentParent {

    private ArrayList<Earthquake> earthquakes;

    //New Instance method "This function is very important to get any data from activity"
    public static LatestTabFragment newInstance (ArrayList<Earthquake> earthquakes){
        LatestTabFragment fragment = new LatestTabFragment();
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
        return inflater.inflate(R.layout.fragment_latest,container,false);  //inflating latest fragment layout
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    //define fragment fields override from PagerFragmentParent
    @Override
    public void defineFields(){
        this.earthquakes = (ArrayList<Earthquake>) getArguments().getSerializable(Constants.KEY_EARTHQUAKES);
    }

}
