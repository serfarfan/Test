package com.example.test.Model;

import android.os.Parcel;
import android.os.Parcelable;

//Model class for handle representation of a movie regardless of its type
public class MovieModel implements Parcelable {

    private MovieModel(Parcel in) {
        backDrop = in.readString();
        title = in.readString();
        originTitle = in.readString();
        releaseDate = in.readString();
        overview = in.readString();
        rating = in.readFloat();
        localBackDropPath = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getBackDrop() {
        return backDrop;
    }

    public void setBackDrop(String backDrop) {
        this.backDrop = backDrop;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginTitle() {
        return originTitle;
    }

    public void setOriginTitle(String originTitle) {
        this.originTitle = originTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    private String backDrop;
    private String title;
    private String originTitle;
    private String releaseDate;
    private String overview;

    public String getLocalBackDropPath() {
        return localBackDropPath;
    }

    public void setLocalBackDropPath(String localBackDropPath) {
        this.localBackDropPath = localBackDropPath;
    }

    private String localBackDropPath;
    private float rating;

    public MovieModel(String backDrop, String title, String originTitle, String releaseDate,
                      String overview, float rating, String localBackDropPath){
        this.backDrop = backDrop;
        this.title = title;
        this.originTitle = originTitle;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.rating = rating;
        this.localBackDropPath = localBackDropPath;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(backDrop);
        parcel.writeString(title);
        parcel.writeString(originTitle);
        parcel.writeString(releaseDate);
        parcel.writeString(overview);
        parcel.writeFloat(rating);
        parcel.writeString(localBackDropPath);
    }
}
