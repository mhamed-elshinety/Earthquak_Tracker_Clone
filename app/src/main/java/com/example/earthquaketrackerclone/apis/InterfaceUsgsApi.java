package com.example.earthquaketrackerclone.apis;

import com.example.earthquaketrackerclone.pojo.USGSModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceUsgsApi {

    @GET("fdsnws/event/1/query")
    Observable<USGSModel> getNearByEarthquakes(@Query("format") String format,
                                               @Query("starttime") String startTime,
                                               @Query("endtime") String endTime,
                                               @Query("minlatitude") float minLatitude,
                                               @Query("minlongitude") float minLongitude,
                                               @Query("maxlatitude") float maxLatitude,
                                               @Query("maxlongitude") float maxLongitude);


    @GET("fdsnws/event/1/query")
    Observable<USGSModel> getMostRecentEarthquakes(@Query("format") String format,
                                                   @Query("starttime") String startTime,
                                                   @Query("endtime") String endTime,
                                                   @Query("limit") int limit,
                                                   @Query("minmagnitude") float minMagnitude);


    @GET("fdsnws/event/1/query")
    Observable<USGSModel> getSignificantRecentlyEarthquake(@Query("format") String format,
                                                           @Query("starttime") String startTime,
                                                           @Query("endtime") String endTime,
                                                           @Query("limit") int limit,
                                                           @Query("orderby") String orderBy);
    @GET("fdsnws/event/1/query")
    Observable<USGSModel> getMapEarthquakes(@Query("format") String format,
                                            @Query("starttime") String startTime,
                                            @Query("endtime") String endTime,
                                            @Query("limit") int limit,
                                            @Query("minmagnitude") float minMagnitude);

    @GET("fdsnws/event/1/query")
    Observable<USGSModel> getEarthquakes(@Query("format") String format,
                                         @Query("starttime") String startTime,
                                         @Query("endtime") String endTime,
                                         @Query("minlatitude") float minLatitude,
                                         @Query("minlongitude") float minLongitude,
                                         @Query("maxlatitude") float maxLatitude,
                                         @Query("maxlongitude") float maxLongitude,
                                         @Query("limit") int limit,
                                         @Query("minmagnitude") float minMagnitude,
                                         @Query("orderby") String orderBy);

    @GET("fdsnws/event/1/count")
    Observable<Integer> count(@Query("starttime") String startTime,
                              @Query("endtime") String endTime);




}
