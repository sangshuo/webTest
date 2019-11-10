package com.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Setter
@Getter
public class ServiceResponse<T> implements Serializable {
    private Integer status;
    private  T data;
    private  String mas;
    private  String token;
    //获取成功状态码和对象数据
    private ServiceResponse(T data){
        this.status = 200;
        this.data = data;
    }
    private ServiceResponse(T data,String token){
        this.status = 200;
        this.data = data;
        this.token = token;
    }
    //获取成功状态码和对象数据
    private ServiceResponse(Integer status ,T data){
        this.status = status;
        this.data = data;
    }
    //获取成功状态码数据状态消息和对象数据
    private ServiceResponse(Integer status ,T data,String mas){
        this.status = status;
        this.data = data;
        this.mas = mas;
    }

    //获取失败状态码数据状态消息
    private ServiceResponse(Integer status ,String mas){
        this.status = status;
        this.mas = mas;
    }
    //获取失败数据状态消息
    private ServiceResponse(String mas){
        this.status = 100;
        this.mas = mas;
    }

    //成功的时候只要返回状态码和成功获取的数据
    public static <T>ServiceResponse successRs(Integer status,T data){
        return new ServiceResponse(status,data);
    }
    //成功的时候只要返回成功获取的数据
    public static <T>ServiceResponse successRs(T data){
        return new ServiceResponse(data);
    }
    public static <T>ServiceResponse successRs(T data,String token){
        return new ServiceResponse(data,token);
    }

    //成功的时候只要返回成功状态码数据状态消息和对象数据
    public static <T>ServiceResponse successRs(Integer status ,T data,String mas){
        return new ServiceResponse(status,data,mas);
    }

    //失败的时候只要返回成功状态码数据状态消息
    public static <T>ServiceResponse defeadetRs(Integer status ,String mas){
        return new ServiceResponse(status,mas);
    }
    //失败的时候只要返回成功状态码数据状态消息
    public static <T>ServiceResponse defeadetRs(String mas){
        return new ServiceResponse(mas);
    }
    @JsonIgnore
    public boolean issuccerd(){
        return status == 200;
    }
}
