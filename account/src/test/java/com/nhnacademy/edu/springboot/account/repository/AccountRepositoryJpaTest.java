package com.nhnacademy.edu.springboot.account.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.edu.springboot.account.domain.Account;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class AccountRepositoryJpaTest {


    @Autowired
    TestEntityManager entityManager;

    @Autowired
    AccountRepository accountRepository;

    @Test
    void testFindById() {
        Account account = new Account(1L, "8134-1", 123456);
        entityManager.persist(account);

        Account byId = accountRepository.findById(1L).orElse(null);
        assertThat(byId).isEqualTo(account);
    }

    @Test
    void testFindByName() {
        Account firstAccount = new Account(1L, "8134-1", 12345);
        Account secondAccount = new Account(2L, "8134-2", 66565);

        entityManager.persist(firstAccount);
        entityManager.persist(secondAccount);

        List<Account> accountList = accountRepository.findAll();
        assertThat(accountList).hasSize(2);
    }

    @Test
    void testSave() {

        Account account = new Account(1L, "8134-1", 12345);
        Account savedAccount = accountRepository.save(account);
        assertThat(savedAccount).isEqualTo(account);
    }

    @Test
    void testDelete() {

        accountRepository.deleteById(1L);
    }

}