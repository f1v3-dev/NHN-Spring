package com.nhnacademy.edu.springboot.account.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.edu.springboot.account.domain.Account;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DefaultAccountServiceTest {

    @Autowired
    AccountService accountService;


    @Test
    @Disabled
    void testGetAccount() {
        List<Account> actual = accountService.getAccounts();

        assertThat(actual).hasSize(2);

        Account account = actual.get(0);
        assertThat(account.getBalance()).isEqualTo(1_000_000);
        assertThat(account.getNumber()).isEqualTo("1234-5678");
    }

}