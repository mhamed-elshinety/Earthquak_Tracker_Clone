package com.example.earthquaketrackerclone.ui;

import com.example.earthquaketrackerclone.pojo.EarthquakeModel;

import java.util.ArrayList;

public interface LatestTabFragmentView {
    void getNearYouEarthquakes(ArrayList<EarthquakeModel> earthquakes);
    void getSignificantRecentlyEarthquake(EarthquakeModel earthquakes);
    void getMostRecentEarthquakes(ArrayList<EarthquakeModel> earthquake);
}
