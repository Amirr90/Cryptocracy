package com.e.cryptocracy.model;

public class ExchangeModel {
    private String id;
    private String name;
    private long year_established;
    private String country;
    private String description;
    private String url;
    private String image;
    private boolean has_trading_incentive;
    private long trust_score;
    private long trust_score_rank;
    private double trade_volume_24h_btc;
    private double trade_volume_24h_btc_normalized;
    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getYear_established() {
        return year_established;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public boolean isHas_trading_incentive() {
        return has_trading_incentive;
    }

    public long getTrust_score() {
        return trust_score;
    }

    public long getTrust_score_rank() {
        return trust_score_rank;
    }

    public double getTrade_volume_24h_btc() {
        return trade_volume_24h_btc;
    }

    public double getTrade_volume_24h_btc_normalized() {
        return trade_volume_24h_btc_normalized;
    }
}
