package dev.aga.spqr.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class QueriesTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void testQueries() throws Exception {
        var query = """
            {
            "query":"query MyModel($id: ID!) {\\n myModel(id: $id) {\\n id\\n }\\n}",
            "variables":{"id":"1"}
            }
            """;

        var req = post("/queries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(query);

        mvc.perform(req)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.errors").doesNotExist())
            .andExpect(jsonPath("$.data").exists());
    }

}
