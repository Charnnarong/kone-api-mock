package com.kone.cth.koneapimock.controller;

import ch.qos.logback.core.joran.spi.ActionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
@RequestMapping("/whispir/*")
public class Whispir {

    private static final String MOCK = "mock";

    @PostMapping(path = {"messages", "workspaces/*/*"})
    public ResponseEntity<String> message(@RequestHeader("accept") String accept,
                                          @RequestHeader("Authorization") String auth,
                                          @RequestHeader("Content-Type") String contentType,
                                          @RequestHeader(name = MOCK, required = false) String mock,
                                          @RequestBody Map<String, Object> payload) throws URISyntaxException, ActionException {

        URI location = new URI("https://api.whispir.com/messages/123_mock_message_id?apikey=123_mock_apikey");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        if (StringUtils.isEmpty(mock)) {

            return new ResponseEntity<>("Your request has been accepted for processing.",responseHeaders, HttpStatus.ACCEPTED);
        }

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
            throw new ActionException();
        }
        if (mock.contains("EXPECTED_429")) {
            return new ResponseEntity("429", HttpStatus.TOO_MANY_REQUESTS);
        }
        if (mock.contains("EXPECTED_503")) {
            return new ResponseEntity("503", HttpStatus.SERVICE_UNAVAILABLE);
        }

        return null;

    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "nope")
    public static class ActionException extends RuntimeException {}
}
