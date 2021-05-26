package com.nuce.service_gara.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class exTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testEmployee() throws Exception {
        this.mockMvc.perform(get("/api/test/unit"))
                .andExpect(jsonPath("$.name").value("hong"))
                .andExpect(jsonPath("$.username").value("hong"));
    }

}
