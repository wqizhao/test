package com.rainier.tool;

/**
 * Created by Bobby on 2018/5/29.
 */
public class Result<T> {
    private  String code;
    private  String message;
    private T data;

    public static final Result success(){
        return new Result("0","Success");
    }

    public static final Result error(){
        return new Result("-1","Error");
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(String code, String message, T data) {

        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {

    }

    public String  getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code == null? null : code.trim();
    }

    public String  getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message == null? null : message.trim();
    }

    public T getData() {
        return data;
    }
    public void setData( T data) {
        this.data = data;
    }
}
