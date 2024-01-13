package com.nhnacademy.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.nhnacademy.domain.Role;
import com.nhnacademy.domain.User;
import com.nhnacademy.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserRepositoryTest {

    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl();
        userRepository.register("tester", "1234", "테스터", Role.CUSTOMER);
    }

    @Test
    @DisplayName("사용자 조회(로그인) - 실패")
    void matches_False() {
        boolean matches = userRepository.matches("fail", "fail");

        assertThat(matches).isFalse();
    }

    @Test
    @DisplayName("사용자 조회(로그인) - 성공")
    void matches_True() {

        boolean matches = userRepository.matches("tester", "1234");

        assertThat(matches).isTrue();
    }

    @Test
    @DisplayName("사용자 등록 - 이미 존재하는 회원")
    void register_Throw_UserAlreadyExistsException() {
        Throwable throwable = catchThrowable(() ->
                userRepository.register("tester", "1234", "동일한 테스터", Role.CUSTOMER));

        assertThat(throwable).isInstanceOf(UserAlreadyExistsException.class);
    }

    @Test
    @DisplayName("사용자 등록 - 성공")
    void register_Success() {
        userRepository.register("tester2", "0000", "테스터2", Role.CUSTOMER);

        User user = userRepository.getUser("tester2");

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo("tester2");
        assertThat(user.getPassword()).isEqualTo("0000");
        assertThat(user.getName()).isEqualTo("테스터2");
        assertThat(user.getRole()).isEqualTo(Role.CUSTOMER);
    }

    @Test
    @DisplayName("사용자 조회 - 실패")
    void getUser_Return_Null() {
        User user = userRepository.getUser("notExist");

        assertThat(user).isNull();
    }

    @Test
    @DisplayName("사용자 조회 - 성공")
    void getUser_Return_User() {
        User user = userRepository.getUser("tester");

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo("tester");
    }

    @Test
    @DisplayName("사용자 존재여부 확인 - 실패")
    void exists_False() {
        boolean notExist = userRepository.exists("notExist");
        assertThat(notExist).isFalse();
    }

    @Test
    @DisplayName("사용자 존재여부 확인 - 성공")
    void exists_True() {
        boolean exist = userRepository.exists("tester");
        assertThat(exist).isTrue();
    }

}