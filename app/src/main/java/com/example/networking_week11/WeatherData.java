package com.example.networking_week11;

public class WeatherData {

    String main = "";
    String icon ="";
    double temp = 0.0;
    double feels_like = 0.0;

    public WeatherData(String main, String icon, double temp, double fl) {
        this.main = main;
        this.icon = icon;
        this.temp = temp;
        this.feels_like = fl;
    }

    public WeatherData() {

    }
}
