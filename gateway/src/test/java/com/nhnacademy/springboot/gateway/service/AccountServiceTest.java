package com.nhnacademy.springboot.gateway.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import com.nhnacademy.springboot.gateway.adaptor.AccountAdaptor;
import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.account.AccountDeleteResponse;
import com.nhnacademy.springboot.gateway.domain.account.AccountLoginRequestDto;
import com.nhnacademy.springboot.gateway.domain.account.AccountRegisterRequestDto;
import com.nhnacademy.springboot.gateway.domain.account.CheckAccount;
import com.nhnacademy.springboot.gateway.domain.task.member.UserDto;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @MockBean
    AccountAdaptor accountAdaptor;


    @Test
    @DisplayName("회원 목록 조회")
    void testGetAccounts() {
        given(accountAdaptor.getAccounts()).willReturn(
                List.of(new Account(null, null, "nhn", "1234", null, null, null)));

        assertThat(accountService.getAccounts().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("회원 아이디로 정보 조회")
    void testGetAccountById() {
        Account test = new Account(1L, "nhn", "nhn", "1234", "nhn@email.com", "010-1234-1234", "1");
        given(accountAdaptor.getAccount(anyLong())).willReturn(test);
        Account result = accountService.getAccount(1L);

        assertThat(result.getUserId()).isEqualTo(test.getUserId());
        assertThat(result.getPassword()).isEqualTo(test.getPassword());
        assertThat(result.getEmail()).isEqualTo(test.getEmail());
    }

    @Test
    @DisplayName("회원 정보 등록")
    void testCreateAccount() {
        Account test = new Account(1L, "nhn", "nhn", "1234", "nhn@email.com", "010-1234-1234", "1");
        given(accountAdaptor.createAccount(any())).willReturn(test);
        Account result = accountService.createAccount(new AccountRegisterRequestDto(test.getName(), test.getUserId(), test.getPassword(), test.getEmail(), test.getPhoneNumber()));

        assertThat(result.getUserId()).isEqualTo(test.getUserId());
        assertThat(result.getPassword()).isEqualTo(test.getPassword());
        assertThat(result.getEmail()).isEqualTo(test.getEmail());
    }

    @Test
    @DisplayName("회원 정보 삭제")
    void testDeleteAccount() {
        AccountDeleteResponse response = new AccountDeleteResponse("OK");
        given(accountAdaptor.deleteAccount(anyLong())).willReturn(response);
        AccountDeleteResponse result = accountService.deleteAccount(1L);

        assertThat(result.getResult()).isEqualTo("OK");
    }

    @Test
    @DisplayName("회원 로그인 확인")
    void testMatches() {
        Account test = new Account(1L, "nhn", "nhn", "1234", "nhn@email.com", "010-1234-1234", "1");
        given(accountAdaptor.matches(any())).willReturn(test);
        Account result = accountService.matches(new AccountLoginRequestDto("nhn", "1234"));

        assertThat(result.getUserId()).isEqualTo(test.getUserId());
        assertThat(result.getPassword()).isEqualTo(test.getPassword());
        assertThat(result.getEmail()).isEqualTo(test.getEmail());
    }

    @Test
    @DisplayName("회원 존재 여부 확인 - 성공")
    void isExistAccount_Success() {
        given(accountAdaptor.isAccountExist(any())).willReturn(new CheckAccount(true));

        UserDto user = new UserDto("seungjo");

        CheckAccount result = accountService.isAccountExist(user);

        assertThat(result).isEqualTo(new CheckAccount(true));
    }

    @Test
    @DisplayName("회원 존재 여부 확인 - 실패")
    void isExistAccount_Fail() {
        given(accountAdaptor.isAccountExist(any())).willReturn(new CheckAccount(false));

        UserDto user = new UserDto("seungjo");

        CheckAccount result = accountService.isAccountExist(user);

        assertThat(result).isEqualTo(new CheckAccount(false));
    }
}