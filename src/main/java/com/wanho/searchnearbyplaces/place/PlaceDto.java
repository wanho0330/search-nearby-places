package com.wanho.searchnearbyplaces.place;


import com.wanho.searchnearbyplaces.user.User;
import lombok.*;

import javax.validation.constraints.NotNull;


public class PlaceDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String name;
        private String address;
        private String tel;
        private String ownerPlaceNumber;
        private User user;

        /*
         *   Mapper 함수 ( PlaceDto.Request > Place )
         */
        static Place requestToEntity(Request request) {
            return Place.builder()
                    .name(request.getName())
                    .address(request.getAddress())
                    .tel(request.getTel())
                    .ownerPlaceNumber(request.getOwnerPlaceNumber())
                    .user(request.getUser())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String name;
        private String address;
        private String tel;
        private String ownerPlaceNumber;


        /*
         *   Mapper 함수 ( Place > PlaceDto.Response )
         */
        static Response entityToResponse(Place place) {
            return Response.builder()
                    .id(place.getId())
                    .name(place.getName())
                    .address(place.getAddress())
                    .tel(place.getTel())
                    .ownerPlaceNumber(place.getOwnerPlaceNumber())
                    .build();
        }
    }

}
