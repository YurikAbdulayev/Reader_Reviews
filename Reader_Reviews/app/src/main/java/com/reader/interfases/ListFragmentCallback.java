package com.reader.interfases;

import com.reader.models.db.Review;

import java.util.List;

/**
 * Created by yurik on 09.03.16.
 */
public interface ListFragmentCallback {

    void onItemReviewClick(int position);

    void onReviewsLoaded(List<Review> reviews);

}
