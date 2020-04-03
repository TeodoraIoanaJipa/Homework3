package com.example.homework3.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    public Company() {
    }

    public Company fromJSON(JSONObject companyJSON) throws JSONException {
        String name = companyJSON.getString("name");
        String catchPhrase = companyJSON.getString("catchPhrase");
        String bs = companyJSON.getString("bs");
        Company company = new Company(name, catchPhrase, bs);

        return company;
    }
}
