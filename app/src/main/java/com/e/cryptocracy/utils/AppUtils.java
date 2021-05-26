package com.e.cryptocracy.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.e.cryptocracy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class AppUtils {
    private static final String TAG = "AppUtils";
    private static final String MY_PREFS_NAME = "my_prefs";

    public static Animation fadeIn(Activity activity) {
        return AnimationUtils.loadAnimation(activity, R.anim.fade_in);
    }

    public static Animation fadeOut(Activity activity) {
        return AnimationUtils.loadAnimation(activity, R.anim.fade_out);
    }

    public static FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    @NotNull
    public static FirebaseFirestore getFireStoreReference() {
        return FirebaseFirestore.getInstance();
    }

    @NotNull
    public static String getUid() {
        return getCurrentUser().getUid();
    }

    public static void updateToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                        return;
                    }
                    String token = task.getResult();
                    Log.d(TAG, token);
                    updateToServer(token);
                });
    }

    private static void updateToServer(String token) {
        getFireStoreReference().collection(AppConstant.USERS).document(getUid()).update(AppConstant.TOKEN, token);
        Log.d(TAG, "TokenUpdatedToServer: " + token);
    }


    public static void updateUserInfo(@Nullable FirebaseUser user) {
        if (null != user) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put(AppConstant.EMAIL, user.getEmail() == null ? "" : user.getEmail());
            userMap.put(AppConstant.CURRENCY, Currency.USD);
            userMap.put(AppConstant.TIMESTAMP, System.currentTimeMillis());
            userMap.put(AppConstant.NAME, user.getDisplayName() == null ? "" : user.getDisplayName());
            getFireStoreReference().collection(AppConstant.USERS).document(getUid()).update(userMap);
        }
    }

    public static void setValue(String key, String value, Activity activity) {
        SharedPreferences sharedpreferences = activity.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getValue(String key, Activity activity) {
        if (activity != null) {
            SharedPreferences pref = activity.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            return pref.getString(key, "");
        } else return null;

    }
}
