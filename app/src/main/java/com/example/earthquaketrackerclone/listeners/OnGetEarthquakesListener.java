package com.example.earthquaketrackerclone.listeners;

import com.example.earthquaketrackerclone.pojo.EarthquakeModel;
import com.example.earthquaketrackerclone.pojo.USGSModel;

import java.util.ArrayList;
import java.util.Map;

public interface OnGetEarthquakesListener {
    void onGetNearYouEarthquakes(ArrayList<EarthquakeModel> earthquakeModels);
    void onGetMostRecentEarthquakes(ArrayList<EarthquakeModel> earthquakeModels);
    void onGetSignificantRecentlyEarthquake(EarthquakeModel earthquake);
    void onGetMapTabEarthquakes(USGSModel usgsModel);
    void onGetSearchTabEarthquakes(USGSModel usgsModel);
    void onGetEarthquakesCounters(ArrayList<Integer> integers);
    void onGetBiggestEarthquakes(ArrayList<USGSModel> usgsModels);
    void onGetNearestEarthquakes(ArrayList<USGSModel> usgsModels);
}
