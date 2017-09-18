package com.kone.cth.koneapimock.controller;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbrBusinessTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void lookup() throws Exception {

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("Accept", "text/xml");
        map.add("Accept-Encoding", "gzip, deflate");
        map.add("Content-Type", "application/x-www-form-urlencoded");

//        ?searchString=1234&includeHistoricalDetails=N&authenticationGuid=656d30dd-31ff-4fda-8322-858e9d0b1bd9
        Map<String,String> form = new HashMap<>();
        form.put("searchString","1234");
        form.put("includeHistoricalDetails","N");
        form.put("authenticationGuid","123456-abcdef-789-ghijkl");

        ResponseEntity<String> response = this.restTemplate
                .postForEntity("/abrxmlsearch/AbrXmlSearch.asmx/SearchByABNv201408?searchString=1234&includeHistoricalDetails=N&authenticationGuid=656d30dd-31ff-4fda-8322-858e9d0b1bd9", map,String.class);

        File responseExpectedFile = new ClassPathResource("AbrXmlSearchResult.xml").getFile();
        String responseExpected = new String(Files.readAllBytes(responseExpectedFile.toPath()));

        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreWhitespace(true);

        DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(responseExpected,response.getBody()));
        List<?> allDiff = diff.getAllDifferences();
        assertThat(allDiff.size()).isEqualTo(1);
        assertThat(allDiff.get(0).toString()).contains("dateTimeRetrieved");

    }

}