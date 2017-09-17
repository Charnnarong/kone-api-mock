package com.kone.cth.koneapimock.controller;

import com.kone.cth.koneapimock.model.AaspireAbn;
import com.kone.cth.koneapimock.model.AustralianTaxOfficeAbn;
import org.springframework.web.bind.annotation.*;

@RestController
public class AustralianTaxOffice {

    @RequestMapping(path = "/abrxmlsearch/AbrXmlSearch.asmx/SearchByABN", method = RequestMethod.GET)
    public @ResponseBody
    AustralianTaxOfficeAbn lookup(@RequestParam("abn") String abn) {

        AustralianTaxOfficeAbn aaspireAbn = new AustralianTaxOfficeAbn();
        if( abn.isEmpty() || abn.contains("NotFound")){
            return aaspireAbn;
        }

        aaspireAbn.setAbn("mock-id-value-" + abn);
        aaspireAbn.setTrading_name("mock-trading_name-" + abn);

        return aaspireAbn;
    }
}
