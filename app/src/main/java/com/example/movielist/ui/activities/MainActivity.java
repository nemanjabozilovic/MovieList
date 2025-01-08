package com.example.movielist.ui.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielist.R;
import com.example.movielist.data.models.Movie;
import com.example.movielist.ui.adapters.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUIElements();

        movieList = getMovieList();

        movieAdapter = new MovieAdapter(movieList, movie -> {
            movieList.remove(movie);
            movieAdapter.notifyDataSetChanged();
        });

        recyclerView.setAdapter(movieAdapter);

        findViewById(R.id.addMovieButton).setOnClickListener(v -> showAddMovieDialog());
    }

    private List<Movie> getMovieList() {
        List<Movie> movieList = new ArrayList<>();

        movieList.add(new Movie("Inception", "Sci-Fi", 2010, 8.8));
        movieList.add(new Movie("Titanic", "Romance", 1997, 7.8));
        movieList.add(new Movie("The Dark Knight", "Action", 2008, 9.0));
        movieList.add(new Movie("The Godfather", "Crime", 1972, 9.2));
        movieList.add(new Movie("Pulp Fiction", "Crime", 1994, 8.9));
        movieList.add(new Movie("Forrest Gump", "Drama", 1994, 8.8));
        movieList.add(new Movie("The Shawshank Redemption", "Drama", 1994, 9.3));
        movieList.add(new Movie("The Matrix", "Sci-Fi", 1999, 8.7));
        movieList.add(new Movie("The Avengers", "Action", 2012, 8.0));
        movieList.add(new Movie("Gladiator", "Action", 2000, 8.5));

        return movieList;
    }

    private void initializeUIElements() {
        recyclerView = findViewById(R.id.movieListContainer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showAddMovieDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add a New Movie");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(16, 16, 16, 16);

        final EditText titleInput = new EditText(this);
        titleInput.setHint("Movie Title");
        layout.addView(titleInput);

        final EditText genreInput = new EditText(this);
        genreInput.setHint("Genre");
        layout.addView(genreInput);

        final EditText yearInput = new EditText(this);
        yearInput.setHint("Year");
        yearInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(yearInput);

        final EditText ratingInput = new EditText(this);
        ratingInput.setHint("Rating (0.0 - 10.0)");
        ratingInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        layout.addView(ratingInput);

        builder.setView(layout);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String title = titleInput.getText().toString().trim();
            String genre = genreInput.getText().toString().trim();
            String yearString = yearInput.getText().toString().trim();
            String ratingString = ratingInput.getText().toString().trim();

            if (title.isEmpty() || genre.isEmpty() || yearString.isEmpty() || ratingString.isEmpty()) {
                Toast.makeText(this, "All fields must be filled out!", Toast.LENGTH_SHORT).show();
                return;
            }

            int year = Integer.parseInt(yearString);
            double rating = Double.parseDouble(ratingString);

            movieList.add(new Movie(title, genre, year, rating));
            movieAdapter.notifyDataSetChanged();

            Toast.makeText(this, "Movie added successfully!", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}