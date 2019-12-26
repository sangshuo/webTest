package com.itdr.commom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL )
public class ServerResponse<T> {

    private Integer status;  //状态
    private T  data;
    private String msg;



    //创建成功时，包含数据
    private ServerResponse(T data){
        this.status=200;
        this.data=data;
    }

    //创建成功时，包含状态码和数据
    private ServerResponse(Integer status,T data){
        this.status=status;
        this.data=data;
    }
    //创建成功时，包含状态码/数据/信息
    private ServerResponse(Integer status,T data,String msg){
        this.status=status;
        this.data=data;
        this.msg=msg;
    }

    //创建失败时，包含状态码/信息
    private ServerResponse( Integer status,String msg){
        this.status=status;
        this.msg=msg;
    }



    //创建失败时，包含信息
    private ServerResponse(String msg){
        this.status=100;
        this.msg=msg;
    }


    //成功时只要返回状态码和成功的数据
    public static <T>ServerResponse successRS(Integer status,T data,String msg){
        return  new ServerResponse<T>(status,data,msg);
    }

    //成功时只要返回状态码和成功的数据
    public static <T>ServerResponse successRS(Integer status,String msg){
        return  new ServerResponse<T>(status,msg);
    }

    //成功时只要返回状态码和成功的数据
    public static <T>ServerResponse successRS(Integer status,T data){
        return  new ServerResponse<T>(status,data);
    }


    //成功时只要返回状态码和成功的数据
    public static <T>ServerResponse successRS(T data){

        return  new ServerResponse<T>(data);
    }


    //失败时只要返回状态码和成功的数据
    public static <T>ServerResponse defeatedRS(Integer status,String msg){

        return  new ServerResponse<T>(status,msg);
    }

    //失败时只要返回状态码和成功的数据
    public static <T>ServerResponse defeatedRS(String msg){

        return  new ServerResponse<T>(msg);
    }

    //失败时只要返回数据
    public static <T>ServerResponse defeatedRS(T data){

        return  new ServerResponse<T>(data);
    }

    //失败时只要返回数据
    public static <T>ServerResponse defeatedRS(Integer status,T data){

        return  new ServerResponse<T>(status,data);
    }

    //判断是否成功的方法
    @JsonIgnore
 public boolean isSuccess(){
        return this.status==Consts.SUCCESS;
 }


    @Override
    public String toString() {
        return "ResponseCode{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

}
