package com.example.movielist.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movielist.R;
import com.example.movielist.data.models.Movie;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final List<Movie> movieList;
    private final OnMovieDeleteListener listener;

    public MovieAdapter(List<Movie> movieList, OnMovieDeleteListener listener) {
        this.movieList = movieList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.titleText.setText(movie.getTitle());
        holder.genreText.setText(movie.getGenre());
        holder.yearText.setText(String.valueOf(movie.getYear()));
        holder.ratingText.setText(String.valueOf(movie.getRating()));

        holder.deleteButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMovieDelete(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, genreText, yearText, ratingText;
        ImageView deleteButton;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            genreText = itemView.findViewById(R.id.genreText);
            yearText = itemView.findViewById(R.id.yearText);
            ratingText = itemView.findViewById(R.id.ratingText);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    public interface OnMovieDeleteListener {
        void onMovieDelete(Movie movie);
    }
}