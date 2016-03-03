package com.reader.utils;

import android.support.annotation.Nullable;

import com.reader.models.db.Link;
import com.reader.models.db.Multimedia;
import com.reader.models.db.Review;
import com.reader.models.network.NetworkLink;
import com.reader.models.network.NetworkMultimedia;
import com.reader.models.network.NetworkResource;
import com.reader.models.network.NetworkReview;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by yurik on 29.02.16.
 */
public class ModelConverter {

    @Nullable
    public static Link convertToLink(@Nullable NetworkLink networkLink) {
        if (networkLink == null) {
            return null;
        }
        Link link = new Link();
        link.setUrl(networkLink.getUrl());
        link.setType(networkLink.getType());
        link.setSuggestedLinkText(networkLink.getSuggestedLinkText());
        return link;
    }

    @Nullable
    public static Multimedia convertToMultimedia(@Nullable NetworkMultimedia networkMultimedia) {
        if (networkMultimedia == null) {
            return null;
        }
        Multimedia multimedia = new Multimedia();
        multimedia.setMovieReviewId(networkMultimedia.getMovieReviewId());
        multimedia.setSrc(networkMultimedia.getResources().getSrc());
        return multimedia;
    }

    @Nullable
    public static Review convertToReview(@Nullable NetworkReview networkReview) {
        if (networkReview == null) {
            return null;
        }
        Review review = new Review();
        review.setMovieId(networkReview.getNytMovieId());
        review.setDisplayTitle(networkReview.getDisplayTitle());
        review.setMpaaRating(networkReview.getMpaaRating());
        review.setByLine(networkReview.getByLine());
        review.setHeadLine(networkReview.getHeadLine());
        review.setPublicationDate(networkReview.getPublicationDate());
        review.setOpeningDate(networkReview.getOpeningDate());
        review.setDateUpdated(networkReview.getDateUpdated());
        review.setSummaryShort(networkReview.getSummaryShort());
        review.setSeoName(networkReview.getSeoName());
        review.setLink(convertToLink(networkReview.getNetworkLink()));
        review.setMultimedia(convertToMultimedia(networkReview.getNetworkMultimedia()));
        List<NetworkLink> relatedUrls = networkReview.getRelatedUrls();
        if (relatedUrls != null && !relatedUrls.isEmpty()) {
            RealmList<Link> realmRelatedUrls = new RealmList<>();
            for (NetworkLink relatedUrl : relatedUrls) {
                Link convertToLink = convertToLink(relatedUrl);
                if (convertToLink != null) {
                    realmRelatedUrls.add(convertToLink);
                }
            }
            review.setRelatedUrls(realmRelatedUrls);
        }
        return review;
    }
}
