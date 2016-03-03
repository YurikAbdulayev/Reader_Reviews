package com.reader.models.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yurik on 26.02.16.
 */
public class NetworkMultimedia {

    private int movieReviewId;

    @SerializedName("resource")
    private NetworkResource resources;

    public NetworkResource getResources() {
        return resources;
    }

    public void setResources(NetworkResource resources) {
        this.resources = resources;
    }

    public int getMovieReviewId() {
        return movieReviewId;
    }

    public void setMovieReviewId(int movieReviewId) {
        this.movieReviewId = movieReviewId;
    }

}
