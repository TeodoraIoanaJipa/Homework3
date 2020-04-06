package com.example.homework3.model;


import org.json.JSONException;
import org.json.JSONObject;


public class User {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public User(Integer id, String name, String username, String email, Address address, String phone, String website, Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

    public User() {
    }

    public User fromJSON(JSONObject userJSON) throws JSONException {
//        JSONObject userJSON = new JSONObject(data);

        Integer id = userJSON.getInt("id");
        String name = userJSON.getString("name");
        String username = userJSON.getString("username");
        String email = userJSON.getString("email");
        String phone = userJSON.getString("phone");
        String website = userJSON.getString("website");
        Address address = new Address().fromJson(userJSON.getJSONObject("address"));
        Company company = new Company().fromJSON(userJSON.getJSONObject("company"));

        User user = new User(id, name, username, email, address, phone, website, company);
        return user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
