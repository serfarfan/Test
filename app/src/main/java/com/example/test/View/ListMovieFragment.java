package com.example.test.View;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.Application.MovieApp;
import com.example.test.Controller.ListMovieFragController;
import com.example.test.Database.Entity.Movie;
import com.example.test.Database.Entity.TopRatedMovie;
import com.example.test.Database.async.AppExecutors;
import com.example.test.R;

import java.util.List;

//View class for fragment containing recyclerView

public class ListMovieFragment extends Fragment {


    private View v;
    private RecyclerView recyclerMovies;
    private ListMovieFragController controller;
    private String TAG = "ListMovieFragment";
    TextView txtTopRated;

    public TextView getTxtTopRated() {
        return txtTopRated;
    }

    public TextView getTxtUpComing() {
        return txtUpComing;
    }

    public TextView getTxtPopular() {
        return txtPopular;
    }

    TextView txtUpComing;
    TextView txtPopular;

    public List<Movie> getMovList() {
        return movList;
    }

    private List<Movie> movList;//Local list, for Offline

    public List<TopRatedMovie> getTopRatedMovieList() {
        return topRatedMovieList;
    }

    private List<TopRatedMovie> topRatedMovieList;


    public RecyclerView getRecyclerMovies() {
        return recyclerMovies;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        verifyRoomDb();
        v = inflater.inflate(R.layout.list_movie_fragment_layout, container, false);
        init();
        controller.initListeners();
        return v;
    }

    private void init(){

        recyclerMovies = v.findViewById(R.id.recycler);
        txtPopular = v.findViewById(R.id.txtPopular);
        txtTopRated = v.findViewById(R.id.txtTopRated);
        txtUpComing = v.findViewById(R.id.txtUpComing);
        controller = new ListMovieFragController(this);
    }

    private void verifyRoomDb(){

        //Verify Room DB
        AppExecutors.getInstance().getDiskIO().execute(() ->  {
            movList = MovieApp.getDatabase().daoAccess().getMovieList();
            Log.d(TAG, "Size in room is " + movList.size());
            //If exists some movies, show them
            if (movList != null && movList.size() > 0) {
                Log.d(TAG, "inside " + movList.size());
            }
        });


    }




}
