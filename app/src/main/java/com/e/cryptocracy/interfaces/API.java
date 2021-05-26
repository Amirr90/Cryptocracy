package com.e.cryptocracy.interfaces;

import com.e.cryptocracy.model.responseModel.CurrencyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("simple/supported_vs_currencies")
    Call<ArrayList<String>> getAllCurrency();
}
