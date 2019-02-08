package com.example.test.View.Adapter;


import com.example.test.Api.Request.MovieRequest;
import com.example.test.Database.Entity.Movie;
import com.example.test.Database.Entity.TopRatedMovie;
import com.example.test.Database.Entity.UpComingMovie;

public interface OnMovieClickCallback {

    //Overload onClick method according to entity or Request
    void onClick(MovieRequest movie);

    void onClick(Movie movie);
    void onClick(TopRatedMovie movie);
    void onClick(UpComingMovie movie);
}
