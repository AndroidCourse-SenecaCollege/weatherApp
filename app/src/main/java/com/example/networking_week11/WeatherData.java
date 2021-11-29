package com.example.networking_week11;

public class WeatherData {

    String main = "";
    String icon ="";
    Double temp = 0.0;

    public WeatherData() {
    }

    public WeatherData(String main, String icon, Double temp) {
        this.main = main;
        this.icon = icon;
        this.temp = temp;
    }
}
