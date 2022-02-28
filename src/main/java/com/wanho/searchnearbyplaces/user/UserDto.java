package com.wanho.searchnearbyplaces.user;

import lombok.*;

import javax.validation.constraints.NotNull;


public class UserDto {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String email;
        private String password;

        static User requestToEntity(Request request) {
            return User.builder()
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .authority("ROLE_OWNER")
                    .build();
        }
    }


    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String email;

        static Response entityToResponse(User user) {
            return Response.builder()
                    .email(user.getEmail())
                    .build();
        }
    }

}
