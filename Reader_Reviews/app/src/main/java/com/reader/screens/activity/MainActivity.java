package com.reader.screens.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.reader.R;
import com.reader.interfases.ListFragmentCallback;
import com.reader.models.db.Review;
import com.reader.screens.fragments.DetailsFragment;
import com.reader.screens.fragments.MovieReviewsListFragment;
import com.reader.ui.adapters.DetailsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements ListFragmentCallback {

    private List<Review> reviews;

    @Nullable
    @Bind(R.id.derails_vp)
    ViewPager viewPager;

    @Nullable
    @Bind(R.id.empty_screen)
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_container,
                            MovieReviewsListFragment.newInstance(),
                            MovieReviewsListFragment.TAG)
                    .commit();
        }
    }


    @Override
    public void onItemReviewClick(int position) {
        if (isTabled()){
            if (viewPager != null){
                viewPager.setCurrentItem(position, true);
            }
        }else {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(DetailsActivity.KEY_REVIEW_INDEX, position);
            startActivity(intent);
        }
    }

    @Override
    public void onReviewsLoaded(List<Review> reviews) {
        this.reviews = reviews;
        updateViewPager();
        if(layout != null){
            layout.setVisibility(View.GONE);
        }
    }

    private boolean isTabled(){
        return viewPager != null;
    }

    private void updateViewPager() {
        if (reviews != null){
            DetailsPagerAdapter adapter = new DetailsPagerAdapter(getSupportFragmentManager(), createPage(reviews) );
            if(viewPager != null){
                viewPager.setAdapter(adapter);
            }
        }
    }

    private List<Fragment> createPage(List<Review> reviews) {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0, reviewListSize = reviews.size(); i < reviewListSize; i++) {
            Review review = reviews.get(i);
            fragments.add(DetailsFragment.newInstance(review.getMovieId()));
        }
        return fragments;
    }

}
