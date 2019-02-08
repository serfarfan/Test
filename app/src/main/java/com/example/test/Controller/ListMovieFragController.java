package com.example.test.Controller;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.example.test.Api.ApiManager;
import com.example.test.Api.MovieApi;
import com.example.test.Api.Request.MovieRequest;
import com.example.test.Api.Response.MovieResponse;
import com.example.test.Application.MovieApp;
import com.example.test.Database.Entity.Movie;
import com.example.test.Database.Entity.TopRatedMovie;
import com.example.test.Database.Entity.UpComingMovie;
import com.example.test.Database.async.AppExecutors;
import com.example.test.Model.MovieModel;
import com.example.test.R;
import com.example.test.Util.Constants;
import com.example.test.Util.Utils;
import com.example.test.View.Adapter.MovieAdapter;
import com.example.test.View.Adapter.MovieAdapterOffline;
import com.example.test.View.Adapter.OnMovieClickCallback;
import com.example.test.View.Adapter.TopRatedMovieAdapter;
import com.example.test.View.Adapter.TopRatedMovieAdapterOffline;
import com.example.test.View.Adapter.UpcomingAdapter;
import com.example.test.View.Adapter.UpcomingAdapterOffline;
import com.example.test.View.DetailsFragment;
import com.example.test.View.ListMovieFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Controller for fragment containing recyclerView with list of movies
public class ListMovieFragController {

    private ListMovieFragment fragment;
    private static MovieApi movieApi;
    private static final String TAG  = "ListMovieFragController";
    private MovieAdapter movieAdapter;
    private MovieAdapterOffline movieAdapterOffline;
    private TopRatedMovieAdapter topRatedMovieAdapter;
    private TopRatedMovieAdapterOffline topRatedMovieAdapterOffline;
    private UpcomingAdapter upcomingAdapter;
    private UpcomingAdapterOffline upcomingAdapterOffline;
    private List<Movie> movList;//Local list, for Offline
    private List<TopRatedMovie> topRatedMovieList;
    private List<UpComingMovie> upComingMovieList;


    private OnMovieClickCallback callback = new OnMovieClickCallback() {

        //OnClick online for all movies
        @Override
        public void onClick(MovieRequest movieReq) {

            String backDrop, title, originTitle, releaseDate, overview, localBackDropPath;
            float rating;
                backDrop = movieReq.getBackDropPath();
                title = movieReq.getTitle();
                originTitle = movieReq.getOriginalTitle();
                rating = movieReq.getRating();
                releaseDate = movieReq.getReleaseDate();
                overview = movieReq.getOverview();
                localBackDropPath = movieReq.getLocalBackDropPath();
            //Fill model movie
            MovieModel movieModel = new MovieModel(backDrop, title, originTitle, releaseDate,
                    overview, rating, localBackDropPath);
            fragTransaction(movieModel);
        }

        //Onclick offline for popular movies
        @Override
        public void onClick(Movie movie) {

            String backDrop, title, originTitle, releaseDate, overview, localBackDropPath;
            float rating;
            backDrop = movie.getBackDropPath();
            title = movie.getTitle();
            originTitle = movie.getOriginalTitle();
            rating = movie.getRating();
            releaseDate = movie.getReleaseDate();
            overview = movie.getOverview();
            localBackDropPath = movie.getLocalBackDropPath();
            //Fill model movie
            MovieModel movieModel = new MovieModel(backDrop, title, originTitle, releaseDate,
                    overview, rating, localBackDropPath);
            fragTransaction(movieModel);
        }

        //Onclick offline for Top Rated movies
        @Override
        public void onClick(TopRatedMovie movie) {

            String backDrop, title, originTitle, releaseDate, overview, localBackDropPath;
            float rating;
            backDrop = movie.getBackDropPath();
            title = movie.getTitle();
            originTitle = movie.getOriginalTitle();
            rating = movie.getRating();
            releaseDate = movie.getReleaseDate();
            overview = movie.getOverview();
            localBackDropPath = movie.getLocalBackDropPath();
            //Fill model movie
            MovieModel movieModel = new MovieModel(backDrop, title, originTitle, releaseDate,
                    overview, rating, localBackDropPath);
            fragTransaction(movieModel);
        }

        //Onclick offline for UpComing movies
        @Override
        public void onClick(UpComingMovie movie) {

            String backDrop, title, originTitle, releaseDate, overview, localBackDropPath;
            float rating;
            backDrop = movie.getBackDropPath();
            title = movie.getTitle();
            originTitle = movie.getOriginalTitle();
            rating = movie.getRating();
            releaseDate = movie.getReleaseDate();
            overview = movie.getOverview();
            localBackDropPath = movie.getLocalBackDropPath();
            //Fill model movie
            MovieModel movieModel = new MovieModel(backDrop, title, originTitle, releaseDate,
                    overview, rating, localBackDropPath);
            fragTransaction(movieModel);
        }
    };


