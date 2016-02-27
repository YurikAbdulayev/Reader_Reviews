package com.reader.models.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yurik on 26.02.16.
 */
public class NetworkReviewResponse {

    public String status;

    public String copyright;

    @SerializedName("num_results")
    public String numResult;

    @SerializedName("results")
    private List<NetworkReview> reviews;

    public List<NetworkReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<NetworkReview> reviews) {
        this.reviews = reviews;
    }

    public String getNumResult() {
        return numResult;
    }

    public void setNumResult(String numResult) {
        this.numResult = numResult;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
