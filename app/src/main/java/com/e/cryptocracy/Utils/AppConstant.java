package com.e.cryptocracy.Utils;

import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class AppConstant {
    public static final int RC_SIGN_IN = 1;

    public static void showToast(@NotNull String s) {
        Toast.makeText(App.context, s, Toast.LENGTH_SHORT).show();
    }
}
