package com.wanho.searchnearbyplaces.place;

import com.wanho.searchnearbyplaces.place.PlaceDto.Request;
import com.wanho.searchnearbyplaces.place.PlaceDto.Response;
import com.wanho.searchnearbyplaces.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Transactional
    public Response findById(Long id) {
        return entityToResponse(placeRepository.findById(id).orElseThrow());
    }

    @Transactional
    public Response savePlace(User user, Request request) {
        request.setUser(user);
        return entityToResponse(placeRepository.save(requestToEntity(request)));
    }

    @Transactional
    public Response editPlace(User user, Request request, Long id) {
        Place place = findPlaceByUserAndId(user, id);

        place.setName(request.getName());
        place.setAddress(request.getAddress());
        place.setTel(request.getTel());

        return entityToResponse(placeRepository.save(place));
    }

    @Transactional
    public Boolean deletePlace(User user, Long id) {
        Place place = findPlaceByUserAndId(user, id);
        if (place == null) {
            return false;
        }

        placeRepository.delete(place);
        return true;
    }

    @Transactional
    public List<Response> findPlacesByUser(User user) {


        List<Place> places = placeRepository.findByUserOrderByIdDesc(user);

        List<Response> responses = new ArrayList<>();

        for (Place place : places) {
            responses.add(entityToResponse(place));
        }

        return responses;
    }


    /*
     *   Place Entity를 얻기 위한 함수 ( id > Place )
     *   - Place를 리턴하여 수정 및 삭제가 가능하도록 함.
     *   - 필요 없는 정보는 감추기 위해 ResponseDto를 사용하기 때문에 필요함.
     */
    private Place findPlaceByUserAndId(User user, Long id) {
        return placeRepository.findByUserAndId(user, id);
    }


    /*
     *   Mapper 함수 ( PlaceDto.Request > Place )
     */
    private Place requestToEntity(Request request) {
        return Place.builder()
                .name(request.getName())
                .address(request.getAddress())
                .tel(request.getTel())
                .ownerPlaceNumber(request.getOwnerPlaceNumber())
                .user(request.getUser())
                .build();
    }

    /*
     *   Mapper 함수 ( Place > PlaceDto.Response )
     */
    private Response entityToResponse(Place place) {
        return Response.builder()
                .id(place.getId())
                .name(place.getName())
                .address(place.getAddress())
                .tel(place.getTel())
                .ownerPlaceNumber(place.getOwnerPlaceNumber())
                .build();
    }


}
