package com.example.gradedclasswork;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class AddEditMovie extends AppCompatActivity {
    private TextView MovieTitle, MovieAuthor, MovieGenre, MovieDuration;
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieving the components to be used later on :
        setContentView(R.layout.activity_add_edit_recipe);
        MovieTitle = findViewById(R.id.tvMovieTitle);
        MovieAuthor = findViewById(R.id.tvMovieAuthor);
        MovieGenre = findViewById(R.id.tvMovieGenre);
        MovieDuration = findViewById(R.id.tvMovieDuration);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);

        // Creating an intent so we can get the movie if it is present already
        Intent intent = getIntent();

        // Getting the movie details placed in their respective places
        if (intent != null) {
            if (intent != null && intent.hasExtra("title")) {
                MovieTitle.setText(intent.getStringExtra("title"));
                MovieAuthor.setText(intent.getStringExtra("Author"));
                MovieGenre.setText(intent.getStringExtra("Genre"));
                MovieDuration.setText(intent.getStringExtra("duration"));
                btnSave.setText("Update");
            }
        }
        // Mapping the functions to the respective components
        btnSave.setOnClickListener(v -> saveMovie());
        btnCancel.setOnClickListener(v -> finish());

    }

    // Saving the movie + handling the null cases for preventing the creation of a null data field-Object
    private void saveMovie() {
        String title = MovieTitle.getText().toString().trim();
        String author = MovieAuthor.getText().toString().trim();
        String genre = MovieGenre.getText().toString().trim();
        String duration = MovieDuration.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(author) || TextUtils.isEmpty(genre) || TextUtils.isEmpty(duration)) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Movie newMovie = new Movie(title, author, genre, duration);

        boolean isExistingmovie = getIntent() != null && getIntent().hasExtra("title");

        long result;
        if (isExistingmovie) {
            result = dbHelper.updateMovie(newMovie);
        } else {
            result = dbHelper.insertMovie(newMovie);
        }

        if (result == -1) {
            Toast.makeText(this, "Movie Saving Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("update", isExistingmovie);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}
