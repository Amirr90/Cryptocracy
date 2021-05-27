package com.e.cryptocracy.utils;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.e.cryptocracy.R;

public class CustomLoadImage {
    private static final String TAG = "CustomLoadImage";

    @BindingAdapter("android:loadCustomImage")
    public static void loadImage(ImageView imageView, String imagePath) {
        if (null != imagePath && !imagePath.isEmpty()) {
            try {
                Glide.with(App.context)
                        .load(imagePath)
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(imageView);

            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "loadImage: " + e.getLocalizedMessage());
            }
        }

    }
}
