package com.kone.cth.koneapimock.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employer {

    private String id;
    private String trading_name;

    public Employer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrading_name() {
        return trading_name;
    }

    public void setTrading_name(String trading_name) {
        this.trading_name = trading_name;
    }


}
