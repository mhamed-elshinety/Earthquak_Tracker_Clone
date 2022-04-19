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
import com.example.earthquaketrackerclone.adapters.BiggestEarthquakesAdapter;
import com.example.earthquaketrackerclone.adapters.EarthquakesCountersAdapter;
import com.example.earthquaketrackerclone.adapters.NearestEarthquakesAdapter;
import com.example.earthquaketrackerclone.pojo.EarthquakeModel;
import com.example.earthquaketrackerclone.pojo.USGSModel;

import java.util.ArrayList;
import java.util.Map;

public class FeaturedFragment extends Fragment implements FeaturedFragmentView {

    private RecyclerView earthquakesCountersRecView;
    private RecyclerView biggestEarthquakesRecView;
    private RecyclerView nearestEarthquakesRecView;

    private FeaturedTabFragmentPresenter presenter;

    public static FeaturedFragment newInstance(){
        return new FeaturedFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defineFields();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_featured,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        defineViews(view);
        presenter.getEarthquakesCounters();
        presenter.getBiggestEarthquakes();
        presenter.getNearestEarthquakes();
    }

    public void defineFields(){
        presenter = new FeaturedTabFragmentPresenter(this);
    }

    public void defineViews(View view) {
        earthquakesCountersRecView = view.findViewById(R.id.earthquakes_counter_rec_view);
        biggestEarthquakesRecView = view.findViewById(R.id.biggest_earthquakes_rec_view);
        nearestEarthquakesRecView = view.findViewById(R.id.nearest_earthquakes_rec_view);
    }

    @Override
    public void onGetEarthquakesCounters(ArrayList<Integer> counters) {
        setCountersRecyclerView(counters);
    }

    private void setCountersRecyclerView(ArrayList<Integer> counters) {
        earthquakesCountersRecView.setAdapter(new EarthquakesCountersAdapter(counters,getContext()));
        earthquakesCountersRecView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    public void onGetBiggestEarthquakes(ArrayList<USGSModel> usgsModels) {
        biggestEarthquakesRecView.setAdapter(new BiggestEarthquakesAdapter(usgsModels,getContext()));
        biggestEarthquakesRecView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    public void onGetNearestEarthquakes(ArrayList<USGSModel> usgsModels) {
        nearestEarthquakesRecView.setAdapter(new NearestEarthquakesAdapter(usgsModels, getContext()));
        nearestEarthquakesRecView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    }
}
