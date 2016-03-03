package com.reader.utils;

import com.reader.models.db.Review;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by yurik on 02.03.16.
 */
public class RealmManager {

    public static void save(List<Review> reviewList) {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(reviewList);
        });
    }

    public static Observable<RealmResults<Review>> allReviews() {
        return Realm.getDefaultInstance()
                .where(Review.class)
                .findAllAsync()
                .asObservable()
                .filter(RealmResults::isLoaded); // while all data is not loaded will not return data!!! якщо фільтр не виконається онНекст не виконається (може не закритися лоадер)
    }

    public static Observable<Review> getById(int id) {
        return Realm.getDefaultInstance()
                .where(Review.class)
                .equalTo(DbFields._MOVIE_ID, id)
                .findFirstAsync()
                .asObservable()
                .filter(RealmObject::isLoaded)
                .map(review -> (Review) review);
    }
}
