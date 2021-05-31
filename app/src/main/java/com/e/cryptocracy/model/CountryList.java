package com.e.cryptocracy.model;

import java.util.ArrayList;

public class CountryList {
    ArrayList<Country> data;
    int count;

    public ArrayList<Country> getData() {
        return data;
    }

    public int getCount() {
        return count;
    }

    public class Country {
        String country;
        String code;

        public String getCountry() {
            return country;
        }

        public String getCode() {
            return code;
        }
    }
}

