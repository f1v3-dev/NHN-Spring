package com.nhnacademy.edu.springboot.account.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springboot.account.domain.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(1)
    @DisplayName("Account : 전체 계좌 조회")
    void testGetAccounts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].number", equalTo("123")))
                .andDo(print());
    }

    @Test
    @Order(2)
    @DisplayName("Account : ID가 1번인 계좌 조회")
    void testGetAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number", equalTo("123")))
                .andExpect(jsonPath("$.balance", equalTo(10000)))
                .andDo(print());
    }

    @Test
    @Order(3)
    @DisplayName("Account : ID가 3번인 계좌 등록")
    void testPostAccount() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Account account = new Account(3L, "8134", 50000);

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number", equalTo("8134")))
                .andExpect(jsonPath("$.balance", equalTo(50000)))
                .andDo(print());
    }

    @Test
    @Order(4)
    @DisplayName("Account : ID가 3번인 계좌 삭제")
    void testDeleteAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/accounts/{id}", 3L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

}