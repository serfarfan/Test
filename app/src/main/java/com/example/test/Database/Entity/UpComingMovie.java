package com.example.test.Database.Entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "UpComingMovie")
public class UpComingMovie {

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(@NonNull String posterPath) {
        this.posterPath = posterPath;
    }

    @NonNull
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@NonNull String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @NonNull
    public String getOverview() {
        return overview;
    }

    public void setOverview(@NonNull String overview) {
        this.overview = overview;
    }

    @NonNull
    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(@NonNull String originalTitle) {
        this.originalTitle = originalTitle;
    }

    @NonNull
    public String getBackDropPath() {
        return backDropPath;
    }

    public void setBackDropPath(@NonNull String backDropPath) {
        this.backDropPath = backDropPath;
    }

    @NonNull
    public float getRating() {
        return rating;
    }

    public void setRating(@NonNull float rating) {
        this.rating = rating;
    }

    @NonNull
    @PrimaryKey
    private int id;

    @NonNull
    private String title;

    private String posterPath;

    @NonNull
    private String releaseDate;

    @NonNull
    private String overview;

    @NonNull
    private String originalTitle;

    private String backDropPath;

    public String getLocalBackDropPath() {
        return localBackDropPath;
    }

    public void setLocalBackDropPath(String localBackDropPath) {
        this.localBackDropPath = localBackDropPath;
    }

    private String localBackDropPath;

    public String getLocalImgPath() {
        return localImgPath;
    }

    public void setLocalImgPath(String localImgPath) {
        this.localImgPath = localImgPath;
    }

    private String localImgPath;

    @NonNull
    private float rating;
}
