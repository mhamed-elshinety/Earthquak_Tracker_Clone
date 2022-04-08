package com.example.earthquaketrackerclone.ui;

import com.example.earthquaketrackerclone.listeners.OnGetEarthquakesListener;
import com.example.earthquaketrackerclone.pojo.EarthquakeModel;
import com.example.earthquaketrackerclone.pojo.USGSModel;

import java.util.ArrayList;

public class MapTabFragmentPresenter implements OnGetEarthquakesListener {

    private MapTabFragmentView mapTabFragmentView;
    private USGSModel usgsModel;

    public MapTabFragmentPresenter(MapTabFragmentView mapTabFragmentView) {
        this.mapTabFragmentView = mapTabFragmentView;
        usgsModel = new USGSModel(this);
    }

    public void getMapEarthquakes(){
        usgsModel.getMapTabEarthquakes();
    }

    @Override
    public void onGetMapTabEarthquakes(USGSModel usgsModel) {
        mapTabFragmentView.getMapEarthquakes(usgsModel);
    }

    @Override
    public void onGetSearchTabEarthquakes(USGSModel usgsModel) {

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

}
