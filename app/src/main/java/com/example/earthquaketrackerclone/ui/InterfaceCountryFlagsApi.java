package com.example.earthquaketrackerclone.ui;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InterfaceCountryFlagsApi {

    @GET("png/{countryName}")
    Observable<ResponseBody> getFlag(@Path("countryName") String countryName);
}
