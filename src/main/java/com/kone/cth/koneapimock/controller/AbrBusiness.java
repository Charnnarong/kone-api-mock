package com.kone.cth.koneapimock.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class AbrBusiness {

    @RequestMapping(path = "/abrxmlsearch/AbrXmlSearch.asmx/SearchByABNv201408", method = RequestMethod.POST, produces = {"text/xml"})
    public ResponseEntity<String> lookup(@RequestParam("searchString") String searchString,
                                         @RequestParam("includeHistoricalDetails") String includeHistoricalDetails,
                                         @RequestParam("authenticationGuid") String authenticationGuid) throws IOException {

        String responseXml = "";
        ResponseEntity<String> responseEntity = null;
        if (searchString.contains("EXPECTED_500")) {
            responseXml = generate500Response(searchString, includeHistoricalDetails, authenticationGuid);
            responseEntity = new ResponseEntity<>(responseXml, HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (searchString.contains("EXPECTED_404")) {
            responseXml = generate404Response(searchString, includeHistoricalDetails, authenticationGuid);
            responseEntity = new ResponseEntity<>(responseXml, HttpStatus.NOT_FOUND);
        } else {
            responseXml = generate200Response(searchString, includeHistoricalDetails, authenticationGuid);
            responseEntity = new ResponseEntity<>(responseXml, HttpStatus.OK);
        }
        return responseEntity;

    }

    private String generate200Response(String searchString, String includeHistoricalDetails, String authenticationGuid) throws IOException {

        String contents = getContents("templates/ABRXMLSearch_200.xml");

        contents = generateCommonResponse(searchString, includeHistoricalDetails, authenticationGuid, contents);

        return contents;
    }


    private String generate404Response(String searchString, String includeHistoricalDetails, String authenticationGuid) throws IOException {

        String contents = getContents("templates/ABRXMLSearch_404.xml");

        contents = generateCommonResponse(searchString, includeHistoricalDetails, authenticationGuid, contents);
        contents = generateCommonException(contents);
        return contents;
    }

    private String generate500Response(@RequestParam("searchString") String searchString, @RequestParam("includeHistoricalDetails") String includeHistoricalDetails, @RequestParam("authenticationGuid") String authenticationGuid) throws IOException {

        String contents = getContents("templates/ABRXMLSearch_500.xml");

        contents = generateCommonResponse(searchString, includeHistoricalDetails, authenticationGuid, contents);
        contents = generateCommonException(contents);
        return contents;
    }

    private String getContents(String filePath) throws IOException {
        File file = new ClassPathResource(filePath).getFile();
        return new String(Files.readAllBytes(file.toPath()));
    }

    private String generateCommonResponse(String searchString, String includeHistoricalDetails, String authenticationGuid, String contents) {
        contents = contents.replace("$authenticationGUID", authenticationGuid);
        contents = contents.replace("$identifierType", "ABN");
        contents = contents.replace("$identifierValue", searchString);
        contents = contents.replace("$history", includeHistoricalDetails);
        contents = contents.replace("$dateRegisterLastUpdated", "0001-01-01");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date date = new Date();
        contents = contents.replace("$dateTimeRetrieved", dateFormat.format(date));
        return contents;
    }

    private String generateCommonException(String contents) {
        contents = contents.replace("$exceptionDescription", "Search text is not a valid ABN or ACN");
        contents = contents.replace("$exceptionCode", "WEBSERVICES");
        return contents;
    }
}
