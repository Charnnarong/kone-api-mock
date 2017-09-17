package com.kone.cth.koneapimock.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbnLookupTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void lookup() throws Exception {
        ResponseEntity<String> response = this.restTemplate
                .getForEntity("/lookup?abn=testing", String.class);

        assertThat(response.getBody()).contains("mock-id-value-testing");

    }



}