package com.example.earthquaketrackerclone.data;

public abstract class Constants {

    public static final int SPLASH_TIME_OUT = 4000;

    //latest fragment bundle keys
    public static final String KEY_EARTHQUAKES = "com.example.earthquaketrackerclone.pojo.earthquakes";

    //HTTP Connection request methods
    public static final String GET = "GET";

    //HTTP Log KEYS
    public static final String LOG_KEY_MY_APP = "My_App";


    //HTTP USGS JSON members keys
    public static final String KEY_FEATURES = "features";
    public static final String KEY_PROPERTIES = "properties" ;
    public static final String KEY_MAG = "mag" ;
    public static final String KEY_PLACE = "place" ;
    public static final String KEY_TIME = "time" ;
    public static final String KEY_URL = "url" ;
    public static final String KEY_DETAILS = "detail" ;

    public static final String KEY_NEAR_YOU = "com.example.earthquaketrackerclone.pojo.NearYou";
    public static final String KEY_MOST_RECENT = "com.example.earthquaketrackerclone.pojo.MostSignificant" ;
    public static final String KEY_SIGNIFICANT_RECENTLY = "com.example.earthquaketrackerclone.pojo.SignificantRecently";
    public static final String USGS_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson" ;
}
