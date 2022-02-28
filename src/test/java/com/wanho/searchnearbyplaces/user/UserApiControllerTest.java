package com.wanho.searchnearbyplaces.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserApiControllerTest {

    @Autowired
    private UserService userService;


    @Test
    void signUp_성공() {
        // given
        UserDto.Request request = UserDto.Request.builder()
                .email("wanho@naver.com")
                .password("{noop}1234")
                .build();

        // when
        UserDto.Response signup = userService.signup(request);

        // then
        then(signup.getEmail()).isEqualTo(request.getEmail());

    }

}