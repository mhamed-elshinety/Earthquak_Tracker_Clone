package com.example.earthquaketrackerclone.ui;

import android.os.Bundle;

import com.example.earthquaketrackerclone.listeners.OnGetEarthquakesListener;
import com.example.earthquaketrackerclone.pojo.EarthquakeModel;
import com.example.earthquaketrackerclone.pojo.USGSModel;

import java.util.ArrayList;
import java.util.Map;

public class FeaturedTabFragmentPresenter implements OnGetEarthquakesListener {

    private FeaturedFragmentView view;
    private USGSModel model;

    public FeaturedTabFragmentPresenter (FeaturedFragmentView view){
        this.view = view;
        model = new USGSModel(this);
    }

    public void getEarthquakesCounters(){
        model.getEarthquakesCounters();
    }

    public void getBiggestEarthquakes(){
        model.getBiggestEarthquakes();
    }

    public void getNearestEarthquakes(){
        model.getNearestEarthquakes();
    }

    @Override
    public void onGetEarthquakesCounters(ArrayList<Integer> counters) {
        view.onGetEarthquakesCounters(counters);
    }

    @Override
    public void onGetBiggestEarthquakes(ArrayList<USGSModel> usgsModels) {
            view.onGetBiggestEarthquakes(usgsModels);
    }

    @Override
    public void onGetNearestEarthquakes(ArrayList<USGSModel> usgsModels) {
        view.onGetNearestEarthquakes(usgsModels);
    }

    @Override
    public void onGetNearYouEarthquakes(ArrayList<EarthquakeModel> earthquakeModels) {

    }

    @Override
    public void onGetMostRecentEarthquakes(ArrayList<EarthquakeModel> earthquakeModels) {

    }

    @Override
    public void onGetSignificantRecentlyEarthquake(EarthquakeModel earthquake) {

    }

    @Override
    public void onGetMapTabEarthquakes(USGSModel usgsModel) {

    }

    @Override
    public void onGetSearchTabEarthquakes(USGSModel usgsModel) {

    }


}
