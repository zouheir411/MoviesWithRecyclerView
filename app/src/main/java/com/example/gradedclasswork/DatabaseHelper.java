package com.example.gradedclasswork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
 // DataBase Helper clas defining the respective db tables and using SQLite
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Movies.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Movies";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "Author";
    private static final String COLUMN_GENRE = "Genre";
    private static final String COLUMN_DURATION = "duration";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_GENRE + " TEXT, " +
                COLUMN_DURATION + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    // implementing the update query to update existent record
    public int updateMovie(Movie existingMovie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, existingMovie.getTitle());
        values.put(COLUMN_AUTHOR, existingMovie.getAuthor());
        values.put(COLUMN_GENRE, existingMovie.getGenre());
        values.put(COLUMN_DURATION, existingMovie.getDuration());
        int result = db.update(TABLE_NAME, values, COLUMN_TITLE + "=?", new String[]{existingMovie.getTitle()});
        db.close();
        return result;
    }


// Implementing the insert query as strings to be executed
    public long insertMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_AUTHOR, movie.getAuthor());
        values.put(COLUMN_GENRE, movie.getGenre());
        values.put(COLUMN_DURATION, movie.getDuration());
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

// Implementing the query that retrieves all movies in the table to be displayed in recycler
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                    String author = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTHOR));
                    String genre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENRE));
                    String duration = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DURATION));
                    movies.add(new Movie(title, author, genre, duration));
                }
            } finally {
                cursor.close();
            }
        }
        db.close();
        return movies;
    }
// Implementing the delete script
    public void deleteMovie(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_TITLE + "=?", new String[]{title});
        db.close();
    }
}