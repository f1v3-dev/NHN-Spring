package com.nhnacademy.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.nhnacademy.domain.Role;
import com.nhnacademy.domain.User;
import com.nhnacademy.exception.UserNotFoundException;
import com.nhnacademy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    User user;

    @BeforeEach
    void setUp() {
        user = new User("tester", "1234", "테스터", Role.CUSTOMER);
    }

    @Test
    @DisplayName("Login 테스트")
    void doLogin_Success() {
        when(userRepository.matches(anyString(), anyString())).thenReturn(true);
        when(userRepository.getUser(anyString())).thenReturn(user);

        User loginUser = userService.doLogin("tester", "1234");

        assertThat(loginUser.getId()).isEqualTo("tester");
        assertThat(loginUser.getName()).isEqualTo("테스터");
        assertThat(loginUser.getRole()).isEqualByComparingTo(Role.CUSTOMER);
    }

    @Test
    @DisplayName("Login 실패 테스트")
    void doLogin_Fail() {
        when(userRepository.matches(anyString(), anyString())).thenReturn(false);

        Throwable throwable = catchThrowable(() ->
                userService.doLogin("tester", "1234"));

        assertThat(throwable).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    @DisplayName("회원 일치 테스트")
    void matches_True() {
        when(userRepository.matches(anyString(), anyString())).thenReturn(true);

        boolean retVal = userService.isMatch("tester", "1234");

        assertThat(retVal).isTrue();
    }

    @Test
    @DisplayName("회원 불일치 테스트")
    void matches_False() {
        when(userRepository.matches(anyString(), anyString())).thenReturn(false);

        boolean retVal = userService.isMatch("tester", "1234");

        assertThat(retVal).isFalse();
    }

    @Test
    @DisplayName("회원 조회")
    void getUser() {
        when(userRepository.getUser(anyString())).thenReturn(user);

        User findUser = userService.getUser("tester");

        assertThat(findUser.getId()).isEqualTo("tester");
        assertThat(findUser.getName()).isEqualTo("테스터");
        assertThat(findUser.getRole()).isEqualByComparingTo(Role.CUSTOMER);
    }
}