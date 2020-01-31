package com.learn.restapi.controller;

import com.learn.restapi.LearnRestAPIApplication;
import com.learn.restapi.service.WarehouseService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest(classes = LearnRestAPIApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class WarehouseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private WarehouseService warehouseService;

    @InjectMocks
    private WarehouseController warehouseController;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(warehouseController).build();
    }

    @Test
    public void findAll() throws Exception{
        mockMvc.perform(get("/warehouse/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Success Find All Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void findById() throws Exception{
        int id = 1;
        mockMvc.perform(get("/warehouse/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Success Find Data By ID")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void create() throws Exception {
        String json = "{\n" +
                " \"hargaBarang\": 10000, \n" +
                " \"merkBarang\": \"String\", \n" +
                " \"namaBarang\": \"String\", \n" +
                " \"stockBarang\": 100 \n" +
                "}";

        log.info(json);
        log.info("======================================" + String.valueOf(jsonPath("$.namaBarang", Matchers.is("string"))));

        mockMvc.perform(post("/warehouse/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Success Create Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void update() throws Exception {
        int id = 1;
        String json = "{\n" +
                " \"hargaBarang\": 10000, \n" +
                " \"merkBarang\": \"String\", \n" +
                " \"namaBarang\": \"String\", \n" +
                " \"stockBarang\": 100 \n" +
                "}";

        log.info(json);
        log.info("======================================" + String.valueOf(jsonPath("$.namaBarang", Matchers.is("string"))));
        mockMvc.perform(put("/warehouse/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Update Success")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void deleteById() throws Exception {
        int id = 1;
        mockMvc.perform(delete("/warehouse/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Data Deleted")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }
}
