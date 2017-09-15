package com.kone.cth.koneapimock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class status {


    @RequestMapping(value = "/status", produces = {"application/json", "application/xml"}, method = RequestMethod.GET)
    public String greeting() {
        return "{\"status\": \"OK\"}";
    }
}
