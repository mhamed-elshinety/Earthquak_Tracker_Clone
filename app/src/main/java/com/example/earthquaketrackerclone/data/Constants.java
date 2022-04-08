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

    //api query keys
    public static final String START_TIME ="start time" ;
    public static final String END_TIME = "end time";
    public static final String MIN_LATITUDE = "min lat";
    public static final String MIN_LONGITUDE = "min long";
    public static final String MAX_LONGITUDE = "max lat";
    public static final String MAX_LATITUDE = "max long";
    public static final String LIMIT = "limit";
    public static final String MIN_MAGNITUDE = "min mag" ;
    public static final String ORDER_BY = "order by" ;
    public static final String GEO_JSON = "geojson";

    public static String KEY_Context= "context";
}
