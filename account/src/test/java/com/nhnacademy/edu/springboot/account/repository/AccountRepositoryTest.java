package com.nhnacademy.edu.springboot.account.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.edu.springboot.account.domain.Account;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountRepositoryTest {


    @Autowired
    AccountRepository accountRepository;

    @Test
    void testAccountRepository() {
        // given
        Account account = new Account(1L, "12345", 1_000_000);
        accountRepository.save(account);

        // when
        Optional<Account> actual = accountRepository.findById(1L);

        // then
        assertThat(actual).isPresent();
        assertThat(actual.get()).isEqualTo(account);
    }

}