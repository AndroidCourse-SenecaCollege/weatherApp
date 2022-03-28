package com.example.networking_week11;

public class City {

    private int id;
    private String cityName;// Toronto , On, Canada
    private String country;

    City(){}
    public City(String city, String country){
        this.cityName = city;
        this.country = country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }

    public int getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}

