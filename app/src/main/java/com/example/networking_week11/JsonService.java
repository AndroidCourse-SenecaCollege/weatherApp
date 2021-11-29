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
            JSONArray json_cities = new JSONArray(json);
            for (int i = 0 ; i< json_cities.length(); i++){
            // "Torbert, LA, United States"
                String fullCityName = json_cities.getString(i);
                int index = fullCityName.indexOf(fullCityName,',');
                String cityName = substring(fullCityName,0,indexOf(fullCityName,','));
                String countryName = substring(fullCityName,indexOf(fullCityName,',') + 1, fullCityName.length());
                City c = new City(cityName,countryName);
                arrayList.add(c);
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
            JSONObject weathedDataObject = weatherArray.getJSONObject(0);
            String mainWeatherValue = weathedDataObject.getString("main");
            String iconValue = weathedDataObject.getString("icon");



            JSONObject tempObject = jsonObject.getJSONObject("main");
            Double temp = tempObject.getDouble("temp");

             weatherData = new WeatherData(mainWeatherValue,iconValue,temp);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weatherData;
    }


}
