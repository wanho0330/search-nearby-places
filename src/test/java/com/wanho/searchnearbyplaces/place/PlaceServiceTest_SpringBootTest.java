package com.wanho.searchnearbyplaces.place;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PlaceServiceTest_SpringBootTest {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
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
    void editPlace() {
        // given
        Place place = placeRepository.save(defaultPlace);

        // when
        PlaceDto.Response response = placeService.editPlace(defaultRequest, 1L);

        // then
        then(response.getId()).isEqualTo(1L);
        then(response.getName()).isEqualTo("신전 떡볶이");
    }

    @Test
    void deletePlace() {
    }
}