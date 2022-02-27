package com.wanho.searchnearbyplaces.user;

public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }

    public UserException() {
        super("유저를 찾을 수 없습니다.");
    }
}
