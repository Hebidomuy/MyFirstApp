package com.daniil.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Назва бази даних та версія
    private static final String DATABASE_NAME = "charging_station_reviews.db";
    private static final int DATABASE_VERSION = 1;

    // Назви таблиці та стовпців
    public static final String TABLE_REVIEWS = "reviews";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STATION_ID = "station_id";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_REVIEW_TEXT = "review_text";
    public static final String COLUMN_DATE_ADDED = "date_added";

    // Конструктор
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Створення таблиці
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REVIEWS_TABLE = "CREATE TABLE " + TABLE_REVIEWS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_STATION_ID + " TEXT,"
                + COLUMN_RATING + " REAL,"
                + COLUMN_REVIEW_TEXT + " TEXT,"
                + COLUMN_DATE_ADDED + " TEXT"
                + ")";
        db.execSQL(CREATE_REVIEWS_TABLE);
    }

    // Оновлення бази даних
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEWS);
        onCreate(db);
    }

    // Додавання відгуку
    public void addReview(String stationId, double rating, String reviewText, String dateAdded) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STATION_ID, stationId);
        values.put(COLUMN_RATING, rating);
        values.put(COLUMN_REVIEW_TEXT, reviewText);
        values.put(COLUMN_DATE_ADDED, dateAdded);
        db.insert(TABLE_REVIEWS, null, values);
        db.close();
    }

    // Отримання всіх відгуків для певної станції
    public Cursor getAllReviewsForStation(String stationId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_RATING, COLUMN_REVIEW_TEXT, COLUMN_DATE_ADDED};
        String selection = COLUMN_STATION_ID + "=?";
        String[] selectionArgs = {stationId};
        Cursor cursor = db.query(TABLE_REVIEWS, columns, selection, selectionArgs, null, null, null);

        // Перевіряємо, чи є дані в курсорі, перш ніж його повернути
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    // Оновлення відгуку
    public int updateReview(int id, double rating, String reviewText, String dateAdded) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RATING, rating);
        values.put(COLUMN_REVIEW_TEXT, reviewText);
        values.put(COLUMN_DATE_ADDED, dateAdded);
        return db.update(TABLE_REVIEWS, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    // Видалення відгуку
    public void deleteReview(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REVIEWS, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Метод для додавання відгуку та рейтингу до бази даних
    public boolean addReviewAndRating(String stationId, String reviewText, float rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("station_id", stationId);
        values.put("review_text", reviewText);
        values.put("rating", rating);
        values.put("date_added", System.currentTimeMillis()); // Додавання поточної дати та часу

        long result = db.insert("reviews", null, values);
        db.close();

        return result != -1; // Повертаємо true, якщо вставка пройшла успішно, інакше false
    }
}
