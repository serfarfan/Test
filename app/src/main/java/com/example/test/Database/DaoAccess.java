package com.example.test.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.test.Database.Entity.Movie;
import com.example.test.Database.Entity.TopRatedMovie;
import com.example.test.Database.Entity.UpComingMovie;

import java.util.List;

//Interface containing all queries to interact with Room database
@Dao
public interface DaoAccess {


    //*******************INSERTS*******************

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMovie(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTopRatedMovie(TopRatedMovie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUpComingMovie(UpComingMovie movie);

    //*******************SELECTS*******************

    @Query("Select * from Movie")
    List<Movie> getMovieList();

    @Query("Select * from TopRatedMovie")
    List<TopRatedMovie> getTopRatedMovieList();

    @Query("Select * from UpComingMovie")
    List<UpComingMovie> getUpComingMovieList();

    //*******************UPDATES*******************

    @Query("UPDATE Movie SET localImgPath = :path WHERE id = :id")
    void updateLocalImgPath(String path, int id);

    @Query("UPDATE Movie SET localBackDropPath = :path WHERE id = :id")
    void updateLocalBackDropPath(String path, int id);


    @Query("UPDATE TopRatedMovie SET localImgPath = :path WHERE id = :id")
    void updateTopRatedLocalImgPath(String path, int id);

    @Query("UPDATE TopRatedMovie SET localBackDropPath = :path WHERE id = :id")
    void updateTopRatedLocalBackDropPath(String path, int id);

    @Query("UPDATE UpComingMovie SET localImgPath = :path WHERE id = :id")
    void updateUpComingLocalImgPath(String path, int id);

    @Query("UPDATE UpComingMovie SET localBackDropPath = :path WHERE id = :id")
    void updateUpComingLocalBackDropPath(String path, int id);

}
