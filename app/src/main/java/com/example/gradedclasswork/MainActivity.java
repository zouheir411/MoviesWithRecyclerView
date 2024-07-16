package com.example.gradedclasswork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private List<Movie> MoviesList;
    private DatabaseHelper dbHelper;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);
        dbHelper = new DatabaseHelper(this);

        MoviesList = dbHelper.getAllMovies();
        adapter = new MovieAdapter(this, MoviesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditMovie.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            boolean isUpdate = data.getBooleanExtra("update", false);
            if (isUpdate) {
                Toast.makeText(this, "Song updated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Song added", Toast.LENGTH_SHORT).show();
            }
            MoviesList.clear();
            MoviesList.addAll(dbHelper.getAllMovies());
            adapter.notifyDataSetChanged();
        }
    }

}
