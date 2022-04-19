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
import com.example.earthquaketrackerclone.pojo.USGSModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class FeaturedFragment extends Fragment implements FeaturedFragmentView {

    private RecyclerView earthquakesCountersRecView;
    private RecyclerView biggestEarthquakesRecView;
    private RecyclerView nearestEarthquakesRecView;
    private ShimmerFrameLayout counterShimmerLayout;
    private ShimmerFrameLayout biggestShimmerLayout;
    private ShimmerFrameLayout nearestShimmerLayout;

    private FeaturedTabFragmentPresenter presenter;

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
        callPresenters();
        startShimmers();
    }

    private void startShimmers() {
        counterShimmerLayout.startShimmer();
        biggestShimmerLayout.startShimmer();
        nearestShimmerLayout.startShimmer();
    }

    private void callPresenters() {
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
        counterShimmerLayout = view.findViewById(R.id.earthquakes_counter_shimmer);
        biggestShimmerLayout = view.findViewById(R.id.biggest_earthquakes_shimmer);
        nearestShimmerLayout = view.findViewById(R.id.nearest_earthquakes_shimmer);
    }

    @Override
    public void onGetEarthquakesCounters(ArrayList<Integer> counters) {
        setCountersRecyclerView(counters);
    }

    private void setCountersRecyclerView(ArrayList<Integer> counters) {
        counterShimmerLayout.stopShimmer();
        counterShimmerLayout.setVisibility(View.GONE);
        earthquakesCountersRecView.setAdapter(new EarthquakesCountersAdapter(counters,getContext()));
        earthquakesCountersRecView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    public void onGetBiggestEarthquakes(ArrayList<USGSModel> usgsModels) {
        biggestShimmerLayout.stopShimmer();
        biggestShimmerLayout.setVisibility(View.GONE);
        biggestEarthquakesRecView.setAdapter(new BiggestEarthquakesAdapter(usgsModels,getContext()));
        biggestEarthquakesRecView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    public void onGetNearestEarthquakes(ArrayList<USGSModel> usgsModels) {
        nearestShimmerLayout.stopShimmer();
        nearestShimmerLayout.setVisibility(View.GONE);
        nearestEarthquakesRecView.setAdapter(new NearestEarthquakesAdapter(usgsModels, getContext()));
        nearestEarthquakesRecView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    }
}
