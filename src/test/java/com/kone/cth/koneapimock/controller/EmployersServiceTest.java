package com.kone.cth.koneapimock.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployersServiceTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void employers_for_404() throws Exception {
        ResponseEntity<String> response = this.restTemplate
                .getForEntity("/employers/ABN_EXPECTED_404", String.class);

        assertThat(response.getStatusCode() == HttpStatus.NOT_FOUND);

    }

    @Test
    public void employers_for_200() throws Exception {
        ResponseEntity<String> response = this.restTemplate
                .getForEntity("/employers/xxxxx", String.class);

        assertThat(response.getStatusCode() == HttpStatus.OK);

    }

}