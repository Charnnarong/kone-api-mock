package com.kone.cth.koneapimock.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WhispirTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void message_202() throws Exception {

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("Accept", "application/vnd.whispir.message-v1+json");
        map.add("Authorization", "Basic 123456");
        map.add("Content-Type", "application/vnd.whispir.message-v1+json");

        File requestPayloadFile = new ClassPathResource("Whispir/RequestSendEmail.json").getFile();
        String requestPayload = new String(Files.readAllBytes(requestPayloadFile.toPath()));

        ResponseEntity<String> response = this.restTemplate
                .postForEntity("/whispir/message?apikey=12345", requestPayload,String.class);

        assertThat(response.getStatusCode().equals(HttpStatus.ACCEPTED));
    }

    @Test
    public void message_400() throws Exception {

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("Accept", "application/vnd.whispir.message-v1+json");
        map.add("Authorization", "Basic 123456");
        map.add("Content-Type", "application/vnd.whispir.message-v1+json");
        map.add("mock", "EXPECTED_400");

        File requestPayloadFile = new ClassPathResource("Whispir/RequestSendEmail.json").getFile();
        String requestPayload = new String(Files.readAllBytes(requestPayloadFile.toPath()));

        ResponseEntity<String> response = this.restTemplate
                .postForEntity("/whispir/message?apikey=12345", requestPayload,String.class);

        assertThat(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void message_null() throws Exception {

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("Accept", "application/vnd.whispir.message-v1+json");
        map.add("Authorization", "Basic 123456");
        map.add("Content-Type", "application/vnd.whispir.message-v1+json");
        map.add("mock", "xxxx");

        File requestPayloadFile = new ClassPathResource("Whispir/RequestSendEmail.json").getFile();
        String requestPayload = new String(Files.readAllBytes(requestPayloadFile.toPath()));

        ResponseEntity<String> response = this.restTemplate
                .postForEntity("/whispir/message?apikey=12345", requestPayload,String.class);

        assertThat(response == null);
    }



}