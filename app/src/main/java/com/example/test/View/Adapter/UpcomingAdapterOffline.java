package com.example.test.View.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.test.Database.Entity.UpComingMovie;
import com.example.test.R;

import java.util.List;

//Adapter class for manage Upcoming movies info when is online
public class UpcomingAdapterOffline extends RecyclerView.Adapter<UpcomingAdapterOffline.MovieViewHolder> {


    private List<UpComingMovie> movieList;
    private OnMovieClickCallback callback;

    public UpcomingAdapterOffline(List<UpComingMovie> movieList, OnMovieClickCallback callback){
        this.movieList = movieList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie,
                viewGroup, false);
        return new UpcomingAdapterOffline.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.bind(movieList.get(i));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    //Inner class for apply ViewHolder pattern
    class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView releaseDate, title, rating;
        ImageView image;
        UpComingMovie movie;


        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            releaseDate = itemView.findViewById(R.id.releaseDateMovie);
            title = itemView.findViewById(R.id.titleMovie);
            rating = itemView.findViewById(R.id.ratingMovie);
            image = itemView.findViewById(R.id.imgMovie);

            itemView.setOnClickListener((View v) -> {

                callback.onClick(movie);
            });
        }

        void bind(UpComingMovie movie) {

            this.movie = movie;
            releaseDate.setText(movie.getReleaseDate());
            title.setText(movie.getTitle());
            rating.setText(String.valueOf(movie.getRating()));
            //Get image from storage

            Glide.with(itemView)
                    .load(movie.getLocalImgPath())
                    .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher))
                    .into(image);
        }

    }
}
