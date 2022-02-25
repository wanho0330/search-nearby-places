package com.wanho.searchnearbyplaces.place;


import lombok.*;


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

    }

}
