package com.example.movielist.ui.activities;

import android.os.Bundle;
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

        movieAdapter = new MovieAdapter(movieList, new MovieAdapter.OnMovieDeleteListener() {
            @Override
            public void onMovieDelete(Movie movie) {
                movieList.remove(movie);
                movieAdapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(movieAdapter);
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
}