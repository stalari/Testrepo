package com.acme.bar.acmesvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BarController.class)
class BarControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void sayHello_matchesLegacyAcmeBarBeanStringExactly() throws Exception {
        mvc.perform(get("/bar/hello"))
            .andExpect(status().isOk())
            .andExpect(content().string("Hey Dude, ACME wants to say H3ll0 to you!"));
    }
}