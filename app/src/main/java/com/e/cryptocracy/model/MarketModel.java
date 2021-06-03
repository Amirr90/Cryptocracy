package com.e.cryptocracy.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class MarketModel {
    private String id;
    private String symbol;
    private String name;
    private String image;
    private double current_price;
    private double market_cap;
    private int market_cap_rank;
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

    public int getMarket_cap_rank() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarketModel that = (MarketModel) o;
        return Double.compare(that.current_price, current_price) == 0 &&
                Double.compare(that.market_cap, market_cap) == 0 &&
                market_cap_rank == that.market_cap_rank &&
                Double.compare(that.fully_diluted_valuation, fully_diluted_valuation) == 0 &&
                Double.compare(that.total_volume, total_volume) == 0 &&
                Double.compare(that.high_24h, high_24h) == 0 &&
                Double.compare(that.low_24h, low_24h) == 0 &&
                Double.compare(that.price_change_24h, price_change_24h) == 0 &&
                Double.compare(that.price_change_percentage_24h, price_change_percentage_24h) == 0 &&
                Double.compare(that.market_cap_change_24h, market_cap_change_24h) == 0 &&
                Double.compare(that.market_cap_change_percentage_24h, market_cap_change_percentage_24h) == 0 &&
                Double.compare(that.circulating_supply, circulating_supply) == 0 &&
                Double.compare(that.total_supply, total_supply) == 0 &&
                Double.compare(that.max_supply, max_supply) == 0 &&
                Double.compare(that.ath, ath) == 0 &&
                Double.compare(that.ath_change_percentage, ath_change_percentage) == 0 &&
                Double.compare(that.atl, atl) == 0 &&
                Double.compare(that.atl_change_percentage, atl_change_percentage) == 0 &&
                Double.compare(that.price_change_percentage_1h_in_currency, price_change_percentage_1h_in_currency) == 0 &&
                Double.compare(that.price_change_percentage_24h_in_currency, price_change_percentage_24h_in_currency) == 0 &&
                Double.compare(that.price_change_percentage_7d_in_currency, price_change_percentage_7d_in_currency) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(symbol, that.symbol) &&
                Objects.equals(name, that.name) &&
                Objects.equals(image, that.image) &&
                Objects.equals(ath_date, that.ath_date) &&
                Objects.equals(atl_date, that.atl_date) &&
                Objects.equals(roi, that.roi) &&
                Objects.equals(last_updated, that.last_updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol, name, image, current_price, market_cap, market_cap_rank, fully_diluted_valuation, total_volume, high_24h, low_24h, price_change_24h, price_change_percentage_24h, market_cap_change_24h, market_cap_change_percentage_24h, circulating_supply, total_supply, max_supply, ath, ath_change_percentage, ath_date, atl, atl_change_percentage, atl_date, roi, last_updated, price_change_percentage_1h_in_currency, price_change_percentage_24h_in_currency, price_change_percentage_7d_in_currency);
    }


    public static DiffUtil.ItemCallback<MarketModel> itemCallback = new DiffUtil.ItemCallback<MarketModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull MarketModel oldItem, @NonNull MarketModel newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull MarketModel oldItem, @NonNull MarketModel newItem) {
            return oldItem.equals(newItem);
        }
    };
}
