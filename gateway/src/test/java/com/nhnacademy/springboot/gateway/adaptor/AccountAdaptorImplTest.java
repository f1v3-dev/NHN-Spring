package com.nhnacademy.springboot.gateway.adaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import com.nhnacademy.springboot.gateway.config.AccountAdaptorProperties;
import com.nhnacademy.springboot.gateway.domain.account.Account;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class AccountAdaptorImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Autowired
    AccountAdaptorProperties accountAdaptorProperties;

    @InjectMocks
    AccountAdaptorImpl accountAdaptor;

    @Test
    void testGetAccounts() {

        List<Account> accounts =
                List.of(new Account(1L, "seungjo", "seungjo", "1234",
                        "seungjo@nhn.com", "010-2717-8134", "1"),
                        new Account(2L, "tester", "test", "1234",
                                "test@nhn.com", "010-1111-1111", "1"));

        ResponseEntity<List<Account>> responseEntity = new ResponseEntity<>(accounts, HttpStatus.OK);

        given(restTemplate.exchange(
                accountAdaptorProperties.getAddress() + "/account/list",
                HttpMethod.GET,
                any(HttpEntity.class),
                any(ParameterizedTypeReference.class)
        )).willReturn(responseEntity);

        List<Account> actual = accountAdaptor.getAccounts();

        assertThat(actual).isEqualTo(responseEntity.getBody());

    }

}