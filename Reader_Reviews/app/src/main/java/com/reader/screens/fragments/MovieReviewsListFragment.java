package com.reader.screens.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.reader.R;
import com.reader.interfases.ListFragmentCallback;
import com.reader.models.db.Review;
import com.reader.screens.activity.DetailsActivity;
import com.reader.screens.view.ReviewsListView;
import com.reader.ui.ItemDecorationAlbumColumns;
import com.reader.ui.MyItemDecoration;
import com.reader.ui.adapters.MovieReviewsAdapter;
import com.reader.ui.listeners.OnItemClickListener;
import com.reader.utils.Utils;

import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieReviewsListFragment extends BaseFragment
        implements ReviewsListView {

    public static final String TAG = "MovieReviewsListFragment";

    private ListFragmentCallback listFragmentCallback;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;

    @Bind(R.id.swipe_reload)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ReviewsListViewManager mReviewsListViewManager;
    private boolean isAnimating;
    private boolean isAnimated;

    public MovieReviewsListFragment() {

    }

    public static MovieReviewsListFragment newInstance() {
        return new MovieReviewsListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_reviews_list, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mReviewsListViewManager = new ReviewsListViewManager(this, this);
        listFragmentCallback = (ListFragmentCallback) context;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mReviewsListViewManager.initialize();
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            refresh();
        });
    }

    @Override
    public void onDetach() {
        mReviewsListViewManager.destroy();
        listFragmentCallback = null;
        super.onDetach();
    }

    private void updateUi(List<Review> reviews) {
        MovieReviewsAdapter adapter ;
        if(mRecyclerView.getAdapter() == null){
            adapter = new MovieReviewsAdapter(reviews);
            if(!isAnimated){
                startAnimation();
            }
            mRecyclerView.setAdapter(adapter);
            adapter.setOnClickListener((view, position) -> {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.KEY_REVIEW_INDEX, position);
                startActivity(intent);
            });
        }else {
            adapter = (MovieReviewsAdapter) mRecyclerView.getAdapter();
            adapter.updateList(reviews);
            if(!isAnimating){
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void startAnimation() {
        isAnimated = true;
        mRecyclerView.getViewTreeObserver()
                .addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        isAnimating = true;
                        mRecyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                        for (int i = 0; i < mRecyclerView.getChildCount(); i++) {
                            mRecyclerView.getChildAt(i).setTranslationX(mRecyclerView.getWidth());
                            ViewPropertyAnimator animate = mRecyclerView.getChildAt(i).animate();
                            if(i == mRecyclerView.getChildCount() - 1) {
                                animate.withEndAction(() -> {
                                    isAnimating = false;
                                    mRecyclerView.getAdapter().notifyDataSetChanged();
                                });
                            }
                            animate
                                    .setDuration(600)
                                    .setStartDelay(i * 100)
                                    .translationX(0f)
                                    .start();
                        }
                        return true;
                    }
                  });
    }

    @Override
    public void loader() {
        if (mRecyclerView.getAdapter() != null && mRecyclerView.getAdapter().getItemCount() > 0) {
            return;
        }
        if (!mSwipeRefreshLayout.isRefreshing()) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoader() {
        mProgressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRetry() {
        Utils.showRetryDialog(getContext(), R.string.retry_hint, (dialog, which) -> {
            mReviewsListViewManager.reload();
        });
    }

    @Override
    public void hideRetry() {
        // TODO btn in layout
    }

    @Override
    public void refresh() {
        mReviewsListViewManager.reload();
    }

    @Override
    public void renderList(List<Review> reviews) {
        hideLoader();
        updateUi(reviews);
        if (listFragmentCallback != null){
            listFragmentCallback.onReviewsLoaded(reviews);
        }
    }
}