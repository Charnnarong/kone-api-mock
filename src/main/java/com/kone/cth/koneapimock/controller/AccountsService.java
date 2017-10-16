package com.kone.cth.koneapimock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/accounts/*")
public class AccountsService {

    private static final String MOCK = "mock";

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping(path = "built", method = RequestMethod.HEAD)
    public @ResponseBody
    ResponseEntity<String> built(@RequestHeader(name = MOCK, required = false) String mock) throws URISyntaxException {

        if (StringUtils.isEmpty(mock)) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }

        return generateErrorResponse(mock);
    }

    private ResponseEntity<String> generateErrorResponse(@RequestHeader(name = MOCK, required = false) String mock) {
        if (mock.contains("ACCOUNTS_EXPECTED_400")) {
            return new ResponseEntity("400", HttpStatus.BAD_REQUEST);
        }

        if (mock.contains("ACCOUNTS_EXPECTED_401")) {
            return new ResponseEntity("401", HttpStatus.UNAUTHORIZED);
        }

        if (mock.contains("ACCOUNTS_EXPECTED_403")) {
            return new ResponseEntity("403", HttpStatus.FORBIDDEN);
        }

        if (mock.contains("ACCOUNTS_EXPECTED_404")) {
            return new ResponseEntity("404", HttpStatus.NOT_FOUND);
        }

        if (mock.contains("ACCOUNTS_EXPECTED_405")) {
            return new ResponseEntity("405", HttpStatus.METHOD_NOT_ALLOWED);
        }

        if (mock.contains("ACCOUNTS_EXPECTED_503")) {
            return new ResponseEntity("503", HttpStatus.SERVICE_UNAVAILABLE);
        }
        return null;
    }
}
