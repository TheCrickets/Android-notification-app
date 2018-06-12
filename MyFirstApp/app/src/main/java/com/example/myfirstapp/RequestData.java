package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.List;

/**
 * RequestData class is used for creating objects that contain the user's preferences and location
 * the object(s) will be transformed into a json String
 */
public class RequestData
{
    private List<String> disasters;
    private double longitude;
    private double latitude;

    public List<String> getDisasters() { return disasters; }
    public void setDisasters(List<String> disasters) { this.disasters = disasters; }
    public void addDisaster(String disaster) { disasters.add(disaster); }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public RequestData(List<String> disasters, double longitude, double latitude)
    {
        this.disasters = disasters;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    RequestData()
    {
        disasters = new ArrayList<>();
        longitude = 0.0;
        latitude = 0.0;
    }
}
