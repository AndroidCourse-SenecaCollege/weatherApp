package com.example.networking_week11;

import static android.text.TextUtils.indexOf;
import static android.text.TextUtils.substring;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;

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


}
