package com.reader.screens.fragments;

import com.reader.api.ApiManager;
import com.reader.models.db.Review;
import com.reader.models.network.NetworkReview;
import com.reader.screens.view.ReviewsListView;
import com.reader.utils.ModelConverter;
import com.reader.utils.RealmManager;
import com.reader.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yurik on 02.03.16.
 */
public class ReviewsListViewManager {
    private ReviewsListView mReviewsListView;
    private BaseFragment mBaseFragment;

    public ReviewsListViewManager(ReviewsListView reviewsListView, BaseFragment baseFragment) {
        mReviewsListView = reviewsListView;
        mBaseFragment = baseFragment;
    }

    public void initialize() {
        getMoviesReviewsFromDb();
        getMoviesReviewsFromNetwork();
    }

    public void reload() {
        initialize();
    }

    public void destroy() {
        mReviewsListView = null;
        mBaseFragment = null;
    }

    private void getMoviesReviewsFromDb() {
        mBaseFragment.addSubscription(
                RealmManager.allReviews()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mReviewsListView::renderList,
                                throwable -> mReviewsListView.showErrorMessage(throwable.getMessage()),
                                mReviewsListView::hideLoader)
        );
    }

    private void getMoviesReviewsFromNetwork() {
        if (Utils.hasInternet(mBaseFragment.getContext())) {
            mReviewsListView.hideRetry();
            mReviewsListView.loader();
            mBaseFragment.addSubscription(
                    ApiManager.getInstance()
                            .allResponses()
                            .map(response -> {
                                List<Review> reviews = new ArrayList<>();
                                for (NetworkReview networkReview : response.getReviews()) {
                                    Review object = ModelConverter.convertToReview(networkReview);
                                    if (object != null) {
                                        object.getMultimedia().setMovieReviewId(networkReview.getNytMovieId());
                                    }
                                    reviews.add(object);
                                }
                                return reviews;
                            })
                            .doOnNext(RealmManager::save)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(mReviewsListView::renderList,
                                    throwable -> {
                                        mReviewsListView.showErrorMessage(throwable.getMessage());
                                        mReviewsListView.hideLoader();
                                        mReviewsListView.showRetry();
                                    },
                                    mReviewsListView::hideLoader));
        } else {
            mReviewsListView.hideLoader();
        }
    }
}
