package com.kone.cth.koneapimock.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
@RequestMapping("/awpregistration/*")
public class AwpRegistration {

    private static final String MOCK = "mock";

    @PostMapping("usernameallowed")
    public ResponseEntity<String> usernameallowed(@RequestHeader(name = MOCK, required = false) String mock,
                                          @RequestBody Map<String, Object> payload) throws URISyntaxException, Whispir.ActionException {


        if (StringUtils.isEmpty(mock)) {

            return new ResponseEntity<>("{\n" +
                    "    \"Username\": \"mock-user-for-usernameallowed\",\n" +
                    "\t\"Status\" : \"registered\"\n" +
                    "}\n", HttpStatus.OK);
        }

        return generateErrorResponse(mock);

    }

    @PostMapping("createportaluser")
    public ResponseEntity<String> createportaluser(@RequestHeader(name = MOCK, required = false) String mock,
                                          @RequestBody Map<String, Object> payload) throws URISyntaxException, Whispir.ActionException {


        if (StringUtils.isEmpty(mock)) {

            return new ResponseEntity<>("{\n" +
                    "    \"Username\": \"mock-user-for-createportaluser\",\n" +
                    "\t\"Status\" : \"created portaluser\"\n" +
                    "}\n", HttpStatus.OK);
        }

        return generateErrorResponse(mock);

    }

    private ResponseEntity<String> generateErrorResponse(@RequestHeader(name = MOCK, required = false) String mock) {
        if (mock.contains("EXPECTED_400")) {
            return new ResponseEntity("400", HttpStatus.BAD_REQUEST);
        }

        if (mock.contains("EXPECTED_500")) {
            return new ResponseEntity("500", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (mock.contains("EXPECTED_401")) {
            return new ResponseEntity("401", HttpStatus.UNAUTHORIZED);
        }

        if (mock.contains("EXPECTED_403")) {
            return new ResponseEntity("403", HttpStatus.FORBIDDEN);
        }

        if (mock.contains("EXPECTED_404")) {
            return new ResponseEntity("404", HttpStatus.NOT_FOUND);
        }

        if (mock.contains("EXPECTED_405")) {
            return new ResponseEntity("405", HttpStatus.METHOD_NOT_ALLOWED);
        }

        if (mock.contains("EXPECTED_422)")) {
            throw new Whispir.ActionException();
        }
        if (mock.contains("EXPECTED_429")) {
            return new ResponseEntity("429", HttpStatus.TOO_MANY_REQUESTS);
        }
        if (mock.contains("EXPECTED_503")) {
            return new ResponseEntity("503", HttpStatus.SERVICE_UNAVAILABLE);
        }
        return null;
    }


}
