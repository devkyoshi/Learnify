package com.learnify.backend.common;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.learnify.backend.common.constants.ErrorCodes;
import com.learnify.backend.common.constants.SuccessCodes;

@JsonSerialize
public record BaseResponse<T>(
        T data,
        Boolean success,
        String errorCode,
        String message
) {

    //Used when we want to send success response
    public BaseResponse(T data){
        this(data, true, null, null);
    }

    //Used when we want to send error response
    public BaseResponse(String errorCode, String message){
        this(null, false, errorCode, message);
    }

    //Used when we want to send error response with data
    public BaseResponse(String errorCode, String message, T data){
        this(data, false, errorCode, message);
    }

    //Used when we want to send success response with message
    public BaseResponse(T data, String message){
        this(data, true, null, message);
    }

    //Used when we want to send success response without message
    public BaseResponse(Boolean success){
        this(null, success, null, null);
    }

    public BaseResponse(Boolean success, T data , String message){
        this(data, success, null, message);
    }

    //Used when we want to send error response with error code
    public BaseResponse(ErrorCodes errorCode){
        this(null, false, errorCode.getCode(), errorCode.getMessage());
    }

    //Used when we want to send success response with success code
    public BaseResponse(SuccessCodes successCode){
        this(null, true, successCode.getCode(), successCode.getMessage());
    }

    public BaseResponse(T data, Boolean success, String errorCode, String message){
        this.data = data;
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
    }

}
