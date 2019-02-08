package com.example.test.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.test.Database.Entity.Movie;
import com.example.test.Database.Entity.TopRatedMovie;
import com.example.test.Database.Entity.UpComingMovie;


@Database(entities = {Movie.class, TopRatedMovie.class, UpComingMovie.class}, version = 1)
 public abstract class MovieDatabase extends RoomDatabase{

    public abstract DaoAccess daoAccess();
}
