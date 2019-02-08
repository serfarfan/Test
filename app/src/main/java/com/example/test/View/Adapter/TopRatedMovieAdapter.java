package com.example.test.View.Adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.test.Api.Request.MovieRequest;
import com.example.test.Application.MovieApp;
import com.example.test.Database.async.AppExecutors;
import com.example.test.R;
import com.example.test.Util.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

//Adapter class for manage TopRated movies info when is online
public class TopRatedMovieAdapter extends RecyclerView.Adapter<TopRatedMovieAdapter.MovieViewHolder> {


    private List<MovieRequest> movieList;
    private OnMovieClickCallback callback;


    public TopRatedMovieAdapter(List<MovieRequest> movieList, OnMovieClickCallback callback) {
        this.movieList = movieList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie,
                viewGroup, false);
        return new TopRatedMovieAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder viewHolder, int i) {
        viewHolder.bind(movieList.get(i));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void clearMovies(){
        movieList.clear();
        notifyDataSetChanged();
    }

    //Inner class for apply ViewHolder pattern
    class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView releaseDate, title, rating;
        ImageView image;
        MovieRequest movieSet;
        int idMovie;
        String localImgPath = "", localBackDropPath = "";

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            releaseDate = itemView.findViewById(R.id.releaseDateMovie);
            title = itemView.findViewById(R.id.titleMovie);
            rating = itemView.findViewById(R.id.ratingMovie);
            image = itemView.findViewById(R.id.imgMovie);

            itemView.setOnClickListener((View v) -> {

                callback.onClick(movieSet);
            });
        }

        void bind(MovieRequest movie) {

            movieSet = movie;
            releaseDate.setText(movie.getReleaseDate());
            title.setText(movie.getTitle());
            rating.setText(String.valueOf(movie.getRating()));
            idMovie = movie.getId();
            //Download image and set to ImageView
            Glide.with(itemView)
                    .load(Constants.IMAGE_URL + movie.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher))
                    .into(image);
            //Download image and save to storage
            Glide.with(itemView).asBitmap()
                    .load(Constants.IMAGE_URL + movie.getPosterPath())
                    .into(new SimpleTarget<Bitmap>(100,100) {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            localImgPath = saveImage(resource, idMovie, "Movie" );
                            //Save to Room
                            AppExecutors.getInstance().getDiskIO().execute(() -> {
                                MovieApp.getDatabase().daoAccess().updateTopRatedLocalImgPath(localImgPath, idMovie);
                            });
                        }

                    });
            //Download  backDrop image and save to storage
            Glide.with(itemView).asBitmap()
                    .load(Constants.IMAGE_URL + movie.getPosterPath())
                    .into(new SimpleTarget<Bitmap>(100,100) {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            localBackDropPath = saveImage(resource, idMovie, "Back" );
                            //Save to Room
                            AppExecutors.getInstance().getDiskIO().execute(() -> {
                                MovieApp.getDatabase().daoAccess().updateTopRatedLocalBackDropPath(localBackDropPath, idMovie);
                            });
                            movie.setLocalBackDropPath(localBackDropPath);
                        }

                    });

        }

        private String saveImage(Bitmap image, int id, String prefix) {
            String savedImagePath = null;

            String imageFileName = "JPEG_" + prefix  + String.valueOf(id) + ".jpg";
            File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    + "/MoviesApp");
            boolean success = true;
            if (!storageDir.exists()) {
                success = storageDir.mkdirs();
            }
            if (success) {
                File imageFile = new File(storageDir, imageFileName);
                savedImagePath = imageFile.getAbsolutePath();
                try {
                    OutputStream fOut = new FileOutputStream(imageFile);
                    image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                    fOut.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Add the image to the system gallery
                galleryAddPic(savedImagePath);
            }
            return savedImagePath;
        }

        private void galleryAddPic(String imagePath) {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File f = new File(imagePath);
            Uri contentUri = Uri.fromFile(f);
            mediaScanIntent.setData(contentUri);
            itemView.getContext().sendBroadcast(mediaScanIntent);
        }

    }
}
