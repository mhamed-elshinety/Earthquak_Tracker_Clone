package com.example.earthquaketrackerclone.listeners;

import com.example.earthquaketrackerclone.pojo.EarthquakeModel;
import com.example.earthquaketrackerclone.pojo.USGSModel;

import java.util.ArrayList;

public interface OnGetEarthquakesListener {
    void onGetNearYouEarthquakes(ArrayList<EarthquakeModel> earthquakeModels);
    void onGetMostRecentEarthquakes(ArrayList<EarthquakeModel> earthquakeModels);
    void onGetSignificantRecentlyEarthquake(EarthquakeModel earthquake);
    void onGetMapTabEarthquakes(USGSModel usgsModel);
    void onGetSearchTabEarthquakes(USGSModel usgsModel);
}
