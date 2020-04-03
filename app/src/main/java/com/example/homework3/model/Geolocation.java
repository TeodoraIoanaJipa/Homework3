package com.example.homework3.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Geolocation {
    private String latitude;
    private String longitude;

    public Geolocation(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Geolocation fromJson(JSONObject geolocationJSON) throws JSONException {
        String lat = geolocationJSON.getString("lat");
        String lng = geolocationJSON.getString("lng");
        Geolocation geolocation = new Geolocation(lat,lng);
        return geolocation;
    }

    public Geolocation() {
    }
}
