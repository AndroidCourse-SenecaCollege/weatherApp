package com.example.networking_week11;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CitiesAdapter.cityClickListner,
NetworkingService.NetworkingListener{

    ArrayList<City> cities = new ArrayList<City>();
    RecyclerView recyclerView;
    CitiesAdapter adapter;
    NetworkingService networkingManager;
    JsonService jsonService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkingManager = ((myApp)getApplication()).getNetworkingService();
        jsonService = ((myApp)getApplication()).getJsonService();
        networkingManager.listener = this;
        recyclerView = findViewById(R.id.citiesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new CitiesAdapter(this,cities);
        recyclerView.setAdapter(adapter);
        setTitle("Search for new cities..");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchViewMenuItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) searchViewMenuItem.getActionView();
        String searchFor = searchView.getQuery().toString();
        if (!searchFor.isEmpty()) {
            searchView.setIconified(false);
            searchView.setQuery(searchFor, false);
        }

        searchView.setQueryHint("Search for cities");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {// when the user clicks enter
                Log.d("query", query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {// after each char
                if (newText.length() >= 3) {
                    // search for cities
                    networkingManager.searchForCity(newText);
                }
                else {
                    cities = new ArrayList<>(0);
                    adapter.cityList = cities;
                    adapter.notifyDataSetChanged();

                }
                return false;
            }
        });
        return true;
    }

    @Override
    public void cityClicked(City selectedCity) {
        Intent intent = new Intent(this,WeatherActivity.class);
        intent.putExtra("cityName",selectedCity.getCityName());
        startActivity(intent);
    }

    @Override
    public void dataListener(String josnString) {
        cities =  jsonService.getCitiesFromJSON(josnString);
        adapter = new CitiesAdapter(this,cities);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void imageListener(Bitmap image) {

    }
}
