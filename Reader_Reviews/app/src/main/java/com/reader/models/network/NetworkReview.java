package com.reader.models.network;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by yurik on 26.02.16.
 */
public class NetworkReview {

    @SerializedName("nyt_movie_id")
    private int nytMovieId;

    @SerializedName("display_title")
    private String displayTitle;

    @SerializedName("mpaa_rating")
    private String mpaaRating;

    @SerializedName("byline")
    private String byLine;

    @SerializedName("headline")
    private String headLine;

    @SerializedName("summary_short")
    private String summaryShort;

    @SerializedName("publication_date")
    private Date publicationDate;

    @SerializedName("opening_date")
    private Date openingDate;

    @SerializedName("date_updated")
    private Date dateUpdated;

    @SerializedName("seo_name")
    private String seoName;

    @SerializedName("related_urls")
    private List<NetworkRelatedURL> relatedUrls;

    @SerializedName("link")
    private NetworkLink networkLink;

    public int getNytMovieId() {
        return nytMovieId;
    }

    public void setNytMovieId(int nytMovieId) {
        this.nytMovieId = nytMovieId;
    }

    public String getDisplayTitle() {
        return displayTitle;
    }

    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getByLine() {
        return byLine;
    }

    public void setByLine(String byLine) {
        this.byLine = byLine;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getSummaryShort() {
        return summaryShort;
    }

    public void setSummaryShort(String summaryShort) {
        this.summaryShort = summaryShort;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getSeoName() {
        return seoName;
    }

    public void setSeoName(String seoName) {
        this.seoName = seoName;
    }

    public List<NetworkRelatedURL> getRelatedUrls() {
        return relatedUrls;
    }

    public void setRelatedUrls(List<NetworkRelatedURL> relatedUrls) {
        this.relatedUrls = relatedUrls;
    }

    public NetworkLink getNetworkLink() {
        return networkLink;
    }

    public void setNetworkLink(NetworkLink networkLink) {
        this.networkLink = networkLink;
    }
}
