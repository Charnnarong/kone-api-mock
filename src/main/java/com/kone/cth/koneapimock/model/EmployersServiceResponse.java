package com.kone.cth.koneapimock.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 {
     "id": "${id}",
     "division": "${division}",
     "organisation": {
         "abn": "${params.query['abn']}",
         "trading_name": "${trading_name}"
         }
 }
 */
public class EmployersServiceResponse {
    private String id = "";
    private String division = "";
    private Map<String,Map<String,String>> organisation = new HashMap<>();


    public EmployersServiceResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Map<String, Map<String, String>> getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Map<String, Map<String, String>> organisation) {
        this.organisation = organisation;
    }
}
