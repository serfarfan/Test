package com.example.test.Api.Request;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Retrofit request with respective parameters
public class MovieRequest implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("original_title")
    @Expose
    private String originalTitle;

    @SerializedName("backdrop_path")
    @Expose
    private String backDropPath;

    @SerializedName("vote_average")
    @Expose
    private float rating;

    public String getLocalBackDropPath() {
        return localBackDropPath;
    }

    public void setLocalBackDropPath(String localBackDropPath) {
        this.localBackDropPath = localBackDropPath;
    }

    private String localBackDropPath;


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public float getRating() {
        return rating;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getBackDropPath() {
        return backDropPath;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(posterPath);
        parcel.writeString(releaseDate);
        parcel.writeString(overview);
        parcel.writeString(originalTitle);
        parcel.writeString(backDropPath);
        parcel.writeFloat(rating);
        parcel.writeString(localBackDropPath);
    }

    protected MovieRequest(Parcel in) {
        id = in.readInt();
        title = in.readString();
        posterPath = in.readString();
        releaseDate = in.readString();
        overview = in.readString();
        originalTitle = in.readString();
        backDropPath = in.readString();
        rating = in.readFloat();
        localBackDropPath = in.readString();
    }

    public static final Creator<MovieRequest> CREATOR = new Creator<MovieRequest>() {
        @Override
        public MovieRequest createFromParcel(Parcel in) {
            return new MovieRequest(in);
        }

        @Override
        public MovieRequest[] newArray(int size) {
            return new MovieRequest[size];
        }
    };
}
