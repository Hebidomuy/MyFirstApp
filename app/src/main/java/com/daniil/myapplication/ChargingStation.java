package com.daniil.myapplication;

import com.google.android.gms.maps.model.LatLng;

public class ChargingStation {
    private String title;
    private String snippet;
    private LatLng location;
    private float markerColor;

    public ChargingStation(String title, String snippet, LatLng location, float markerColor) {
        this.title = title;
        this.snippet = snippet;
        this.location = location;
        this.markerColor = markerColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public float getMarkerColor() {
        return markerColor;
    }

    public void setMarkerColor(float markerColor) {
        this.markerColor = markerColor;
    }
}
