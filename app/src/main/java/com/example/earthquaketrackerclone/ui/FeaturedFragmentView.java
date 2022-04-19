package com.example.earthquaketrackerclone.ui;

import com.example.earthquaketrackerclone.pojo.EarthquakeModel;
import com.example.earthquaketrackerclone.pojo.USGSModel;

import java.util.ArrayList;
import java.util.Map;

public interface FeaturedFragmentView {
    void onGetEarthquakesCounters(ArrayList<Integer> counters);
    void onGetBiggestEarthquakes(ArrayList<USGSModel> usgsModels);
    void onGetNearestEarthquakes(ArrayList<USGSModel> usgsModels);
}
