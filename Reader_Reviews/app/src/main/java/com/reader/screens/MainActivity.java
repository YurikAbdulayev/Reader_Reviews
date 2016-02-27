package com.reader.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.reader.R;
import com.reader.api.ApiManager;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "logtag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiManager
                .getInstance()
                .allResponses()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(networkReviewResponse -> {
                    if(networkReviewResponse != null){
                        Log.d(TAG, "onCreate: " + networkReviewResponse.getReviews().size());
                    }
                }, Throwable::printStackTrace);
    }
}
