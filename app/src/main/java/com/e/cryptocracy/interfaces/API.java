package com.e.cryptocracy.interfaces;

import com.e.cryptocracy.model.CoinModel;
import com.e.cryptocracy.model.CountryList;
import com.e.cryptocracy.model.DerivativeModel;
import com.e.cryptocracy.model.EventTypeModel;
import com.e.cryptocracy.model.ExchangeModel;
import com.e.cryptocracy.model.MarketModel;
import com.e.cryptocracy.model.responseModel.EventResponse;
import com.e.cryptocracy.model.responseModel.TrendingCoinResponse;

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
    Call<CountryList> countries();

    @GET("api/v3/events/types")
    Call<EventTypeModel> getEventData();


    @GET("api/v3/events")
    Call<EventTypeModel> getEventData(
            @Query("country_code") @Nullable String countryCode,
            @Query("type") @Nullable String type,
            @Query("page") int perPage,
            @Query("upcoming_events_only") boolean upcomingEventsOnly,
            @Query("from_date") @NotNull String fromDate,
            @Query("to_date") @NotNull String toDate);


    @GET("api/v3/events")
    Call<EventResponse> getEventData(
            @Query("country_code") @Nullable String countryCode,
            @Query("type") @Nullable String type,
            @Query("page") int perPage,
            @Query("upcoming_events_only") boolean upcomingEventsOnly);

    @GET("api/v3/derivatives")
    Call<List<DerivativeModel>> derivativeData(
            @Query("include_tickers") @NotNull String includeTickers);

    @GET("api/v3/search/trending")
    Call<TrendingCoinResponse> loadTendingCoinData();
}


