package com.e.cryptocracy.model;

public class MarketModel {
    private String id;
    private String symbol;
    private String name;
    private String image;
    private double current_price;
    private double market_cap;
    private double market_cap_rank;
    private double fully_diluted_valuation;
    private double total_volume;
    private double high_24h;
    private double low_24h;
    private double price_change_24h;
    private double price_change_percentage_24h;
    private double market_cap_change_24h;
    private double market_cap_change_percentage_24h;
    private double circulating_supply;
    private double total_supply;
    private double max_supply;
    private double ath;
    private double ath_change_percentage;
    private String ath_date;
    private double atl;
    private double atl_change_percentage;
    private String atl_date;
    private Object roi;
    private String last_updated;
    private double price_change_percentage_1h_in_currency;
    private double price_change_percentage_24h_in_currency;
    private double price_change_percentage_7d_in_currency;


    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public double getCurrent_price() {
        return current_price;
    }

    public double getMarket_cap() {
        return market_cap;
    }

    public double getMarket_cap_rank() {
        return market_cap_rank;
    }

    public double getFully_diluted_valuation() {
        return fully_diluted_valuation;
    }

    public double getTotal_volume() {
        return total_volume;
    }

    public double getHigh_24h() {
        return high_24h;
    }

    public double getLow_24h() {
        return low_24h;
    }

    public double getPrice_change_24h() {
        return price_change_24h;
    }

    public double getPrice_change_percentage_24h() {
        return price_change_percentage_24h;
    }

    public double getMarket_cap_change_24h() {
        return market_cap_change_24h;
    }

    public double getMarket_cap_change_percentage_24h() {
        return market_cap_change_percentage_24h;
    }

    public double getCirculating_supply() {
        return circulating_supply;
    }

    public double getTotal_supply() {
        return total_supply;
    }

    public double getMax_supply() {
        return max_supply;
    }

    public double getAth() {
        return ath;
    }

    public double getAth_change_percentage() {
        return ath_change_percentage;
    }

    public String getAthDate() {
        return ath_date;
    }

    public double getAtl() {
        return atl;
    }

    public double getAtl_change_percentage() {
        return atl_change_percentage;
    }

    public String getAtl_date() {
        return atl_date;
    }

    public Object getRoi() {
        return roi;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public double getPrice_change_percentage_1h_in_currency() {
        return price_change_percentage_1h_in_currency;
    }

    public double getPrice_change_percentage_24h_in_currency() {
        return price_change_percentage_24h_in_currency;
    }

    public double getPrice_change_percentage_7d_in_currency() {
        return price_change_percentage_7d_in_currency;
    }
}
