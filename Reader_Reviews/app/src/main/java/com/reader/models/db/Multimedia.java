package com.reader.models.db;

import com.reader.models.network.NetworkResource;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yurik on 29.02.16.
 */
public class Multimedia extends RealmObject {

    @PrimaryKey
    private int movieReviewId;

    private String src;

    public int getMovieReviewId() {
        return movieReviewId;
    }

    public void setMovieReviewId(int movieReviewId) {
        this.movieReviewId = movieReviewId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
