package com.wanho.searchnearbyplaces.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;



    @Test
    void signup() {
        //given
        User defaultUser = User.builder()
                .email("wanho@naver.com")
                .password("test")
                .authority("ROLE_OWNER")
                .build();

        // when & then
        //then(userService.signup(defaultUser.getEmail(), defaultUser.getPassword()).getEmail()).isEqualTo("wanho@naver.com");
    }

    @Test
    void findByEmail() {
        // given
        User defaultUser = User.builder()
                .email("wanho@naver.com")
                .password("test")
                .authority("ROLE_OWNER")
                .build();

        //userService.signup(defaultUser.getEmail(), defaultUser.getPassword());

        // when
        User user = userService.findByEmail("wanho@naver.com");

        // then
        then(user.getPassword()).isEqualTo(defaultUser.getPassword());
    }

    @Test
    void delete() {
        // given

    }
}