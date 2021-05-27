package com.e.cryptocracy.utils;

import com.e.cryptocracy.interfaces.API;

public class URLUtils {

    public static final String BASE_URL = "https://api.coingecko.com/";

    public static API getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(API.class);
    }


}
