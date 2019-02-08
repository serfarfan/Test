package com.example.test.Controller;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.test.Model.MovieModel;
import com.example.test.R;
import com.example.test.Util.Constants;
import com.example.test.Util.Utils;
import com.example.test.View.DetailsFragment;

//Controller for fragment containing layout with details of movie
public class DetailsFragmentController {

    private DetailsFragment fragment;
    private String backDrop, title, originTitle, releaseDate, overview, localBackDropPath;
    private float rating;

    public DetailsFragmentController(DetailsFragment fragment){
        this.fragment = fragment;
    }

    public void initListeners(){

    }

    private void setValues(){

        //Set values
        fragment.getMovieDetailsTitle().setText(title);
        fragment.getRatingDetailsMovie().setText(String.valueOf(rating));
        fragment.getReleaseDateDetailsMovie().setText(releaseDate);
        fragment.getMovieDetailsOriginTitle().setText(originTitle);
        fragment.getOverviewDetailsMovie().setText(overview);

        if (Utils.isConnected(fragment.getActivity())){
            Glide.with(fragment.getActivity())
                    .load(Constants.IMAGE_URL + backDrop)
                    .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher))
                    .into(fragment.getMovieDetailsBackdrop());
        } else {
            Glide.with(fragment.getActivity())
                    .load(localBackDropPath)
                    .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher))
                    .into(fragment.getMovieDetailsBackdrop());
        }
    }

    public void getMovie(){

        Bundle bundle = fragment.getArguments();
        MovieModel movieModel = bundle.getParcelable("movieMod");
        //Get values
        if (movieModel != null) {
            title = movieModel.getTitle();
            rating = movieModel.getRating();
            releaseDate = movieModel.getReleaseDate();
            originTitle = movieModel.getOriginTitle();
            overview = movieModel.getOverview();
            backDrop = movieModel.getBackDrop();
            localBackDropPath = movieModel.getLocalBackDropPath();
        }
        setValues();
    }
}
