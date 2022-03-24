package com.example.earthquaketrackerclone.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.earthquaketrackerclone.R;
import com.example.earthquaketrackerclone.other.Constants;
import com.example.earthquaketrackerclone.other.PagerFragmentParent;
import com.example.earthquaketrackerclone.pojo.Earthquake;

import java.util.ArrayList;

public class SearchFragment extends PagerFragmentParent {

    ArrayList<Earthquake> earthquakes;

    public static SearchFragment newInstance(ArrayList<Earthquake> earthquakes){
        SearchFragment fragment = new SearchFragment();
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
        return inflater.inflate(R.layout.fragment_search,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    //define fragment fields override from PagerFragmentParent
    @Override
    public void defineFields() {
        this.earthquakes = (ArrayList<Earthquake>) getArguments().getSerializable(Constants.KEY_EARTHQUAKES);
    }
}
