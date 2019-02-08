package com.example.test.View;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.Controller.DetailsFragmentController;
import com.example.test.R;

//View class for fragment containing details layout
public class DetailsFragment extends Fragment {

    private View v;
    private DetailsFragmentController controller;

    public ImageView getMovieDetailsBackdrop() {
        return movieDetailsBackdrop;
    }

    public TextView getMovieDetailsTitle() {
        return movieDetailsTitle;
    }

    public TextView getRatingDetailsMovie() {
        return ratingDetailsMovie;
    }

    public TextView getReleaseDateDetailsMovie() {
        return releaseDateDetailsMovie;
    }

    public TextView getMovieDetailsOriginTitle() {
        return movieDetailsOriginTitle;
    }

    public TextView getOverviewDetailsMovie() {
        return overviewDetailsMovie;
    }

    private ImageView movieDetailsBackdrop;
    private TextView movieDetailsTitle, ratingDetailsMovie, releaseDateDetailsMovie,
            movieDetailsOriginTitle, overviewDetailsMovie;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.details_fragment_layout, container, false);
        init();
        controller.getMovie();
        controller.initListeners();
        return v;
    }

    private void init(){

        movieDetailsBackdrop = v.findViewById(R.id.movieDetailsBackdrop);
        movieDetailsTitle = v.findViewById(R.id.movieDetailsTitle);
        ratingDetailsMovie = v.findViewById(R.id.ratingDetailsMovie);
        releaseDateDetailsMovie = v.findViewById(R.id.releaseDateDetailsMovie);
        movieDetailsOriginTitle = v.findViewById(R.id.movieDetailsOriginTitle);
        overviewDetailsMovie = v.findViewById(R.id.overviewDetailsMovie);
        controller = new DetailsFragmentController(this);
    }

}
