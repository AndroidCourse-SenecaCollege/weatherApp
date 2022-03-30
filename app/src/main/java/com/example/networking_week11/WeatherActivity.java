package com.example.networking_week11;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WeatherActivity extends AppCompatActivity
        implements NetworkingService.NetworkingListener {

    NetworkingService networkingManager;
    JsonService jsonService;
    TextView cityText;
    TextView weatherText;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        String cityName = getIntent().getStringExtra("city");

        networkingManager = ((myApp)getApplication()).getNetworkingService();
        jsonService = ((myApp)getApplication()).getJsonService();
        networkingManager.listener = this;

        networkingManager.getWeatherDataForCity(cityName);

        cityText = findViewById(R.id.cityName);
        weatherText = findViewById(R.id.weather);
        imageView = findViewById(R.id.image);
        cityText.setText(cityName);
    }

    @Override
    public void dataListener(String josnString) {
        // json data from weather API
        // parse json
        WeatherData data = jsonService.getWeatherData(josnString);
        weatherText.setText(data.main + " : " +  String.format("%.2f",data.temp)  + ": feels like " + String.format("%.2f",data.feels_like));
        networkingManager.getImageData(data.icon);//
    }

    @Override
    public void imageListener(Bitmap image) {
        imageView.setImageBitmap(image);
    }
}
