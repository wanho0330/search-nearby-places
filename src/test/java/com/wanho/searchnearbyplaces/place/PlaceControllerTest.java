package com.wanho.searchnearbyplaces.place;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private PlaceService placeService;


    private final Place defaultPlace = Place.builder()
            .id(1L)
            .name("엽기 떡볶이")
            .address("서울시 광진구 자양동")
            .tel("02-123-4567")
            .ownerPlaceNumber("123-456789")
            .build();

    private final PlaceDto.Request defaultRequest = PlaceDto.Request.builder()
            .name("신전 떡볶이")
            .address("서울시 광진구 자양동")
            .tel("02-123-4567")
            .ownerPlaceNumber("123-456789")
            .build();

    @Test
    void editPlace() throws Exception {
        mockMvc.perform(put("/place/")
                        .param("id", String.valueOf(1L)))
                .andDo(print());

    }

}