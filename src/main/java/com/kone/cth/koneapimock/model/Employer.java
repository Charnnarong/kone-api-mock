package com.kone.cth.koneapimock.model;

public class Employer {

    private String id;
    private String trading_name;
    private EmployersClassification employersClassification;


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

    public EmployersClassification getEmployersClassification() {
        return employersClassification;
    }

    public void setEmployersClassification(EmployersClassification employersClassification) {
        this.employersClassification = employersClassification;
    }
}
