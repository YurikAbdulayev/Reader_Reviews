package com.reader.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.reader.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by yurik on 29.02.16.
 */
public class Utils {
    public static boolean hasInternet(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static boolean hasApi21() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static Palette.Swatch findSwatchByMostUsedColor(List<Palette.Swatch> swatches) {
        return Collections.max(swatches, (lhs, rhs) -> lhs.getPopulation() > rhs.getPopulation() ? 1 :
                lhs.getPopulation() == rhs.getPopulation() ? 0 : -1);
    }

    public static void loadImage(ImageView imageView, String url) {
        if (imageView == null || imageView.getContext() == null
                || imageView.getContext().isRestricted()) {
            return;
        }
        Glide
                .with(imageView.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    public static void showRetryDialog(Context context, @StringRes int message,
                                       @Nullable DialogInterface.OnClickListener onClickListener) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(R.string.btn_retry, onClickListener)
                .setNegativeButton(android.R.string.no, null)
                .create();
        alertDialog.show();
    }

}
