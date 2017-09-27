package com.kone.cth.koneapimock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/whispir")
public class Whispir {

    private static final String MOCK = "mock";
    @PostMapping(path = "/message")
    public ResponseEntity<Object> message(@RequestParam("Accept") String accept,
                                          @RequestParam("Authorization") String auth,
                                          @RequestParam("Content-Type") String contentType,
                                          @RequestParam( name = MOCK , required = false) String mock,
                                          @RequestBody Map<String,Object> payload){

        if(StringUtils.isEmpty(mock)){
            return new ResponseEntity( "Your request has been accepted for processing." , HttpStatus.ACCEPTED);
        }

        if(mock.contains("EXPECTED_400")){
            return new ResponseEntity( "400" , HttpStatus.BAD_REQUEST);
        }

        if(mock.contains("EXPECTED_500")){
            return new ResponseEntity( "500" , HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(mock.contains("EXPECTED_401")){
            return new ResponseEntity( "401" , HttpStatus.UNAUTHORIZED);
        }

        if(mock.contains("EXPECTED_403")){
            return new ResponseEntity( "403" , HttpStatus.FORBIDDEN);
        }

        if(mock.contains("EXPECTED_404")){
            return new ResponseEntity( "404" , HttpStatus.NOT_FOUND);
        }

        if(mock.contains("EXPECTED_405")){
            return new ResponseEntity( "405" , HttpStatus.METHOD_NOT_ALLOWED);
        }

        if(mock.contains("EXPECTED_422)")){
            return new ResponseEntity( "422" , HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if(mock.contains("EXPECTED_429")){
            return new ResponseEntity( "429" , HttpStatus.TOO_MANY_REQUESTS);
        }
        if(mock.contains("EXPECTED_503")){
            return new ResponseEntity( "503" , HttpStatus.SERVICE_UNAVAILABLE);
        }

        return null;

    }
}
