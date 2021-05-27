package com.e.cryptocracy.interfaces;

import com.e.cryptocracy.model.MarketModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("api/v3/simple/supported_vs_currencies")
    Call<ArrayList<String>> getAllCurrency();


    @GET("api/v3//coins/markets")
    Call<List<MarketModel>> coinMarketData(@Query("vs_currency") @NotNull String vsCurrency,
                                           @Query("ids") @Nullable String id,
                                           @Query("category") @NotNull String category,
                                           @Query("order") @NotNull String order,
                                           @Query("per_page") int perPage,
                                           @Query("page") @Nullable Integer page,
                                           @Query("price_change_percentage") @NotNull String priceChangePercentage);

    @GET("api/v3//coins/markets")
    Call<List<MarketModel>> coinMarketData(@Query("vs_currency") @NotNull String vsCurrency,
                                           @Query("order") @NotNull String order,
                                           @Query("per_page") int perPage,
                                           @Query("page") @Nullable Integer page,
                                           @Query("price_change_percentage") @NotNull String priceChangePercentage);
}
