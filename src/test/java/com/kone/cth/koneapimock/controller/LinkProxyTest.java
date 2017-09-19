package com.kone.cth.koneapimock.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LinkProxyTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void lookupForEmployer() throws Exception {
        ResponseEntity<String> response = this.restTemplate
                .getForEntity("/employer/plans/planCode-mock/employers", String.class);

        assertThat(response.getBody()).contains("mock-trading_name-planCode-mock");
    }

    @Test
    public void lookupForEmployerClassification() throws Exception {
        ResponseEntity<String> response = this.restTemplate
                .getForEntity("/employer/plans/planCode-mock-2/employers/id-mock/classification", String.class);

        assertThat(response.getBody()).contains("mock-division-planCode-mock-2");
    }

}