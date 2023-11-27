package com.daniil.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;
    private List<ChargingStation> chargingStations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        float zoomLevel = 10.0f;

        LatLng location = new LatLng(47.83941, 35.14583);  //  Координати (м. Запоріжжя)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel));

        addChargingStation(47.88833, 35.02139, "Electric Vehicle Charging Station", "Вулиця Талаліхіна, 75, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation(47.87497, 35.06959, "AutoEnterprise 271 Charging Station", "Бульвар Вінтера, 30, Запоріжжя, Запорізька область, 69061", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation(47.87436, 35.31275, "Electric Vehicle Charging Station", "Вулиця Зразкова, 2Б, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation(47.83258, 35.04417, "AE Charging Station", "м. Запоріжжя, вул. Заднепровская, 31 Запоріжжя Zaporizhia Oblast 69000, 69499", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation(47.84299, 35.10380, "UGV Chargers Charging Station", "Бульвар Шевченка, 56, Запоріжжя, Запорізька область, 69061", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation(47.84085, 35.10165, "UGV Chargers", "Бульвар Шевченка, 71, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation(47.83792, 35.10373, "AE Charging Station", "Вулиця Немировича-Данченка, 73, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation(47.84713, 35.13265, "Elecargroup", "Проспект Соборний, 147, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation(47.84386, 35.12180, "AE Charging Station", "Вулиця Перемоги, Запоріжжя, Запорізька область, 69000, 69499", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation(47.84305, 35.15343, "Electric Vehicle Charging Station", "Вулиця Перемоги, 90, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation(47.85546, 35.24466, "Electric Vehicle Charging Station", "Донецьке шосе, 7, Запоріжжя, Запорізька область, 69061", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation(47.84439, 35.23981, "Electric Vehicle Charging Station", "Автомагістраль Харків-Сімферополь, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation(47.84927, 35.22670, "AutoEnterprise 329", "155А,, Чарівна вулиця, 155А, Запоріжжя, Запорізька область", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation(47.83656, 35.20971, "Elecargroup Charging Station", "Вулиця Іванова, 24, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation(47.83689, 35.18591, "AutoEnterprise 272", "Вулиця Іванова, 1, Запоріжжя, Запорізька область, 69061", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation(47.82823, 35.17453, "AE Charging Station", "м, вулиця Олександрівська, 75, Запоріжжя, Запорізька область, 69002", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation(47.82008, 35.16530, "UGV Chargers. Електрозаправна станція", "Вулиця Фортечна, 2, Запоріжжя, Запорізька область, 69001", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation(47.81486, 35.18424, "Electric Vehicle Charging Station", "Вулиця Святого Миколая, 6, Запоріжжя, Запорізька область, 69061", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation(47.76358, 35.20721, "Зарядна станція для електромобіля", "Технікумівська вулиця, 6А, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation(47.76348, 35.20775, "AutoEnterprise 326", "Технікумівська вулиця, 6А, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_YELLOW);

        addMarkers();
    }

    private void addChargingStation(double latitude, double longitude, String title, String snippet, float markerColor) {
        chargingStations.add(new ChargingStation(title, snippet, new LatLng(latitude, longitude), markerColor));
    }

    private void addMarkers() {
        for (ChargingStation station : chargingStations) {
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(station.getLocation())
                    .title(station.getTitle())
                    .snippet(station.getSnippet())
                    .icon(BitmapDescriptorFactory.defaultMarker(station.getMarkerColor()));

            Marker marker = googleMap.addMarker(markerOptions);
            marker.setTag(station);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
