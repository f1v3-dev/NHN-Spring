package com.nhnacademy.edu.springboot.account.controller;


import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springboot.account.domain.Account;
import com.nhnacademy.edu.springboot.account.repository.AccountRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class AccountRestControllerMockBeanTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountRepository accountRepository;

    @Test
    @DisplayName("Account : 모든 계좌 정보 조회")
    void testGetAccounts() throws Exception {
        given(accountRepository.findAll())
                .willReturn(List.of(new Account(1L, "123-456", 10000)));

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].number", equalTo("123-456")))
                .andExpect(jsonPath("$[0].balance", equalTo(10000)))
                .andDo(print());
    }

    @Test
    @DisplayName("Account : 1번 계좌 정보 조회")
    void testGetAccount() throws Exception {

        Optional<Account> account = Optional.of(new Account(1L, "123-456", 100000));

        given(accountRepository.findById(anyLong()))
                .willReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number", equalTo("123-456")))
                .andExpect(jsonPath("$.balance", equalTo(100000)))
                .andDo(print());
    }

    @Test
    @DisplayName("Account : 새로운 계좌 등록")
    void testPostAccount() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Account account = new Account(1L, "1234-5678", 12345);

        given(accountRepository.save(any()))
                .willReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(account)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number", equalTo(account.getNumber())))
                .andExpect(jsonPath("$.balance", equalTo(account.getBalance())))
                .andDo(print());
    }

    @Test
    @DisplayName("Account : 1번 계좌 삭제")
    void testDeleteAccount() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/accounts/{id}", 1))
                .andExpect(status().isOk())
                .andDo(print());
    }
}