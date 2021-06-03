package com.e.cryptocracy.model;

public class DerivativeModel {
    private String market;
    private String symbol;
    private String index_id;
    private String price;
    private double price_percentage_change_24H;
    private String contract_type;
    private double index;
    private double basis;
    private double spread;
    private double funding_rate;
    private double open_interest;
    private double volume_24h;
    private long last_traded_at;
    private String expired_at;
    private String position;


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMarket() {
        return market;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getIndex_id() {
        return index_id;
    }

    public String getPrice() {
        return price;
    }

    public double getPrice_percentage_change_24H() {
        return price_percentage_change_24H;
    }

    public String getContract_type() {
        return contract_type;
    }

    public double getIndex() {
        return index;
    }

    public double getBasis() {
        return basis;
    }

    public double getSpread() {
        return spread;
    }

    public double getFunding_rate() {
        return funding_rate;
    }

    public double getOpen_interest() {
        return open_interest;
    }

    public double getVolume_24h() {
        return volume_24h;
    }

    public long getLast_traded_at() {
        return last_traded_at;
    }

    public String getExpired_at() {
        return expired_at;
    }

    @Override
    public String toString() {
        return "DerivativeModel{" +
                "market='" + market + '\'' +
                ", symbol='" + symbol + '\'' +
                ", index_id='" + index_id + '\'' +
                ", price='" + price + '\'' +
                ", price_percentage_change_24H=" + price_percentage_change_24H +
                ", contract_type='" + contract_type + '\'' +
                ", index=" + index +
                ", basis=" + basis +
                ", spread=" + spread +
                ", funding_rate=" + funding_rate +
                ", open_interest=" + open_interest +
                ", volume_24H=" + volume_24h +
                ", last_traded_at=" + last_traded_at +
                ", expired_at='" + expired_at + '\'' +
                '}';
    }
}
