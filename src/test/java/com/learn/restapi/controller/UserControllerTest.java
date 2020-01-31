package com.learn.restapi.controller;

import com.learn.restapi.LearnRestAPIApplication;
import com.learn.restapi.service.UserService;
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
public class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void findAll() throws Exception{
        mockMvc.perform(get("/user")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Success Find All Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void findById() throws Exception{
        int id = 1;
        mockMvc.perform(get("/user/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Success Find Data By ID")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
//        panjang json ada 2
    }

    @Test
    public void create() throws Exception {
        String json = "{\n" +
                "  \"saldoUser\": 1000,\n" +
                "  \"username\": \"string\"\n" +
                "}";
        log.info(json);
        log.info("======================================" + String.valueOf(jsonPath("$.userName", Matchers.is("string"))));

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Success Create Data")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void updateById() throws Exception{
        int id = 1;
        String json =   "{\n" +
                "  \"saldoUser\": 2000,\n" +
                "  \"username\": \"string\"\n" +
                "}";
        log.info(json);
        log.info("======================================" + String.valueOf(jsonPath("$.userName", Matchers.is("string"))));
        mockMvc.perform(put("/user/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Update Success")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

    @Test
    public void deleteById() throws Exception{
        int id = 1;
        mockMvc.perform(delete("/user/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("Data Deleted")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }

}

