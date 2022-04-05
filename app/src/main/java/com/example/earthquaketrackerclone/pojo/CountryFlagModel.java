package com.example.earthquaketrackerclone.pojo;

import android.graphics.Bitmap;

public class CountryFlagModel {
    private String countyName;
    private Bitmap countryFlag;

    public CountryFlagModel(String countyName, Bitmap countryFlag) {
        this.countyName = countyName;
        this.countryFlag = countryFlag;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public Bitmap getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(Bitmap countryFlag) {
        this.countryFlag = countryFlag;
    }
}
