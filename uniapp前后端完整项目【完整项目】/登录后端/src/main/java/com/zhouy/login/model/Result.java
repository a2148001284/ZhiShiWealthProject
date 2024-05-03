package com.zhouy.login.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {

    private Object object;

    private String msg;

    private Integer code;
//
//    public Result(Object data,String msg,Integer code){
//        this.object=data;
//        this.msg=msg;
//        this.code=code;
//    }

    public static Result success(Object data, String msg){
        return new Result(data,msg,200);
    }

    public static Result fail(String msg, Integer code){
        return new Result(null,msg,code);
    }

    public static Result fail(Object data,Integer code){ return new Result(data,null,code);}
}
