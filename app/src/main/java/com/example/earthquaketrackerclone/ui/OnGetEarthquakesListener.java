package com.example.earthquaketrackerclone.ui;

import com.example.earthquaketrackerclone.pojo.EarthquakeModel;

import java.util.ArrayList;

public interface OnGetEarthquakesListener {
    void onGetNearYouEarthquakes(ArrayList<EarthquakeModel> earthquakeModels);
    void onGetMostRecentEarthquakes(ArrayList<EarthquakeModel> earthquakeModels);
    void onGetSignificantRecentlyEarthquake(EarthquakeModel earthquake);
}
