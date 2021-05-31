package com.e.cryptocracy.model;

import android.media.Image;

public class CoinModel {
    String id;
    String symbol;
    double current_price;
    String name;
    String market_cap_rank;
    String coingecko_rank;
    MarketData market_data;
    Images image;

    public Images getImage() {
        return image;
    }

    public MarketData getMarket_data() {
        return market_data;
    }

    public void setCurrent_price(double current_price) {
        this.current_price = current_price;
    }

    public double getCurrent_price() {
        return current_price;
    }

    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getMarket_cap_rank() {
        return market_cap_rank;
    }

    public String getCoingecko_rank() {
        return coingecko_rank;
    }

    public class MarketData {
        double price_change_24h;
        double price_change_percentage_24h;
        double price_change_percentage_7d;
        double price_change_percentage_14d;
        double price_change_percentage_30d;
        double price_change_percentage_60d;
        double price_change_percentage_200d;
        double price_change_percentage_1y;
        double market_cap_change_24h;
        double market_cap_change_percentage_24h;


        public double getPrice_change_24h() {
            return price_change_24h;
        }

        public double getPrice_change_percentage_24h() {
            return price_change_percentage_24h;
        }

        public double getPrice_change_percentage_7d() {
            return price_change_percentage_7d;
        }

        public double getPrice_change_percentage_14d() {
            return price_change_percentage_14d;
        }

        public double getPrice_change_percentage_30d() {
            return price_change_percentage_30d;
        }

        public double getPrice_change_percentage_60d() {
            return price_change_percentage_60d;
        }

        public double getPrice_change_percentage_200d() {
            return price_change_percentage_200d;
        }

        public double getPrice_change_percentage_1y() {
            return price_change_percentage_1y;
        }

        public double getMarket_cap_change_24h() {
            return market_cap_change_24h;
        }

        public double getMarket_cap_change_percentage_24h() {
            return market_cap_change_percentage_24h;
        }
    }


    @Override
    public String toString() {
        return "CoinModel{" +
                "id='" + id + '\'' +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", market_cap_rank='" + market_cap_rank + '\'' +
                ", coingecko_rank='" + coingecko_rank + '\'' +
                '}';
    }

    public class Images{
        String thumb;
        String small;
        String large;

        public String getThumb() {
            return thumb;
        }

        public String getSmall() {
            return small;
        }

        public String getLarge() {
            return large;
        }
    }
}
