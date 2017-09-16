package com.kone.cth.koneapimock.controller;

import com.kone.cth.koneapimock.model.AaspireAbn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AbnLookup {


    @RequestMapping(path = "/lookup", method = RequestMethod.GET)
    public @ResponseBody
    AaspireAbn lookup(@RequestParam("abn") String abn) {

        AaspireAbn aaspireAbn = new AaspireAbn();
        if( abn.isEmpty() || abn.contains("NotFound")){
            return aaspireAbn;
        }

        aaspireAbn.setId("mock-id-value-" + abn);
        aaspireAbn.setDivision("mock-division-" + abn);
        aaspireAbn.setTrading_name("mock-trading_name-" + abn);

        return aaspireAbn;
    }

}
