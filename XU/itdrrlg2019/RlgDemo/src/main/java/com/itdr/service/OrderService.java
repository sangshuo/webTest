package com.itdr.service;


import com.itdr.commom.ServerResponse;
import com.itdr.pojo.Orders;
import org.apache.ibatis.annotations.Param;



public interface OrderService {
    //根据订单号查询所有订单对象集合
    ServerResponse creates( Integer uid,Integer shippingId);
}
