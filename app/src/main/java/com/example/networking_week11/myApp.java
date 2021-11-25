package com.example.networking_week11;

import android.app.Application;

public class myApp extends Application {

    private NetworkingService networkingService = new NetworkingService();

    public JsonService getJsonService() {
        return jsonService;
    }

    private JsonService jsonService = new JsonService();


    public NetworkingService getNetworkingService() {
        return networkingService;
    }
}
