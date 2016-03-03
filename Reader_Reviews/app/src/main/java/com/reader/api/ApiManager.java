package com.reader.api;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.reader.utils.DateUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yurik on 26.02.16.
 */
public class ApiManager {

    private ApiManager() {

    }

    private static ApiService sInsatnce;

    public static ApiService getInstance(){
        if (sInsatnce == null){
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            Interceptor interceptor = chain -> {
                Request.Builder builder = chain.request().newBuilder()
                        .url(chain.request().url() + "?api-key=" + C.API_KEY);
                return chain.proceed(builder.build());
            };
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(interceptor)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(C.BASE_URL + C.VERSION)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(defaultGson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            sInsatnce = retrofit.create(ApiService.class);
        }
        return sInsatnce;
    }

    @NonNull
    private static Gson defaultGson() {
        return new GsonBuilder().setDateFormat(DateUtils.DATE_SCHEME).create();
    }
}
