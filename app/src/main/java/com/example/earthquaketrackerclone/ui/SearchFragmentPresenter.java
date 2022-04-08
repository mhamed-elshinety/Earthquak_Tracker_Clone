package com.example.earthquaketrackerclone.ui;

import android.os.Bundle;

import com.example.earthquaketrackerclone.listeners.OnGetEarthquakesListener;
import com.example.earthquaketrackerclone.pojo.EarthquakeModel;
import com.example.earthquaketrackerclone.pojo.USGSModel;

import java.util.ArrayList;

public class SearchFragmentPresenter implements OnGetEarthquakesListener {

    private SearchFragmentView searchFragmentView;
    private USGSModel usgsModel;

    public SearchFragmentPresenter(SearchFragmentView searchFragmentView){
        setSearchFragmentView(searchFragmentView);
        usgsModel = new USGSModel(this);
    }

    public void setSearchFragmentView(SearchFragmentView searchFragmentView) {
        this.searchFragmentView = searchFragmentView;
    }

    public void getEarthquakes(Bundle queryBundle){
        usgsModel.getSearchMapEarthquakes(queryBundle);
    }

    @Override
    public void onGetSearchTabEarthquakes(USGSModel usgsModel) {
        searchFragmentView.onGetEarthquakes(usgsModel);
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

}
