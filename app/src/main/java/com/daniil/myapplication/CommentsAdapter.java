package com.daniil.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentViewHolder> {

    private List<Comment> commentsList;
    private Context context;

    // Конструктор
    public CommentsAdapter(Context context, List<Comment> commentsList) {
        this.context = context;
        this.commentsList = commentsList;
    }

    // Метод для встановлення списку коментарів
    public void setComments(List<Comment> commentsList) {
        this.commentsList = commentsList;
        notifyDataSetChanged();
    }

    // Оголошення ViewHolder
    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewRating;
        public RatingBar ratingBar;
        public TextView textViewComment;
        public TextView textViewDateAddedTitle;
        public TextView textViewDateAdded;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            textViewComment = itemView.findViewById(R.id.textViewCommentText);
            textViewDateAddedTitle = itemView.findViewById(R.id.textViewDateAddedTitle);
            textViewDateAdded = itemView.findViewById(R.id.textViewDateAdded);
        }
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        // Отримуємо коментар за позицією
        Comment comment = commentsList.get(position);

        // Встановлюємо текст коментаря
        holder.textViewComment.setText(comment.getReviewText());

        // Встановлюємо оцінку та видимість відповідних елементів
        if (comment.getRating() > 0) {
            holder.textViewRating.setVisibility(View.VISIBLE);
            holder.ratingBar.setVisibility(View.VISIBLE);
            holder.ratingBar.setRating(comment.getRating());
        } else {
            holder.textViewRating.setVisibility(View.GONE);
            holder.ratingBar.setVisibility(View.GONE);
        }

        // Встановлюємо дату та видимість відповідних елементів
        if (comment.getDateAdded() != null && !comment.getDateAdded().isEmpty()) {
            holder.textViewDateAddedTitle.setVisibility(View.VISIBLE);
            holder.textViewDateAdded.setVisibility(View.VISIBLE);

            // Перетворення мілісекунд в Date
            Date date = new Date(Long.parseLong(comment.getDateAdded()));

            // Форматування дати згідно з новим форматом
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
            String formattedDate = outputFormat.format(date);

            // Встановлення форматованої дати в текстове поле
            holder.textViewDateAdded.setText(formattedDate);
        } else {
            holder.textViewDateAddedTitle.setVisibility(View.GONE);
            holder.textViewDateAdded.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }
}
