package com.example.earthquaketrackerclone.pojo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.earthquaketrackerclone.data.Assistant;
import com.example.earthquaketrackerclone.data.Constants;
import com.example.earthquaketrackerclone.ui.InterfaceCountryFlagsApi;
import com.example.earthquaketrackerclone.ui.InterfaceUsgsApi;
import com.example.earthquaketrackerclone.ui.OnGetEarthquakesListener;

import java.util.ArrayList;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class USGSModel {

    private ArrayList<Feature> features;
    private final OnGetEarthquakesListener listener;

    public USGSModel(OnGetEarthquakesListener listener) {
        this.listener = listener;
    }

    public USGSModel(ArrayList<Feature> features, OnGetEarthquakesListener listener) {
        this.features = features;
        this.listener = listener;
    }

    public void getNearYouEarthquakes() {
        InterfaceUsgsApi api = getUSGSRetrofitInstance().create(InterfaceUsgsApi.class);

        Observable<USGSModel> observable =
                api.getNearByEarthquakes("geojson",
                        Assistant.getFormatDateAfterDays(-10,"yyyy-MM-dd"),
                        Assistant.getFormatDateAfterDays(0,"yyyy-MM-dd"),
                        0, 0, 50, 50)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(USGSModel -> listener.onGetNearYouEarthquakes(getEarthquakesFromJsonResponse(USGSModel)),
                e -> {Log.d(Constants.LOG_KEY_MY_APP, "aly: " + e.getMessage()); e.printStackTrace();});
    }

    private ArrayList<EarthquakeModel> addFlagsToList(ArrayList<EarthquakeModel> earthquakes) {
        ArrayList<Bitmap> bitmaps = getFlags(getCountriesNames(earthquakes));
        for(int i=0 ; i<earthquakes.size(); i++){
            earthquakes.get(i).setFlagModel(new CountryFlagModel(" ", bitmaps.get(i)));
        }
        return earthquakes;
    }

    private ArrayList<Bitmap> getFlags(ArrayList<String> countriesNames) {
        ArrayList<Bitmap> flags = new ArrayList<>();
        Retrofit retrofitInstance = getFlagsAPIRetrofitInstance();
        InterfaceCountryFlagsApi flagsApi = retrofitInstance.create(InterfaceCountryFlagsApi.class);
        for(int i=0 ; i<countriesNames.size() ; i++){
            Observable<ResponseBody> observable =
                    flagsApi.getFlag(countriesNames.get(i))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            observable.subscribe(o-> flags.add(BitmapFactory.decodeStream(o.byteStream())));
        }
        return flags;
    }

    private Retrofit getFlagsAPIRetrofitInstance() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl("https://countryflagsapi.com")
                .build();
    }

    private ArrayList<String> getCountriesNames(ArrayList<EarthquakeModel> earthquakes) {
        ArrayList<String> countriesNames = new ArrayList<>();
        for(int i=0; i<earthquakes.size() ; i++)
            countriesNames.add(earthquakes.get(i).getPlace().split(" ")[earthquakes.get(i).getPlace().split(" ").length-1]);
        return countriesNames;
    }

    public void getMostRecentEarthquakes() {
        InterfaceUsgsApi api = getUSGSRetrofitInstance().create(InterfaceUsgsApi.class);

        Observable<USGSModel> observable =
                api.getMostRecentEarthquakes("geojson",
                        Assistant.getFormatDateAfterDays(-2,"yyyy-MM-dd"),
                        Assistant.getFormatDateAfterDays(0,"yyyy-MM-dd"), 20,
                        3 ,"time")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(USGSModel ->
                listener.onGetMostRecentEarthquakes(getEarthquakesFromJsonResponse(USGSModel)),
                e -> Log.d(Constants.LOG_KEY_MY_APP, "getMostRecentEarthquakes: " + e.toString()));
    }

    public void getSignificantRecentlyEarthquake() {
        InterfaceUsgsApi api = getUSGSRetrofitInstance().create(InterfaceUsgsApi.class);
        Observable <USGSModel> observable =
                api.getSignificantRecentlyEarthquake("geojson",
                        Assistant.getFormatDateAfterDays(-3,"yyyy-MM-dd"),
                        Assistant.getFormatDateAfterDays(0,"yyyy-MM-dd"),
                        100, "magnitude")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(o-> listener.onGetSignificantRecentlyEarthquake(getEarthquakesFromJsonResponse(o).get(0)),
                             e-> Log.d(Constants.LOG_KEY_MY_APP, "getSignificantRecentlyEarthquake: " + e.toString()));
    }

    private Retrofit getUSGSRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    private ArrayList<EarthquakeModel> getEarthquakesFromJsonResponse(USGSModel USGSModel) {
        ArrayList<EarthquakeModel> earthquakes = new ArrayList<>();
        for (int i = 0; i < USGSModel.features.size(); i++)
            earthquakes.add(USGSModel.features.get(i).getProperties());
        return earthquakes;

    }

    private static class Feature {
        private final EarthquakeModel properties;
        private final Geometry geometry;

        public Feature(EarthquakeModel properties, Geometry geometry) {
            this.properties = properties;
            this.geometry = geometry;
        }

        public EarthquakeModel getProperties() {
            return properties;
        }

        public Geometry getGeometry() {
            return geometry;
        }
    }

    private static class Geometry{
        private final float[] coordinates;

        public Geometry(float[] coordinates) {
            this.coordinates = coordinates;
        }

        public float[] getCoordinates() {
            return coordinates;
        }
    }

}
