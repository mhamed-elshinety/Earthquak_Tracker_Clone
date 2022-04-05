package com.example.earthquaketrackerclone.pojo;

public class EarthquakeModel {

    private double mag;
    private long time;
    private String place;
    private String url;
    private CountryFlagModel flagModel;

    public EarthquakeModel(double mag, long time, String place, String url) {
        this.mag = mag;
        this.time = time;
        this.place = place;
        this.url = url;
    }

    public EarthquakeModel(double mag, long time, String place, String url, CountryFlagModel flagModel) {
        this.mag = mag;
        this.time = time;
        this.place = place;
        this.url = url;
        this.flagModel = flagModel;
    }

    public CountryFlagModel getFlagModel() {
        return flagModel;
    }

    public void setFlagModel(CountryFlagModel flagModel) {
        this.flagModel = flagModel;
    }

    public double getMag() {
        return mag;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}





