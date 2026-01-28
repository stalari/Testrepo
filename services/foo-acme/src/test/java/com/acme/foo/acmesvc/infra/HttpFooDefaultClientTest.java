package com.acme.foo.acmesvc.infra;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpFooDefaultClientTest {

    private MockWebServer server;

    @BeforeEach
    void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void tellMeSomething_callsFooDefaultEndpoint_andReturnsBody() {
        server.enqueue(new MockResponse().setResponseCode(200).setBody("BASE"));

        HttpFooDefaultClient client = new HttpFooDefaultClient(server.url("/").toString(), RestClient.builder());

        assertEquals("BASE", client.tellMeSomething());
    }
}