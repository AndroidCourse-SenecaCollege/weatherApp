package com.example.networking_week11;

import static android.text.TextUtils.indexOf;
import static android.text.TextUtils.substring;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonService {

    public ArrayList<City> getCitiesFromJSON(String json)  {
    ArrayList<City> arrayList = new ArrayList<>(0);
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0 ; i < jsonArray.length();i++){
                String city = jsonArray.getString(i);
                City c = new City();
                c.setCityName(city);
                arrayList.add(c);
                // if I need the city name and province and Country name
                // I have to use substring function
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public WeatherData getWeatherData(String jsonString) {
        WeatherData weatherData = new WeatherData();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray weatherArray = jsonObject.getJSONArray("weather");
            JSONObject weatherArrayFirstObject = weatherArray.getJSONObject(0);
            weatherData.main = weatherArrayFirstObject.getString("main");
            weatherData.icon = weatherArrayFirstObject.getString("icon");
            JSONObject mainObject = jsonObject.getJSONObject("main");
            weatherData.setTemp( mainObject.getDouble("temp"));
            weatherData.setFeels_like(   mainObject.getDouble("feels_like"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weatherData;
    }


}
