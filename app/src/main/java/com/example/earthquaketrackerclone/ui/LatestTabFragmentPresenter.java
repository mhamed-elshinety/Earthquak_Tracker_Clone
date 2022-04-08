package com.example.earthquaketrackerclone.ui;

import com.example.earthquaketrackerclone.listeners.OnGetEarthquakesListener;
import com.example.earthquaketrackerclone.pojo.EarthquakeModel;
import com.example.earthquaketrackerclone.pojo.USGSModel;

import java.util.ArrayList;

public class LatestTabFragmentPresenter implements OnGetEarthquakesListener {

    private final LatestTabFragmentView view;
    private final USGSModel model =  new USGSModel(this);

    public LatestTabFragmentPresenter(LatestTabFragmentView view) {
            this.view = view;
        }

    public void getNearYouEarthquakes(){
        model.getNearYouEarthquakes();
    }
    public void getMostRecentEarthquakes(){
        model.getMostRecentEarthquakes();
    }
    public void getSignificantRecentlyEarthquake(){model.getSignificantRecentlyEarthquake();}


    @Override
    public void onGetNearYouEarthquakes(ArrayList<EarthquakeModel> earthquakeModels) {
        view.getNearYouEarthquakes(earthquakeModels);
    }

    @Override
    public void onGetMostRecentEarthquakes(ArrayList<EarthquakeModel> earthquakeModels) {
        view.getMostRecentEarthquakes(earthquakeModels);
    }

    @Override
    public void onGetSignificantRecentlyEarthquake(EarthquakeModel earthquakeModel) {
        view.getSignificantRecentlyEarthquake(earthquakeModel);
    }

    @Override
    public void onGetMapTabEarthquakes(USGSModel usgsModel) {

    }

    @Override
    public void onGetSearchTabEarthquakes(USGSModel usgsModel) {

    }
}
