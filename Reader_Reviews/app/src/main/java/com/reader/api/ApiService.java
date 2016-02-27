package com.reader.api;

import com.reader.models.network.NetworkReviewResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by yurik on 26.02.16.
 */
public interface ApiService {

    @GET(C.ALL)
    Observable<NetworkReviewResponse> allResponses();
}
