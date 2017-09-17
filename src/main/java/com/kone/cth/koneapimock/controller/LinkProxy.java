package com.kone.cth.koneapimock.controller;

import com.kone.cth.koneapimock.model.AaspireAbn;
import com.kone.cth.koneapimock.model.Employer;
import com.kone.cth.koneapimock.model.EmployersClassification;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employer/plans")
public class LinkProxy {

    @RequestMapping(path = "/{planCode}/employers", method = RequestMethod.GET)
    public @ResponseBody
    Employer lookup(@PathVariable String planCode) {

        Employer employer = new Employer();
        if( planCode.isEmpty() || planCode.contains("NotFoundPlanCode")){
            return employer;
        }

        employer.setId("mock-id-value-" + planCode);
        EmployersClassification employersClassification =  new EmployersClassification();
        employersClassification.setDivision("mock-division-" + planCode);
        employer.setEmployersClassification(employersClassification);
        employer.setTrading_name("mock-trading_name-" + planCode);

        return employer;
    }

    @RequestMapping(path = "/{planCode}/employers/{id}/classification", method = RequestMethod.GET)
    public @ResponseBody
    EmployersClassification lookup(@PathVariable String planCode, @PathVariable String id) {

        EmployersClassification employersClassification = new EmployersClassification();
        if( planCode.isEmpty() || planCode.contains("NotFoundPlanCode") || id.isEmpty() || id.contains("NotFoundId")){
            return employersClassification;
        }

        employersClassification.setDivision("mock-division-" + planCode);

        return employersClassification;
    }

}