    private void fragTransaction(MovieModel movieModel){

        Bundle bundle = new Bundle();
        bundle.putParcelable("movieMod", movieModel);
        //Transaction
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);
        FragmentTransaction ft = fragment.getActivity().getFragmentManager().beginTransaction();
        ft.hide(fragment);
        ft.add(android.R.id.content, detailsFragment);
        ft.addToBackStack("list").commit();
    }

    public ListMovieFragController(ListMovieFragment fragment){
        this.fragment = fragment;
    }

    private void getLocalPopMovies(){

        if (movList != null && movList.size() > 0) {
            movieAdapterOffline = new MovieAdapterOffline(movList, callback);
            fragment.getRecyclerMovies().setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
            fragment.getRecyclerMovies().setAdapter(movieAdapterOffline);
            fragment.getRecyclerMovies().addItemDecoration(
                    new DividerItemDecoration(fragment.getActivity(), DividerItemDecoration.VERTICAL));
        } else {
            Toast.makeText(fragment.getActivity(), R.string.no_internet, Toast.LENGTH_LONG).show();
        }
    }

    private void getLocalTopRatedMovies(List<TopRatedMovie> topMovies){

        if (topMovies != null && topMovies.size() > 0) {
            topRatedMovieAdapterOffline = new TopRatedMovieAdapterOffline(topMovies, callback);
            fragment.getRecyclerMovies().setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
            fragment.getRecyclerMovies().setAdapter(topRatedMovieAdapterOffline);
            fragment.getRecyclerMovies().addItemDecoration(
                    new DividerItemDecoration(fragment.getActivity(), DividerItemDecoration.VERTICAL));
        } else {
            Toast.makeText(fragment.getActivity(), R.string.no_internet, Toast.LENGTH_LONG).show();
        }
    }

    private void getLocalUpcomingMovies( List<UpComingMovie> upComingMovies){

        if (upComingMovies != null && upComingMovies.size() > 0) {
            upcomingAdapterOffline = new UpcomingAdapterOffline(upComingMovies, callback);
            fragment.getRecyclerMovies().setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
            fragment.getRecyclerMovies().setAdapter(upcomingAdapterOffline);
            fragment.getRecyclerMovies().addItemDecoration(
                    new DividerItemDecoration(fragment.getActivity(), DividerItemDecoration.VERTICAL));
        } else {
            Toast.makeText(fragment.getActivity(), R.string.no_internet, Toast.LENGTH_LONG).show();
        }
    }


    public void initListeners(){

        movieApi = ApiManager.getAPIService();
        movList = fragment.getMovList();
        topRatedMovieList = fragment.getTopRatedMovieList();
        if (Utils.isConnected(fragment.getActivity()))getMovies();
        else getLocalPopMovies();
        //**************************UpComing**************************
        fragment.getTxtUpComing().setOnClickListener((View) -> {

            AppExecutors.getInstance().getDiskIO().execute(() ->  {
                upComingMovieList = MovieApp.getDatabase().daoAccess().getUpComingMovieList();
            });

            if (upcomingAdapter != null)upcomingAdapter.clearMovies();
            if (Utils.isConnected(fragment.getActivity()))getUpComingMovies();
            else getLocalUpcomingMovies(upComingMovieList);
            fragment.getTxtUpComing().setTextColor(fragment.getActivity().getResources().getColor(R.color.colorPrimaryDark));
            fragment.getTxtTopRated().setTextColor(fragment.getActivity().getResources().getColor(R.color.gray));
            fragment.getTxtPopular().setTextColor(fragment.getActivity().getResources().getColor(R.color.gray));
                }
        );

        //**************************Popular***********************

        fragment.getTxtPopular().setOnClickListener((View) -> {

            AppExecutors.getInstance().getDiskIO().execute(() ->  {
                movList = MovieApp.getDatabase().daoAccess().getMovieList();
            });

            if (movieAdapter != null) movieAdapter.clearMovies();
            if (Utils.isConnected(fragment.getActivity())) getMovies();
            else getLocalPopMovies();
            fragment.getTxtPopular().setTextColor(fragment.getActivity().getResources().getColor(R.color.colorPrimaryDark));
            fragment.getTxtTopRated().setTextColor(fragment.getActivity().getResources().getColor(R.color.gray));
            fragment.getTxtUpComing().setTextColor(fragment.getActivity().getResources().getColor(R.color.gray));
                }
        );

        //**************************TopRated**************************
        fragment.getTxtTopRated().setOnClickListener((View) -> {

            AppExecutors.getInstance().getDiskIO().execute(() ->  {
                topRatedMovieList = MovieApp.getDatabase().daoAccess().getTopRatedMovieList();
            });

            if (topRatedMovieAdapter != null)topRatedMovieAdapter.clearMovies();
            if (Utils.isConnected(fragment.getActivity())) getTopRatedMovies();
            else getLocalTopRatedMovies(topRatedMovieList);
            fragment.getTxtTopRated().setTextColor(fragment.getActivity().getResources().getColor(R.color.colorPrimaryDark));
            fragment.getTxtPopular().setTextColor(fragment.getActivity().getResources().getColor(R.color.gray));
            fragment.getTxtUpComing().setTextColor(fragment.getActivity().getResources().getColor(R.color.gray));
                }
        );
    }

    private void getUpComingMovies(){

        Call<MovieResponse> call = movieApi.getUpcomingMovies(Constants.API_KEY, Constants.LANGUAGE, 1);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    if (movieResponse != null && movieResponse.getMovies() != null) {
                        //Save list of top rated movies in Room DB
                        AppExecutors.getInstance().getDiskIO().execute(() -> {
                            parseRespUpcomingMovies(movieResponse.getMovies());
                        });

                        upcomingAdapter = new UpcomingAdapter(movieResponse.getMovies(), callback);
                        fragment.getRecyclerMovies().setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
                        fragment.getRecyclerMovies().setAdapter(upcomingAdapter);
                        fragment.getRecyclerMovies().addItemDecoration(
                                new DividerItemDecoration(fragment.getActivity(), DividerItemDecoration.VERTICAL));
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }

    private void getTopRatedMovies(){

        Call<MovieResponse> call = movieApi.getTopRatedMovies(Constants.API_KEY, Constants.LANGUAGE, 1);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    if (movieResponse != null && movieResponse.getMovies() != null) {
                        //Save list of top rated movies in Room DB
                        AppExecutors.getInstance().getDiskIO().execute(() -> {
                            parseRespTopRatedMovies(movieResponse.getMovies());
                        });
                        topRatedMovieAdapter = new TopRatedMovieAdapter(movieResponse.getMovies(), callback);
                        fragment.getRecyclerMovies().setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
                        fragment.getRecyclerMovies().setAdapter(topRatedMovieAdapter);
                        fragment.getRecyclerMovies().addItemDecoration(
                                new DividerItemDecoration(fragment.getActivity(), DividerItemDecoration.VERTICAL));
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }


    private void getMovies(){

        Call<MovieResponse> call = movieApi.getMovieList(Constants.API_KEY, Constants.LANGUAGE, 1);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    MovieResponse movieResponse = response.body();
                    if (movieResponse != null && movieResponse.getMovies() != null){
                        //Save list of movies in Room DB
                        AppExecutors.getInstance().getDiskIO().execute(() -> {
                                parseResponseMovies(movieResponse.getMovies());
                                    });
                        movieAdapter = new MovieAdapter(movieResponse.getMovies(), callback);
                        fragment.getRecyclerMovies().setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
                        fragment.getRecyclerMovies().setAdapter(movieAdapter);
                        fragment.getRecyclerMovies().addItemDecoration(
                                new DividerItemDecoration(fragment.getActivity(), DividerItemDecoration.VERTICAL));
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "Error downloading");
            }
        });
    }

    private void parseRespUpcomingMovies(List<MovieRequest> movieList){
        int movieId;
        float rating;
        String title, posterPath, releaseDate, overview, originalTitle, backDropPath, localImgPath,
                localBackDropPath;
        UpComingMovie movie = new UpComingMovie();
        if (movieList != null)
            for (int i = 0; i < movieList.size(); i++) {
                //Get values from JSON response
                movieId =  movieList.get(i).getId();
                title  =  movieList.get(i).getTitle();
                posterPath = movieList.get(i).getPosterPath();
                releaseDate = movieList.get(i).getReleaseDate();
                overview = movieList.get(i).getOverview();
                originalTitle = movieList.get(i).getOriginalTitle();
                backDropPath = movieList.get(i).getBackDropPath();
                rating = movieList.get(i).getRating();
                localImgPath = "";
                localBackDropPath = "";
                //Set values to room entity
                movie.setId(movieId);
                movie.setTitle(title);
                movie.setBackDropPath(backDropPath);
                movie.setOriginalTitle(originalTitle);
                movie.setOverview(overview);
                movie.setPosterPath(posterPath);
                movie.setRating(rating);
                movie.setReleaseDate(releaseDate);
                movie.setLocalImgPath(localImgPath);
                movie.setLocalBackDropPath(localBackDropPath);
                MovieApp.getDatabase().daoAccess().addUpComingMovie(movie);
            }
    }

    private void parseRespTopRatedMovies(List<MovieRequest> movieList){

        int movieId;
        float rating;
        String title, posterPath, releaseDate, overview, originalTitle, backDropPath, localImgPath,
                localBackDropPath;
        TopRatedMovie movie = new TopRatedMovie();
        if (movieList != null)
            for (int i = 0; i < movieList.size(); i++) {
                //Get values from JSON response
                movieId =  movieList.get(i).getId();
                title  =  movieList.get(i).getTitle();
                posterPath = movieList.get(i).getPosterPath();
                releaseDate = movieList.get(i).getReleaseDate();
                overview = movieList.get(i).getOverview();
                originalTitle = movieList.get(i).getOriginalTitle();
                backDropPath = movieList.get(i).getBackDropPath();
                rating = movieList.get(i).getRating();
                localImgPath = "";
                localBackDropPath = "";
                //Set values to room entity
                movie.setId(movieId);
                movie.setTitle(title);
                movie.setBackDropPath(backDropPath);
                movie.setOriginalTitle(originalTitle);
                movie.setOverview(overview);
                movie.setPosterPath(posterPath);
                movie.setRating(rating);
                movie.setReleaseDate(releaseDate);
                movie.setLocalImgPath(localImgPath);
                movie.setLocalBackDropPath(localBackDropPath);
                MovieApp.getDatabase().daoAccess().addTopRatedMovie(movie);
            }
    }



    private void parseResponseMovies(List<MovieRequest> movieList){

        Movie movie = new Movie();
        int movieId;
        float rating;
        String title, posterPath, releaseDate, overview, originalTitle, backDropPath, localImgPath,
                localBackDropPath;

        if (movieList != null)
            for (int i = 0; i < movieList.size(); i++) {
                //Get values from JSON response
                movieId =  movieList.get(i).getId();
                title  =  movieList.get(i).getTitle();
                posterPath = movieList.get(i).getPosterPath();
                releaseDate = movieList.get(i).getReleaseDate();
                overview = movieList.get(i).getOverview();
                originalTitle = movieList.get(i).getOriginalTitle();
                backDropPath = movieList.get(i).getBackDropPath();
                rating = movieList.get(i).getRating();
                localImgPath = "";
                localBackDropPath = "";
                //Set values to room entity
                movie.setId(movieId);
                movie.setTitle(title);
                movie.setBackDropPath(backDropPath);
                movie.setOriginalTitle(originalTitle);
                movie.setOverview(overview);
                movie.setPosterPath(posterPath);
                movie.setRating(rating);
                movie.setReleaseDate(releaseDate);
                movie.setLocalImgPath(localImgPath);
                movie.setLocalBackDropPath(localBackDropPath);
                MovieApp.getDatabase().daoAccess().addMovie(movie);
            }
    }

}
