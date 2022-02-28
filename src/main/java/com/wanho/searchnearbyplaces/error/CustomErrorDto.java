package com.wanho.searchnearbyplaces.error;


import com.wanho.searchnearbyplaces.constant.ErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class CustomErrorDto {


    private final Boolean success;
    private final Integer errorCode;
    private final String message;

    public static CustomErrorDto set(Boolean success, Integer errorCode, String message) {
        return new CustomErrorDto(success, errorCode, message);
    }

    public static CustomErrorDto set(Boolean success, ErrorCode errorCode) {
        return new CustomErrorDto(success, errorCode.getCode(), errorCode.getMessage());
    }

    public static CustomErrorDto set(Boolean success, ErrorCode errorCode, Exception e) {
        return new CustomErrorDto(success, errorCode.getCode(), errorCode.getMessage(e));
    }

    public static CustomErrorDto set(Boolean success, ErrorCode errorCode, String message) {
        return new CustomErrorDto(success, errorCode.getCode(), errorCode.getMessage(message));
    }
}


