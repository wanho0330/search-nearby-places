package com.wanho.searchnearbyplaces.place;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/*
* Service는 Application을 띄울 필요가 없으므로 MockitoExtention 사용
 */
@ExtendWith(MockitoExtension.class)
class PlaceServiceTest {

    @Mock
    private PlaceRepository placeRepository;

    @InjectMocks
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
    void findById_when사용() {
        // given
        Optional<Place> place = Optional.of(defaultPlace);

        // when
        when(placeRepository.findById(anyLong())).thenReturn(place);

        // then
        assertEquals(placeService.findById(1L).getName(), "엽기 떡볶이");



    }


    @Test
    void findById_given사용() {
        // given
        given(placeRepository.findById(anyLong()))
                .willReturn(Optional.of(defaultPlace));

        // when
        PlaceDto.Response response = placeService.findById(1L);

        // then
        assertEquals("엽기 떡볶이", response.getName());
    }

    @Test
    void savePlace() {
        // given
        given(placeRepository.save(any()))
                .willReturn(defaultPlace);

        // when
        PlaceDto.Response response = placeService.savePlace(defaultRequest);

        // then
        assertEquals("엽기 떡볶이", response.getName());
    }

    @Test
    void editPlace() {
        // given
        given(placeRepository.save(any()))
                .willReturn(defaultPlace);
        placeService.savePlace(defaultRequest);


        // when
        PlaceDto.Response response = placeService.editPlace(defaultRequest, 1L);

        // then
        assertEquals("엽기 떡볶이", response.getName());
    }

    @Test
    void deletePlace() {
    }
}