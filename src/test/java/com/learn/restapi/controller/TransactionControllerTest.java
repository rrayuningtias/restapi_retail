package com.learn.restapi.controller;

import com.learn.restapi.LearnRestAPIApplication;
import com.learn.restapi.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(classes = LearnRestAPIApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/transaction")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Success Find All Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void findById() throws Exception {
        int id = 1;
        mockMvc.perform(get("/transaction/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Success Find Data By ID")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void create() throws Exception {
        String json = "{\n" +
                " \"date\": \"2019-06-17\",\n" +
                " \"idUser\": 1,\n" +
                " \"idWarehouse\": 1,\n" +
                " \"qtyTransaction\": 2\n" +
                "}";
        log.info(json);
        log.info("========================================" + String.valueOf(jsonPath("$.idUser", Matchers.is("int"))));

        mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Success Create Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

}
