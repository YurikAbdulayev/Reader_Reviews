package com.reader.screens.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.reader.R;
import com.reader.api.ApiManager;
import com.reader.screens.fragments.MovieReviewsListFragment;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "logtag";

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
}
