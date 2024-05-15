package com.daniil.myapplication;

import static com.daniil.myapplication.database.DatabaseHelper.COLUMN_DATE_ADDED;
import static com.daniil.myapplication.database.DatabaseHelper.COLUMN_ID;
import static com.daniil.myapplication.database.DatabaseHelper.COLUMN_RATING;
import static com.daniil.myapplication.database.DatabaseHelper.COLUMN_REVIEW_TEXT;
import static com.daniil.myapplication.database.DatabaseHelper.COLUMN_STATION_ID;
import static com.daniil.myapplication.database.DatabaseHelper.TABLE_REVIEWS;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daniil.myapplication.database.DatabaseHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FragmentMap extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;
    private List<ChargingStation> chargingStations = new ArrayList<>();
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        // Ініціалізація та налаштування MapView
        mapView = rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        // Отримання екземпляра бази даних
        dbHelper = new DatabaseHelper(getActivity());
        db = dbHelper.getWritableDatabase();

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        // Налаштування початкового виду карти
        float zoomLevel = 10.0f;
        LatLng location = new LatLng(47.83941, 35.14583);  //  Координати (м. Запоріжжя)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel));

        // Додавання зарядних станцій
        addChargingStation("station1", 47.88833, 35.02139, "Electric Vehicle Charging Station", "Вулиця Талаліхіна, 75, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation("station2", 47.87497, 35.06959, "AutoEnterprise 271 Charging Station", "Бульвар Вінтера, 30, Запоріжжя, Запорізька область, 69061", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation("station3", 47.87436, 35.31275, "Electric Vehicle Charging Station", "Вулиця Зразкова, 2Б, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation("station4", 47.83258, 35.04417, "AE Charging Station", "м. Запоріжжя, вул. Заднепровская, 31 Запоріжжя Zaporizhia Oblast 69000, 69499", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation("station5", 47.84299, 35.10380, "UGV Chargers Charging Station", "Бульвар Шевченка, 56, Запоріжжя, Запорізька область, 69061", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation("station6", 47.84085, 35.10165, "UGV Chargers", "Бульвар Шевченка, 71, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation("station7", 47.83792, 35.10373, "AE Charging Station", "Вулиця Немировича-Данченка, 73, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation("station8", 47.84713, 35.13265, "Elecargroup", "Проспект Соборний, 147, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation("station9", 47.84386, 35.12180, "AE Charging Station", "Вулиця Перемоги, Запоріжжя, Запорізька область, 69000, 69499", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation("station10", 47.84305, 35.15343, "Electric Vehicle Charging Station", "Вулиця Перемоги, 90, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation("station11", 47.85546, 35.24466, "Electric Vehicle Charging Station", "Донецьке шосе, 7, Запоріжжя, Запорізька область, 69061", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation("station12", 47.84439, 35.23981, "Electric Vehicle Charging Station", "Автомагістраль Харків-Сімферополь, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation("station13", 47.84927, 35.22670, "AutoEnterprise 329", "155А,, Чарівна вулиця, 155А, Запоріжжя, Запорізька область", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation("station14", 47.83656, 35.20971, "Elecargroup Charging Station", "Вулиця Іванова, 24, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation("station15", 47.83689, 35.18591, "AutoEnterprise 272", "Вулиця Іванова, 1, Запоріжжя, Запорізька область, 69061", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation("station16", 47.82823, 35.17453, "AE Charging Station", "м, вулиця Олександрівська, 75, Запоріжжя, Запорізька область, 69002", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation("station17", 47.82008, 35.16530, "UGV Chargers. Електрозаправна станція", "Вулиця Фортечна, 2, Запоріжжя, Запорізька область, 69001", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation("station18", 47.81486, 35.18424, "Electric Vehicle Charging Station", "Вулиця Святого Миколая, 6, Запоріжжя, Запорізька область, 69061", BitmapDescriptorFactory.HUE_YELLOW);
        addChargingStation("station19", 47.76358, 35.20721, "Зарядна станція для електромобіля", "Технікумівська вулиця, 6А, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_BLUE);
        addChargingStation("station20", 47.76348, 35.20775, "AutoEnterprise 326", "Технікумівська вулиця, 6А, Запоріжжя, Запорізька область, 69000", BitmapDescriptorFactory.HUE_YELLOW);

        addMarkers();

        // Установка обробника натискання на маркери
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Отримуємо ChargingStation з тега маркера
                ChargingStation station = (ChargingStation) marker.getTag();
                if (station != null) {
                    // Показуємо інформацію про станцію
                    showStationInfo(station);
                    return true; // Обробили подію натискання на маркері
                }
                return false; // Не вдалося обробити подію натискання на маркері
            }
        });
    }

    // Додавання інформації про зарядні станції
    private void addChargingStation(String stationId, double latitude, double longitude, String title, String snippet, float markerColor) {
        chargingStations.add(new ChargingStation(stationId, title, snippet, new LatLng(latitude, longitude), markerColor));
    }

    // Додавання маркерів на карту
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

    // Метод для показу інформації про станцію
    private void showStationInfo(ChargingStation station) {
        // Отримуємо коментарі для станції
        List<Comment> comments = getCommentsForStation(station.getStationId());

        // Обчислюємо середню оцінку
        float averageRating = calculateAverageRating(comments);

        // Створюємо рядок для відображення середньої оцінки
        String ratingString = String.format(Locale.getDefault(), "Середня оцінка станції: %.1f", averageRating);

        // Створюємо діалогове вікно
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(station.getTitle())
                .setMessage(station.getSnippet() + "\n" + ratingString)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // После закриття діалогового вікна створюємо інтент для запуску ReviewActivity
                        Intent intent = new Intent(getActivity(), ReviewActivity.class);
                        intent.putExtra("selectedStationId", station.getStationId()); // Передача ідентифікатора обраної станції
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    // Метод для відображення діалогу з відгуком та рейтингом
    private void showReviewDialog(String stationId) {
        // Проверяем, что stationId не null и не пустой
        if (stationId != null && !stationId.isEmpty()) {
            // Создание Intent для запуска ReviewActivity
            Intent intent = new Intent(getActivity(), ReviewActivity.class);
            intent.putExtra("station_id", stationId); // Передача ідентифікатора обраної заправки
            startActivity(intent);
        } else {
            // Якщо stationId некоректний, виводимо повідомлення про помилку
            Toast.makeText(getActivity(), "Ошибка: Некоректний ідентифікатор станції", Toast.LENGTH_SHORT).show();
        }
    }

    // Метод для додавання відгуку та рейтингу до бази даних
    private void addReviewAndRating(String stationId, String reviewText, float rating) {
        // Використання інструкції try-with-resources для автоматичного закриття бази даних
        try (DatabaseHelper dbHelper = new DatabaseHelper(getActivity())) {
            dbHelper.addReviewAndRating(stationId, reviewText, rating);
            // Повідомлення користувача про успішне додавання відгуку та рейтингу
            Toast.makeText(getActivity(), "Відгук успішно додано!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Обробка винятків, якщо щось пішло не так
            e.printStackTrace();
        }
    }

    // Метод який обчислює середню оцінку на основі коментарів для станції
    private float calculateAverageRating(List<Comment> comments) {
        float sum = 0;
        if (comments != null && !comments.isEmpty()) {
            for (Comment comment : comments) {
                sum += comment.getRating();
            }
            return sum / comments.size();
        } else {
            return 0; // Повертаємо 0, якщо немає коментарів
        }
    }

    // Метод отримання коментарів для певної станції в базі даних.
    public List<Comment> getCommentsForStation(String stationId) {
        List<Comment> comments = new ArrayList<>();
        // Отримання бази даних для читання
        SQLiteDatabase db = this.db;

        // Визначення стовпців, які необхідно отримати
        String[] columns = {COLUMN_ID, COLUMN_RATING, COLUMN_REVIEW_TEXT, COLUMN_DATE_ADDED};

        // Визначення умови вибірки - станція з вказаним ідентифікатором
        String selection = COLUMN_STATION_ID + "=?";
        String[] selectionArgs = {stationId};

        // Виконання запиту до бази даних
        Cursor cursor = db.query(TABLE_REVIEWS, columns, selection, selectionArgs, null, null, null);

        // Перевірка, чи є дані в курсорі
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Отримання даних з курсора
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                float rating = cursor.getFloat(cursor.getColumnIndex(COLUMN_RATING));
                String reviewText = cursor.getString(cursor.getColumnIndex(COLUMN_REVIEW_TEXT));
                long timestamp = cursor.getLong(cursor.getColumnIndex(COLUMN_DATE_ADDED));

                // Додавання коментаря до списку
                comments.add(new Comment(id, rating, reviewText, String.valueOf(timestamp)));
            } while (cursor.moveToNext());

            // Закриття курсора після використання
            cursor.close();
        }

        // Повертаємо список коментарів для станції
        return comments;
    }

    // Реалізація методів життєвого циклу MapView
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        dbHelper.close();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
