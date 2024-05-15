package com.daniil.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daniil.myapplication.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends AppCompatActivity {

    private EditText editTextReview;
    private RatingBar ratingBar;
    private Button buttonSubmitReview;
    private Button buttonExitReview;
    private Button buttonViewPreviousComments;
    private DatabaseHelper dbHelper;
    private String selectedStationId;

    // Оголошення RecyclerView та адаптера
    private RecyclerView recyclerViewComments;
    private CommentsAdapter commentsAdapter;
    private List<Comment> commentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_review_dialog);

        // Отримання ідентифікатора обраної станції з Intent
        Intent intent = getIntent();
        selectedStationId = intent.getStringExtra("selectedStationId");

        editTextReview = findViewById(R.id.editTextReview);
        ratingBar = findViewById(R.id.ratingBar);
        buttonSubmitReview = findViewById(R.id.buttonSubmitReview);
        buttonExitReview = findViewById(R.id.buttonExitReview);
        buttonViewPreviousComments = findViewById(R.id.buttonViewPreviousComments);

        dbHelper = new DatabaseHelper(this);

        // Ініціалізація списку коментарів
        commentsList = new ArrayList<>();

        // Оголошення RecyclerView та адаптера
        recyclerViewComments = findViewById(R.id.recyclerViewComments);
        commentsAdapter = new CommentsAdapter(this, commentsList);
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewComments.setAdapter(commentsAdapter);

        // Обробник натискання на кнопку "Переглянути попередні коментарі"
        buttonViewPreviousComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Отримуємо список попередніх коментарів з бази даних
                commentsList = getPreviousCommentsFromDatabase();

                // Перевіряємо, чи є коментарі
                if (commentsList.isEmpty()) {
                    // Якщо немає коментарів, показуємо повідомлення
                    Toast.makeText(ReviewActivity.this, "Немає попередніх коментарів", Toast.LENGTH_SHORT).show();
                } else {
                    // Якщо є коментарі, оновлюємо дані у адаптері та показуємо RecyclerView
                    commentsAdapter.setComments(commentsList);
                    recyclerViewComments.setVisibility(View.VISIBLE);

                    // Повідомляємо адаптер про зміни
                    commentsAdapter.notifyDataSetChanged();
                }
            }
        });

        // Обробник натискання на кнопку "Вийти"
        buttonExitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Просто закриваємо активність
                finish();
            }
        });

        // Обробник натискання на кнопку "Відправити відгук"
        buttonSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Отримуємо текст відгуку та рейтинг
                String reviewText = editTextReview.getText().toString();
                float rating = ratingBar.getRating();

                // Перевіряємо, чи відгук та рейтинг вказані
                if (reviewText.trim().isEmpty()) {
                    // Якщо текст відгуку порожній, показуємо помилку
                    Toast.makeText(ReviewActivity.this, "Введіть відгук", Toast.LENGTH_SHORT).show();
                } else if (rating == 0) {
                    // Якщо рейтинг не вказаний, показуємо помилку
                    Toast.makeText(ReviewActivity.this, "Вкажіть рейтинг", Toast.LENGTH_SHORT).show();
                } else {
                    // Всі дані заповнені, додаємо відгук та рейтинг у базу даних
                    boolean isReviewAdded = dbHelper.addReviewAndRating(selectedStationId, reviewText, rating);

                    if (isReviewAdded) {
                        // Якщо відгук успішно додано, виводимо повідомлення про успішне додавання
                        Toast.makeText(ReviewActivity.this, "Відгук успішно доданий", Toast.LENGTH_SHORT).show();
                        // Закриваємо екран відгуків та оцінок
                        finish();
                    } else {
                        // Якщо сталася помилка при додаванні відгуку, виводимо повідомлення про помилку
                        Toast.makeText(ReviewActivity.this, "Помилка при додаванні відгуку", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // Метод для отримання попередніх коментарів з бази даних
    private List<Comment> getPreviousCommentsFromDatabase() {
        List<Comment> comments = new ArrayList<>();
        // Отримуємо всі відгуки для заданої станції
        Cursor cursor = dbHelper.getAllReviewsForStation(selectedStationId);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Перевіряємо наявність стовпців у вибірці перед отриманням їх значень
                if (cursor.getColumnIndex(DatabaseHelper.COLUMN_ID) != -1 &&
                        cursor.getColumnIndex(DatabaseHelper.COLUMN_RATING) != -1 &&
                        cursor.getColumnIndex(DatabaseHelper.COLUMN_REVIEW_TEXT) != -1 &&
                        cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE_ADDED) != -1) {

                    // Отримуємо значення стовпців
                    int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                    float rating = cursor.getFloat(cursor.getColumnIndex(DatabaseHelper.COLUMN_RATING));
                    String reviewText = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_REVIEW_TEXT));
                    String dateAdded = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE_ADDED));
                    // Створюємо об'єкт коментаря та додаємо його до списку
                    Comment comment = new Comment(id, rating, reviewText, dateAdded);
                    comments.add(comment);
                }
            } while (cursor.moveToNext());
            // Закриваємо курсор
            cursor.close();
        }
        return comments;
    }
}
