package com.daniil.myapplication;

public class Comment {
    private int id;
    private float rating;
    private String reviewText;
    private String dateAdded;

    public Comment() {
        // Пустой конструктор
    }

    public Comment(int id, float rating, String reviewText, String dateAdded) {
        this.id = id;
        this.rating = rating;
        this.reviewText = reviewText;
        this.dateAdded = dateAdded;
    }

    public int getId() {
        return id;
    }

    public float getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public String getDateAdded() {
        return dateAdded;
    }
}
