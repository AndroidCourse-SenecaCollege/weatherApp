package com.example.networking_week11;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkingService {

    private String cityURL = "http://gd.geobytes.com/AutoCompleteCity?&q=";
    private String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=" ;
    private String  weatherURL2 = "&appid=071c3ffca10be01d334505630d2c1a9c";
    private String iconURL = "https://openweathermap.org/img/wn/";
    private String iconURL2 = "@2x.png";


    public static ExecutorService networkExecutorService = Executors.newFixedThreadPool(4);
    public static Handler networkingHandler = new Handler(Looper.getMainLooper());

    interface NetworkingListener{
        void dataListener(String josnString);
        void imageListener(Bitmap image);
    }

    public NetworkingListener listener;

//http://gd.geobytes.com/AutoCompleteCity?&q=toro
    public void searchForCity(String cityChars){//tor // toro
        String urlString = cityURL + cityChars;
        connect(urlString);
    }

    public void getWeatherDataForCity(String city){
        String urlFoWeather = weatherURL + city + weatherURL2;
        connect(urlFoWeather);
    }


    public void getImageData(String icon){
        String iconurl = iconURL + icon + iconURL2;
        networkExecutorService.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(iconurl);
                    Bitmap bitmap = BitmapFactory.decodeStream((InputStream)url.getContent()) ;
                    networkingHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.imageListener(bitmap);
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    public void connect(String url){
           networkExecutorService.execute(new Runnable() {
               @Override
               public void run() {
                   HttpURLConnection  httpURLConnection = null;
                   // any code here will run in background thread
                   try {
                       String jsonString = "";
                       URL urlObject = new URL(url);
                       //jsonString = ((String)urlObject.getContent());
                       //Log.d("txt",jsonString);
                        httpURLConnection= (HttpURLConnection)urlObject.openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setRequestProperty("Content-Type","application/json");

                        InputStream in = httpURLConnection.getInputStream();
                        InputStreamReader reader = new InputStreamReader(in);
                        int inputStreamData = 0;
                        while ( (inputStreamData = reader.read()) != -1){
                            char current = (char)inputStreamData;
                            jsonString+= current;
                        } // json is ready
                       // I can send it to somewhere else to decode it

                       final String finalJsonString = jsonString;
                       networkingHandler.post(new Runnable() {
                           @Override
                           public void run() {
                               listener.dataListener(finalJsonString);
                           }
                       });


                   } catch (MalformedURLException e) {
                       e.printStackTrace();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   finally {
                       httpURLConnection.disconnect();
                   }


               }
           });
    }

}
