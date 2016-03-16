package com.reader;

import com.reader.screens.fragments.BaseFragment;
import com.reader.screens.fragments.ReviewsListViewManager;
import com.reader.screens.view.ReviewsListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

/**
 * Created by yurik on 14.03.16.
 */
public class ReviewsListViewManagerTest {

    @Mock
    ReviewsListView reviewsListView;

    @Mock
    BaseFragment baseFragment;

    private ReviewsListViewManager reviewsListViewManager;

    @Before
    public void beforeTest(){
        MockitoAnnotations.initMocks(this);

        reviewsListViewManager = new ReviewsListViewManager(reviewsListView, baseFragment);
    }

    @Test
    public void validateInitialize(){

        reviewsListViewManager.initialize();
        verify(reviewsListViewManager).getMoviesReviewsFromDb();
        verify(reviewsListViewManager).getMoviesReviewsFromNetwork();
    }

}
