package com.e.cryptocracy.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.e.cryptocracy.R;
import com.e.cryptocracy.interfaces.UpdateFavouriteCoinsListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class AppUtils {
    private static final String TAG = "AppUtils";
    private static final String MY_PREFS_NAME = "my_prefs";
    static ProgressDialog progressDialog;


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

    public static void setValue(String key, String value, Context activity) {
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

    public static String getValue(String key, Context activity) {
        if (activity != null) {
            SharedPreferences pref = activity.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            return pref.getString(key, "");
        } else return null;

    }

    public static void updateCurrency(@NotNull TextView tvCurrency, @NotNull FragmentActivity requireActivity) {
        tvCurrency.setText(getValue(AppConstant.CURRENCY, requireActivity));
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity != null) {
            try {
                @SuppressLint("WrongConstant") InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
                View view = activity.getCurrentFocus();
                if (view != null) {
                    IBinder binder = view.getWindowToken();
                    if (binder != null) {
                        inputMethodManager.hideSoftInputFromWindow(binder, 0);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }

        return false;
    }


    public static String getCurrencyFormat(double num) {
        String currency = getValue(AppConstant.CURRENCY, App.context).toUpperCase();
        String COUNTRY = currency.substring(0, 2);
        String LANGUAGE = "en";
        return NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY)).format(num);
    }

    public static String getCurrencyFormat(long num) {
        String currency = getValue(AppConstant.CURRENCY, App.context).toUpperCase();
        String COUNTRY = currency.substring(0, 2);
        String LANGUAGE = "en";
        return NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY)).format(num);
    }

    public static String getCurrencyFormat(String num) {
        String currency = getValue(AppConstant.CURRENCY, App.context).toUpperCase();
        String COUNTRY = currency.substring(0, 2);
        String LANGUAGE = "en";
        return NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY)).format(num);
    }

    public static String setPercentage(double number) {
        return String.format("%.2f", number) + "%";
    }

    public static String prettyCount(Integer number) {
        char[] suffix = {' ', 'k', 'M', 'B', 'T', 'P', 'E'};
        long numValue = number.longValue();
        int value = (int) Math.floor(Math.log10(numValue));
        int base = value / 3;
        if (value >= 3 && base < suffix.length) {
            return new DecimalFormat("#0.0").format(numValue / Math.pow(10, base * 3)) + suffix[base];
        } else {
            return new DecimalFormat("#,##0").format(numValue);
        }
    }

    public static String prettyCount(double number) {
        char[] suffix = {' ', 'k', 'M', 'B', 'T', 'P', 'E'};
        long numValue = (long) number;
        int value = (int) Math.floor(Math.log10(numValue));
        int base = value / 3;
        if (value >= 3 && base < suffix.length) {
            return new DecimalFormat("#0.0").format(numValue / Math.pow(10, base * 3)) + suffix[base];
        } else {
            return new DecimalFormat("#,##0").format(numValue);
        }
    }


    public static void showRequestDialog(Activity activity) {

        //Log.d("Token-Number", AppSettings.getString(AppSettings.token));

        try {
            if (!((Activity) activity).isFinishing()) {
                progressDialog = ProgressDialog.show(activity, null, null, false, true);
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(android.R.color.transparent)));
                progressDialog.setContentView(R.layout.progress_bar);
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showRequestDialog(Context activity) {

        try {
            if (!((Activity) activity).isFinishing()) {
                progressDialog = ProgressDialog.show(activity, null, null, false, true);
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(android.R.color.transparent)));
                progressDialog.setContentView(R.layout.progress_bar);
                progressDialog.show();

            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    public static void hideDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getJSONFromModel(Object o) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(o);
        try {
            JSONObject request = new JSONObject(jsonString);
            return request.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void updateFavCoins(String id, boolean checked, @NotNull UpdateFavouriteCoinsListener updateFavouriteCoinsListener) {
        if (!isNetworkConnected(App.context)) {
            AppConstant.showToast("No Internet");
            return;
        }
        Log.d(TAG, "getUid: " + getUid());
        Log.d(TAG, "checked: " + checked);
        Log.d(TAG, "id: " + id);

        Map<String, Object> map = new HashMap<>();
        map.put("coinId", id);
        if (checked)
            getFireStoreReference().collection(AppConstant.USERS).document(getUid()).collection(AppConstant.FAVOURITE).document(id).set(map).addOnSuccessListener(obj -> updateFavouriteCoinsListener.onSuccess("Added !!")).addOnFailureListener(e -> updateFavouriteCoinsListener.onFailed(e.getLocalizedMessage()));
        else
            getFireStoreReference().collection(AppConstant.USERS).document(getUid()).collection(AppConstant.FAVOURITE).document(id).delete().addOnSuccessListener(obj -> updateFavouriteCoinsListener.onSuccess("Removed !!")).addOnFailureListener(e -> updateFavouriteCoinsListener.onFailed(e.getLocalizedMessage()));
    }
}
