package com.nhnacdemy.springboot.openapi.adaptor;

import com.nhnacdemy.springboot.openapi.config.AccountAdaptorProperties;
import com.nhnacdemy.springboot.openapi.domain.Account;
import com.nhnacdemy.springboot.openapi.service.AccountAdaptor;
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
                restTemplate.exchange(accountAdaptorProperties.getAddress() + "/accounts",
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<List<Account>>() {
                        });

        if (!HttpStatus.OK.equals(exchange.getStatusCode())) {
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
                restTemplate.exchange(accountAdaptorProperties.getAddress() + "/accounts/" + id,
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<Account>() {
                        });

        if (!HttpStatus.OK.equals(exchange.getStatusCode())) {
            throw new RuntimeException();
        }

        return exchange.getBody();
    }

    @Override
    public void createAccount(Account account) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Account> requestEntity = new HttpEntity<>(account, httpHeaders);

        ResponseEntity<Void> exchange =
                restTemplate.exchange(accountAdaptorProperties.getAddress() + "/accounts",
                        HttpMethod.POST,
                        requestEntity,
                        new ParameterizedTypeReference<Void>() {
                        });

        if (!HttpStatus.CREATED.equals(exchange.getStatusCode())) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteAccount(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Account> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Void> exchange = restTemplate.exchange(accountAdaptorProperties.getAddress() + "/accounts/" + id,
                HttpMethod.DELETE,
                requestEntity,
                new ParameterizedTypeReference<Void>() {
                });

        if (!HttpStatus.OK.equals(exchange.getStatusCode())) {
            throw new RuntimeException();
        }
    }
}
