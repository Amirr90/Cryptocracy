package com.e.cryptocracy.utils;

import com.e.cryptocracy.interfaces.API;
import com.e.cryptocracy.interfaces.ApiCallbackInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiUtils {
    public static void getAllCurrency(final ApiCallbackInterface apiCallbackInterface) {
        try {
            final API api = URLUtils.getAPIService();
            Call<ArrayList<String>> specialityResCall = api.getAllCurrency();
            specialityResCall.enqueue(new Callback<ArrayList<String>>() {
                @Override
                public void onResponse(@NotNull Call<ArrayList<String>> call, @NotNull Response<ArrayList<String>> response) {
                    if (response.code() == 200) {
                        if (null != response.body())
                            apiCallbackInterface.onSuccess(response.body());
                    }
                }

                @Override
                public void onFailure(@NotNull Call<ArrayList<String>> call, @NotNull Throwable t) {
                    apiCallbackInterface.onFailed(t.getLocalizedMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void stData() {

    }
}
