package com.kone.cth.koneapimock.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class status {


    @RequestMapping(path = "/status",  method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> greeting() {

        Map<String, String> status = new HashMap<>();
        status.put("status", "OK");
        return new ResponseEntity<Map<String, String>>(status,  HttpStatus.OK);
    }
}
