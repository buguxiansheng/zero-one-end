package com.lcfc.demo.common.util;

import lombok.Data;

@Data
public class Result<T>{
    private int code;
    private String msg;
    private T data;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }
}
