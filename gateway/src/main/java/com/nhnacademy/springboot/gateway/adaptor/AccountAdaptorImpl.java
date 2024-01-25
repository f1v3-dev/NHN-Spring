package com.nhnacademy.springboot.gateway.adaptor;

import com.nhnacademy.springboot.gateway.config.AccountAdaptorProperties;
import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.account.AccountLoginRequestDto;
import com.nhnacademy.springboot.gateway.domain.account.AccountRegisterDto;
import com.nhnacademy.springboot.gateway.domain.account.AccountRequestDto;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AccountAdaptorImpl implements AccountAdaptor {

    private final RestTemplate restTemplate;

    private final AccountAdaptorProperties accountAdaptorProperties;


    public AccountAdaptorImpl(RestTemplate restTemplate, AccountAdaptorProperties accountAdaptorProperties) {
        this.restTemplate = restTemplate;
        this.accountAdaptorProperties = accountAdaptorProperties;
    }

    @Override
    public List<Account> getAccounts() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<Account>> exchange =
                restTemplate.exchange(
                        accountAdaptorProperties.getAddress() + "/accounts",
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        });


        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    @Override
    public Account getAccount(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Account> exchange =
                restTemplate.exchange(
                        accountAdaptorProperties.getAddress() + "/accounts/{id}",
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        }, id);


        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    @Override
    public Account createAccount(AccountRegisterDto account) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<AccountRegisterDto> requestEntity = new HttpEntity<>(account, httpHeaders);

        ResponseEntity<Account> exchange = restTemplate.exchange(accountAdaptorProperties.getAddress() + "/accounts",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Account>() {
                });

        if (HttpStatus.CREATED != exchange.getStatusCode()) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    @Override
    public void deleteAccount(Long id) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Integer> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(accountAdaptorProperties.getAddress() + "/accounts/{id}",
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<Void>() {
                });

        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new RuntimeException();
        }
    }

    @Override
    public AccountRequestDto matches(AccountLoginRequestDto account) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<AccountLoginRequestDto> requestEntity = new HttpEntity<>(account, httpHeaders);

        ResponseEntity<AccountRequestDto> exchange =
                restTemplate.exchange(accountAdaptorProperties.getAddress() + "/accounts/login",
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<AccountRequestDto>() {
                        });

        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new RuntimeException();
        }


        return exchange.getBody();
    }
}
