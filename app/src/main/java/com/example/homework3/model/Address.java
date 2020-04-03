package com.example.homework3.model;

import android.provider.Telephony;

import org.json.JSONException;
import org.json.JSONObject;

public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipCode;
    private Geolocation geoLocation;

    public Address(String street, String suite, String city, String zipCode, Geolocation geoLocation) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipCode = zipCode;
        this.geoLocation = geoLocation;
    }

    public Address() {
    }

    public Address fromJson(JSONObject addressJSON) throws JSONException {
        String street = addressJSON.getString("street");
        String suite = addressJSON.getString("suite");
        String city = addressJSON.getString("city");
        String zipcode = addressJSON.getString("zipcode");
        Geolocation geo = new Geolocation().fromJson(addressJSON.getJSONObject("geo"));

        Address newAddress = new Address(street, suite, city, zipCode, geo);
        return newAddress;
    }
}
