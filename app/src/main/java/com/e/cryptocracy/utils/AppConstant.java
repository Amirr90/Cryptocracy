package com.e.cryptocracy.utils;

import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AppConstant {
    public static final int RC_SIGN_IN = 1;
    @NotNull
    public static final String USERS = "users";
    @NotNull
    public static final String TOKEN = "token";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String IMAGE = "image";
    public static final String CURRENCY = "currency";
    public static final String SORT_ORDER = "sort_order";
    public static final String TIMESTAMP = "timestamp";
    @Nullable
    public static final String CATEGORY = "category";
    @Nullable
    public static final String COIN_ID = "coin_id";
    @Nullable
    public static final String PRICE="price";

    public static void showToast(@NotNull String s) {
        Toast.makeText(App.context, s, Toast.LENGTH_SHORT).show();
    }
}
