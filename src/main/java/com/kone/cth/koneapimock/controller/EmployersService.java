package com.kone.cth.koneapimock.controller;

import com.kone.cth.koneapimock.model.EmployersServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployersService {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping(path = "/employers/{abn}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<EmployersServiceResponse> employers(@PathVariable("abn") String abn) {


        EmployersServiceResponse employer = new EmployersServiceResponse();
        if( abn.isEmpty() || abn.contains("ABN_EXPECTED_404")){
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if( abn.isEmpty() || abn.contains("ABN_EXPECTED_401")){
            return  new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        if( abn.isEmpty() || abn.contains("ABN_EXPECTED_403")){
            return  new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        if( abn.isEmpty() || abn.contains("ABN_EXPECTED_429")){
            return  new ResponseEntity(HttpStatus.TOO_MANY_REQUESTS);
        }
        if( abn.isEmpty() || abn.contains("ABN_EXPECTED_503")){
            return  new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }

        employer.setId("1234");
        employer.setDivision("division-for-1234");
        Map<String,Map<String,String>> organazation = new HashMap<>();
        Map<String,String> orgDetails = new HashMap<>();
        orgDetails.put("abn","1234");
        orgDetails.put("trading_name","trading_name-for-1234");
        organazation.put("organisation",orgDetails);
        employer.setOrganisation(organazation);

        return new ResponseEntity(employer,HttpStatus.OK);
    }
}
