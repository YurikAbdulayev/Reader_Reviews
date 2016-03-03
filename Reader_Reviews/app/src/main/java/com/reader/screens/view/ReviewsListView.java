package com.reader.screens.view;

import com.reader.models.db.Review;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by yurik on 02.03.16.
 */
public interface ReviewsListView {

    void loader();

    void hideLoader();

    void showErrorMessage(String message);

    void showRetry();

    void hideRetry();

    void refresh();

    void renderList (List<Review> reviews);
}
