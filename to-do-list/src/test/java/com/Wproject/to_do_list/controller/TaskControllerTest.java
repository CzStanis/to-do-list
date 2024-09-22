package com.Wproject.to_do_list.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskControllerTest {

    private WireMockServer wireMockServer;

    @BeforeEach
    public void setUp() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8080));
        wireMockServer.start();

        wireMockServer.stubFor(post(urlEqualTo("/api/tasks/add"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(matchingJsonPath("$.name"))
                .willReturn(aResponse()
                        .withStatus(201)));
//        Unirest.config().setObjectMapper(new ObjectMapper() {
//            ObjectMapper mapper = new ObjectMapper();
//            public String writeValue(Object value) {
//                return mapper.writeValueAsString(value);
//            }
//
//            public <T> T readValue(String value, Class<T> valueType) {
//                return mapper.readValue(value, valueType);
//            }
//        });
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }

    @Test
    public void testCreateTask() {

        String requestBody = "{ \"name\": \"Test Task\", \"completed\": \"false\" }";

        HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/api/tasks/add")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .asJson();

        assertEquals(201, response.getStatus());
    }
}
