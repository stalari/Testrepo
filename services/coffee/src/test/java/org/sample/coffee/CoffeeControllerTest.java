package org.sample.coffee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CoffeeController.class)
class CoffeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void drink_matchesLegacyCoffeeBeanStringExactly() throws Exception {
        mvc.perform(get("/coffee/drink"))
            .andExpect(status().isOk())
            .andExpect(content().string("drinking coffee!"));
    }
}