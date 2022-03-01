package com.wanho.searchnearbyplaces.place;

import com.wanho.searchnearbyplaces.user.User;
import com.wanho.searchnearbyplaces.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@Transactional
class PlaceRestControllerTest {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;
    private User user;
    private Place place;


    @BeforeEach
    public void setUp(@Autowired WebApplicationContext applicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .alwaysDo(print())
                .build();

    }

    @Test
    void getPlace() {
    }

    @Test
    void savePlace() {
    }

    @Test
    void editPlace() {
    }

    @Test
    void deletePlace() {
    }

    @Test
    @WithUserDetails(
            value = "user",
            userDetailsServiceBeanName = "userDetailsService",
            setupBefore = TestExecutionEvent.TEST_EXECUTION
    )
    void getPlaces() throws Exception {
        // given
        user = User.builder()
                .email("wanho@naver.com")
                .password("abcd")
                .build();

        user = userRepository.save(user);

        place = Place.builder()
                .name("엽떡")
                .user(user)
                .tel("02-123-4567")
                .address("Seoul")
                .build();

        place = placeRepository.save(place);

        // when & then





    }
}