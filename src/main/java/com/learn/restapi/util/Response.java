package com.learn.restapi.util;

import lombok.Data;

@Data
public class Response <T> {
    private String message;
    private T data;

}
