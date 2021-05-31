package com.e.cryptocracy.interfaces;

import com.e.cryptocracy.model.CoinModel;
import com.e.cryptocracy.model.CountryList;
import com.e.cryptocracy.model.ExchangeModel;
import com.e.cryptocracy.model.MarketModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {
    @GET("api/v3/simple/supported_vs_currencies")
    Call<ArrayList<String>> getAllCurrency();


    @GET("api/v3/coins/markets")
    Call<List<MarketModel>> coinMarketData(@Query("vs_currency") @NotNull String vsCurrency,
                                           @Query("ids") @Nullable String id,
                                           @Query("category") @NotNull String category,
                                           @Query("order") @NotNull String order,
                                           @Query("per_page") int perPage,
                                           @Query("page") @Nullable Integer page,
                                           @Query("price_change_percentage") @NotNull String priceChangePercentage);

    @GET("api/v3/coins/markets")
    Call<List<MarketModel>> coinMarketData(@Query("vs_currency") @NotNull String vsCurrency,
                                           @Query("order") @NotNull String order,
                                           @Query("per_page") int perPage,
                                           @Query("page") @Nullable Integer page,
                                           @Query("price_change_percentage") @NotNull String priceChangePercentage);

    @GET("api/v3/coins/{id}")
    Call<CoinModel> getCoinDataById(@Path("id") @NotNull String id,
                                    @Query("localization") @NotNull String localization,
                                    @Query("tickers") Boolean tickers,
                                    @Query("market_data") @Nullable Boolean market_data,
                                    @Query("community_data") @Nullable Boolean community_data,
                                    @Query("developer_data") @Nullable Boolean developer_data,
                                    @Query("sparkline") @Nullable Boolean sparkline);

    @GET("api/v3/coins/{id}/ohlc")
    Call<ArrayList<ArrayList<String>>> get_OHLC_Data(@Path("id") @NotNull String id,
                                                     @Query("days") @NotNull String duration,
                                                     @Query("vs_currency") @Nullable String days);

    @GET("api/v3/exchanges")
    Call<List<ExchangeModel>> getExchangeData(
            @Query("per_page") @NotNull String per_page,
            @Query("page") @NotNull String page
    );

    @GET("api/v3/events/countries")
    Call<CountryList>countries();
}
