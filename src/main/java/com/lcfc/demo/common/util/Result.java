package com.lcfc.demo.common.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code = 0;
    private String msg = "success";
    private T data;

    public Result(int code, String msg, T data) {
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // 正确信息
    public Result<T> ok(T data){
        this.data = data;
        return this;
    }
    

    // 错误信息
    public Result error(int code,String msg) {
        return new Result(code, msg);
    }

}
