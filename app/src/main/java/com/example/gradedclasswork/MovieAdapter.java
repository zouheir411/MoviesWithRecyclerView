package com.example.gradedclasswork;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<Movie> AllMovies;
    private DatabaseHelper dbHelper;

    public MovieAdapter(Context context, List<Movie> MovieList) {
        this.context = context;
        this.AllMovies = MovieList;
        dbHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie song = AllMovies.get(position);
        holder.MovieTitle.setText(song.getTitle());
        holder.MovieAuthor.setText(song.getAuthor());
        holder.MovieGenre.setText(song.getGenre());
        holder.MovieDuration.setText(song.getDuration());

        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddEditMovie.class);
            intent.putExtra("title", song.getTitle());
            intent.putExtra("author", song.getAuthor());
            intent.putExtra("genre", song.getGenre());
            intent.putExtra("duration", song.getDuration());
            // Calling with request Code = 1 for ensuring the intent success
            ((MainActivity) context).startActivityForResult(intent, 1);
        });


        holder.btnDelete.setOnClickListener(v -> {
            dbHelper.deleteMovie(song.getTitle());
            AllMovies.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Song deleted", Toast.LENGTH_SHORT).show();
        });

        holder.btnOpenOnYouTube.setOnClickListener(v -> {
            String query = song.getTitle() + " " + song.getAuthor();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=" + query));
            context.startActivity(intent);
        });

        holder.btnShare.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareBody = "Check out this song: " + song.getTitle() + " by " + song.getAuthor();
            intent.putExtra(Intent.EXTRA_SUBJECT, song.getTitle());
            intent.putExtra(Intent.EXTRA_TEXT, shareBody);
            context.startActivity(Intent.createChooser(intent, "Share via"));
        });
    }

    @Override
    public int getItemCount() {
        return AllMovies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        private TextView MovieTitle, MovieAuthor, MovieGenre, MovieDuration;
        ImageButton btnEdit, btnDelete, btnOpenOnYouTube, btnShare;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            MovieTitle = itemView.findViewById(R.id.tvMovieTitle);
            MovieAuthor = itemView.findViewById(R.id.tvMovieAuthor);
            MovieGenre = itemView.findViewById(R.id.tvMovieGenre);
            MovieDuration = itemView.findViewById(R.id.tvMovieDuration);
            Button btnSave = itemView.findViewById(R.id.btnSave);
            Button btnCancel = itemView.findViewById(R.id.btnCancel);
        }
    }
}
