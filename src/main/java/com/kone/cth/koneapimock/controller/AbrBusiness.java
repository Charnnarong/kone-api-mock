package com.kone.cth.koneapimock.controller;

import com.kone.cth.koneapimock.model.AaspireAbn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class AbrBusiness {

    @RequestMapping(path = "/abrxmlsearch/AbrXmlSearch.asmx/SearchByABNv201408", method = RequestMethod.POST , produces={"text/xml"})
    public String lookup(@RequestParam("searchString") String searchString,
                      @RequestParam("includeHistoricalDetails") String includeHistoricalDetails,
                      @RequestParam("authenticationGuid") String authenticationGuid) throws IOException {

        File file = new ClassPathResource("templates/ABRXMLSearchSuccess.xml").getFile();
        String contents = new String(Files.readAllBytes(file.toPath()));

        contents = contents.replace("$authenticationGUID", authenticationGuid);
        contents = contents.replace("$identifierType", "ABN");
        contents = contents.replace("$identifierValue", searchString);
        contents = contents.replace("$history", includeHistoricalDetails);
        contents = contents.replace("$dateRegisterLastUpdated", "0001-01-01");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date date = new Date();
        contents = contents.replace("$dateTimeRetrieved", dateFormat.format(date));
        contents = contents.replace("$exceptionDescription", "Search text is not a valid ABN or ACN");
        contents = contents.replace("$exceptionCode", "WEBSERVICES");
        return contents;
    }
}
