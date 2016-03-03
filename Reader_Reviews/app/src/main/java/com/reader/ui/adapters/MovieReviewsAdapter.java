package com.reader.ui.adapters;

import android.graphics.Bitmap;
import android.support.v4.util.Pair;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.reader.R;
import com.reader.models.db.Review;
import com.reader.utils.RxUtils;
import com.reader.utils.Utils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by yurik on 29.02.16.
 */
public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewsAdapter.ViewHolder> {

    private List<Review> mReviews;

    public MovieReviewsAdapter(List<Review> reviews) {
        mReviews = reviews;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindReview(mReviews.get(position));
    }

    @Override
    public int getItemCount() {
        return mReviews != null ? mReviews.size() : 0;
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.unSubscribe();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_movie_poster)
        ImageView mIvPoster;

        @Bind(R.id.tv_movie_title)
        TextView mTvTitle;

        private Subscription mSubscription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindReview(Review review) {
            mTvTitle.setText(review.getDisplayTitle());
            Utils.loadImage(mIvPoster, review.getMultimedia().getSrc());
            mSubscription = RxUtils.generatePalette(review.getMultimedia().getSrc(),
                    mIvPoster.getContext()).subscribe(this::colorizeUi,
                    Throwable::printStackTrace);
        }

        private void colorizeUi(Pair<Palette, Bitmap> paletteBitmapPair) {
            Palette.Swatch swatch = Utils.findSwatchByMostUsedColor(paletteBitmapPair.first
                    .getSwatches());
            if (swatch != null) {
                mTvTitle.setBackgroundColor(swatch.getRgb());
                mTvTitle.setTextColor(swatch.getBodyTextColor());
            }
        }

        public void unSubscribe() {
            if (mSubscription != null) {
                mSubscription.unsubscribe();
            }
        }

    }
}
