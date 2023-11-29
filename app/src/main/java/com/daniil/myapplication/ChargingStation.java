package com.daniil.myapplication;

import com.google.android.gms.maps.model.LatLng;
/**
 * Клас ChargingStation представляє зарядну станцію для електромобіля на мапі Google.
 * Кожна зарядна станція має назву (title), короткий опис (snippet), координати місця розташування (location)
 * та колір маркера, який використовується для позначення на мапі (markerColor).
 */
public class ChargingStation {
    private String title;
    private String snippet;
    private LatLng location;
    private float markerColor;
    /**
     * Конструктор класу ChargingStation для ініціалізації об'єкта.
     *
     * @param title       Назва зарядної станції
     * @param snippet     Короткий опис
     * @param location    Координати місця розташування
     * @param markerColor Колір маркера
     */
    public ChargingStation(String title, String snippet, LatLng location, float markerColor) {
        this.title = title;
        this.snippet = snippet;
        this.location = location;
        this.markerColor = markerColor;
    }
    // Геттери та сеттери для доступу до полів класу
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
