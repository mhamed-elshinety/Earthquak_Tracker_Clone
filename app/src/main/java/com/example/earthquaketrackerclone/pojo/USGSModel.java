package com.example.earthquaketrackerclone.pojo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.example.earthquaketrackerclone.data.Assistant;
import com.example.earthquaketrackerclone.data.Constants;
import com.example.earthquaketrackerclone.apis.InterfaceCountryFlagsApi;
import com.example.earthquaketrackerclone.apis.InterfaceUsgsApi;
import com.example.earthquaketrackerclone.listeners.OnGetEarthquakesListener;

import java.util.ArrayList;
import java.util.Date;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
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

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }

    public void getNearYouEarthquakes() {
        InterfaceUsgsApi api = getUSGSRetrofitInstance().create(InterfaceUsgsApi.class);

        Observable<USGSModel> observable =
                api.getNearByEarthquakes(Constants.GEO_JSON,
                        Assistant.getFormatDateAfterDays(-10,"yyyy-MM-dd"),
                        Assistant.getFormatDateAfterDays(0,"yyyy-MM-dd"),
                        0, 0, 50, 50)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(USGSModel -> listener.onGetNearYouEarthquakes(getEarthquakesFromJsonResponse(USGSModel)),
                e -> {Log.d(Constants.LOG_KEY_MY_APP, "aly: " + e.getMessage()); e.printStackTrace();});
    }

    public void getMostRecentEarthquakes() {
        InterfaceUsgsApi api = getUSGSRetrofitInstance().create(InterfaceUsgsApi.class);

        Observable<USGSModel> observable =
                api.getMostRecentEarthquakes("geojson",
                        Assistant.getFormatDateAfterDays(-2,"yyyy-MM-dd"),
                        Assistant.getFormatDateAfterDays(0,"yyyy-MM-dd"), 20,
                        3 )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(USGSModel ->
                listener.onGetMostRecentEarthquakes(getEarthquakesFromJsonResponse(USGSModel)),
                e -> Log.d(Constants.LOG_KEY_MY_APP, "getMostRecentEarthquakes: " + e.toString()));
    }

    public void getSignificantRecentlyEarthquake() {
        InterfaceUsgsApi api = getUSGSRetrofitInstance().create(InterfaceUsgsApi.class);
        Observable <USGSModel> observable =
                api.getSignificantRecentlyEarthquake(Constants.GEO_JSON,
                        Assistant.getFormatDateAfterDays(-3,"yyyy-MM-dd"),
                        Assistant.getFormatDateAfterDays(0,"yyyy-MM-dd"),
                        100, "magnitude")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(o-> listener.onGetSignificantRecentlyEarthquake(getEarthquakesFromJsonResponse(o).get(0)),
                             e-> Log.d(Constants.LOG_KEY_MY_APP, "getSignificantRecentlyEarthquake: " + e.toString()));
    }

    public void getMapTabEarthquakes(){
        InterfaceUsgsApi api = getUSGSRetrofitInstance().create(InterfaceUsgsApi.class);

        Observable <USGSModel> observable = api.getMapEarthquakes(Constants.GEO_JSON,
                Assistant.getFormatDateAfterDays(-5,"yyyy-MM-dd"),
                Assistant.getFormatDateAfterDays(0,"yyyy-MM-dd"),
                100,3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(o-> listener.onGetMapTabEarthquakes(o));
    }

    public void getSearchMapEarthquakes(Bundle queryBundle) {
        InterfaceUsgsApi api = getUSGSRetrofitInstance().create(InterfaceUsgsApi.class);

        Observable<USGSModel> observable = api.getEarthquakes(Constants.GEO_JSON,
                queryBundle.getString(Constants.START_TIME),queryBundle.getString(Constants.END_TIME),
                queryBundle.getFloat(Constants.MIN_LATITUDE),queryBundle.getFloat(Constants.MIN_LONGITUDE),
                queryBundle.getFloat(Constants.MAX_LATITUDE),queryBundle.getFloat(Constants.MAX_LONGITUDE),
                queryBundle.getInt(Constants.LIMIT),queryBundle.getFloat(Constants.MIN_MAGNITUDE),
                queryBundle.getString(Constants.ORDER_BY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(o-> listener.onGetSearchTabEarthquakes(o));
    }

    public void getEarthquakesCounters(){
        InterfaceUsgsApi api = getUSGSRetrofitInstance().create(InterfaceUsgsApi.class);

        Observable<Integer> observable = Observable.merge(
                api.count(Assistant.getFormatDateAfterDays(-1,"yyyy-MM-dd"),Assistant.getFormatDateAfterDays(0,"yyyy-MM-dd"))
                ,api.count(Assistant.getFormatDateAfterDays(-7,"yyyy-MM-dd"),Assistant.getFormatDateAfterDays(0,"yyyy-MM-dd"))
                ,api.count(Assistant.getFormatDateAfterDays(-30,"yyyy-MM-dd"),Assistant.getFormatDateAfterDays(0,"yyyy-MM-dd")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<Integer>() {
            ArrayList<Integer> integers = new ArrayList<>();
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull Integer integer) { integers.add(integer);}

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
                listener.onGetEarthquakesCounters(integers);
            }
        });
    }

    public void getBiggestEarthquakes(){
        InterfaceUsgsApi api = getUSGSRetrofitInstance().create(InterfaceUsgsApi.class);
        Observable observable1 =  api.getEarthquakes(Constants.GEO_JSON,
                Assistant.getFormatDateAfterDays(-1,"yyyy-MM-dd"),
                Assistant.getFormatDateAfterDays(0,"yyyy-MM-dd"),
                -90,-180,90,180,5,1,Constants.MAGNITUDE);

        Observable observable2 =  api.getEarthquakes(Constants.GEO_JSON,
                Assistant.getFormatDateAfterDays(-7,"yyyy-MM-dd"),
                Assistant.getFormatDateAfterDays(0,"yyyy-MM-dd"),
                -90,-180,90,180,5,1,Constants.MAGNITUDE);


        Observable<USGSModel> observable = Observable.merge(observable1,observable2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<USGSModel>() {
            ArrayList<USGSModel> models =  new ArrayList<>();
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull USGSModel usgsModel) {
                models.add(usgsModel);
                Log.d(Constants.LOG_KEY_MY_APP, "onNext: " + usgsModel.features.get(0).properties.getPlace());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(Constants.LOG_KEY_MY_APP, "onError: " + e.getMessage());

            }

            @Override
            public void onComplete() {
                listener.onGetBiggestEarthquakes(models);
                Log.d(Constants.LOG_KEY_MY_APP, "onComplete: " + models.toString());
            }
        });

    }

    public void getNearestEarthquakes(){
        InterfaceUsgsApi api = getUSGSRetrofitInstance().create(InterfaceUsgsApi.class);
        Observable<USGSModel> observable1 = api.getEarthquakes(Constants.GEO_JSON,
                Assistant.getFormatDateAfterDays(-1,"yyyy-MM-dd"),
                Assistant.getFormatDateAfterDays(0,"yyyy-MM-dd"),0,0,
                50,60,1,1,Constants.MAGNITUDE);

        Observable<USGSModel> observable2 = api.getEarthquakes(Constants.GEO_JSON,
                Assistant.getFormatDateAfterDays(-1,"yyyy-MM-dd"),
                Assistant.getFormatDateAfterDays(0,"yyyy-MM-dd"),0,0,
                50,60,1,1,Constants.TIME);

        Observable<USGSModel> observable = Observable.merge(observable1,observable2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<USGSModel>() {
            ArrayList<USGSModel> usgsModels = new ArrayList<>();
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull USGSModel usgsModel) {
                usgsModels.add(usgsModel);
                Log.d(Constants.LOG_KEY_MY_APP, "onNext: " + usgsModel.getFeatures().get(0).getProperties().getPlace());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(Constants.LOG_KEY_MY_APP, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                listener.onGetNearestEarthquakes(usgsModels);
                Log.d(Constants.LOG_KEY_MY_APP, "onComplete: ");
            }
        });

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

    public static class Feature {
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

    public static class Geometry{
        private final float[] coordinates;

        public Geometry(float[] coordinates) {
            this.coordinates = coordinates;
        }

        public float[] getCoordinates() {
            return coordinates;
        }
    }

}
