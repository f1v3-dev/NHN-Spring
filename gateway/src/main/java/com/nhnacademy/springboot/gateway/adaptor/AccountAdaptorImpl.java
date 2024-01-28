package com.nhnacademy.springboot.gateway.adaptor;

import com.nhnacademy.springboot.gateway.config.AccountAdaptorProperties;
import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.account.AccountDeleteResponse;
import com.nhnacademy.springboot.gateway.domain.account.AccountLoginRequestDto;
import com.nhnacademy.springboot.gateway.domain.account.AccountRegisterRequestDto;
import com.nhnacademy.springboot.gateway.domain.account.CheckAccount;
import com.nhnacademy.springboot.gateway.domain.task.member.UserDto;
import com.nhnacademy.springboot.gateway.exception.ConnectionServerException;
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
                        accountAdaptorProperties.getAddress() + "/account/list",
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        });

        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new ConnectionServerException("모든 회원 정보 조회 실패");
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
                        accountAdaptorProperties.getAddress() + "/account/{id}",
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        }, id);


        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new ConnectionServerException(id + "번 회원 정보 조회 실패");
        }

        return exchange.getBody();
    }

    @Override
    public Account createAccount(AccountRegisterRequestDto account) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<AccountRegisterRequestDto> requestEntity = new HttpEntity<>(account, httpHeaders);

        ResponseEntity<Account> exchange = restTemplate.exchange(accountAdaptorProperties.getAddress() + "/account",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Account>() {
                });

        if (HttpStatus.CREATED != exchange.getStatusCode()) {
            throw new ConnectionServerException(account.getUserId() + " 회원 가입 실패");
        }

        return exchange.getBody();
    }

    @Override
    public AccountDeleteResponse deleteAccount(Long accountId) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Integer> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<AccountDeleteResponse> exchange =
                restTemplate.exchange(accountAdaptorProperties.getAddress() + "/account/{accountId}",
                        HttpMethod.DELETE,
                        requestEntity,
                        new ParameterizedTypeReference<>() {
                        }, accountId);

        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new ConnectionServerException(accountId + "번 회원 탈퇴 실패");
        }

        return exchange.getBody();
    }

    @Override
    public Account matches(AccountLoginRequestDto account) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<AccountLoginRequestDto> requestEntity = new HttpEntity<>(account, httpHeaders);

        ResponseEntity<Account> exchange =
                restTemplate.exchange(accountAdaptorProperties.getAddress() + "/login",
                        HttpMethod.POST,
                        requestEntity,
                        new ParameterizedTypeReference<Account>() {
                        });

        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new ConnectionServerException(account.getUserId() + " 회원 로그인 실패");
        }


        return exchange.getBody();
    }

    @Override
    public CheckAccount isAccountExist(UserDto user) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<UserDto> requestEntity = new HttpEntity<>(user, httpHeaders);

        ResponseEntity<CheckAccount> exchange = restTemplate.exchange(
                accountAdaptorProperties.getAddress() + "/account/exist",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        if (HttpStatus.OK != exchange.getStatusCode()) {
            throw new ConnectionServerException(user.getUserId() + " 회원 정보 조회 실패");
        }

        return exchange.getBody();
    }
}
