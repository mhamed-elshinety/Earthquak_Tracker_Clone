package com.example.earthquaketrackerclone.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earthquaketrackerclone.R;
import com.example.earthquaketrackerclone.adapters.EarthquakeRecAdapter;
import com.example.earthquaketrackerclone.data.Constants;
import com.example.earthquaketrackerclone.pojo.EarthquakeModel;

import java.util.ArrayList;

public class SearchResultFragment extends Fragment {

    private ArrayList<EarthquakeModel> earthquakes;
    private RecyclerView recyclerView;

    public static SearchResultFragment getNewInstance() {
        SearchResultFragment searchResultFragment = new SearchResultFragment();
        return searchResultFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defineFields();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_result, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        defineViews();
        addEarthquakesToRecView();
    }

    private void addEarthquakesToRecView() {
        recyclerView.setAdapter(new EarthquakeRecAdapter(getActivity(),earthquakes,LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
    }

    private void defineViews() {
        recyclerView = requireActivity().findViewById(R.id.earthquakes_vertical_rec_view);
    }


    private void defineFields() {
        earthquakes = (ArrayList<EarthquakeModel>) getArguments().getSerializable(Constants.KEY_EARTHQUAKES);
    }


}
