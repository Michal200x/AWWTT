package com.example.awwtt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class DataControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAddFormView() throws Exception {
        mockMvc.perform(get("/add-by-keyboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-form"));
    }
    @Test
    public void shouldSaveDataAndRedirect() throws Exception {
        mockMvc.perform(post("/save")
                        .param("massBeforeFriction", "100")
                        .param("massAfterFriction", "90")
                        .param("pressureForce", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }
    @Test
    public void shouldDisplayDataInView() throws Exception {
        mockMvc.perform(post("/save")
                .param("massBeforeFriction", "100")
                .param("massAfterFriction", "90")
                .param("pressureForce", "5"));

        mockMvc.perform(get("/data-view"))
                .andExpect(status().isOk())
                .andExpect(view().name("data-view"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("&Delta;m")));
    }


}
