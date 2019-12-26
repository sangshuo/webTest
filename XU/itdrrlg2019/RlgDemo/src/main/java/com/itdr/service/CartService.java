package com.itdr.service;

import com.itdr.commom.ServerResponse;
import com.itdr.pojo.Cart;

import java.util.List;

public interface CartService {
    //购物车添加商品
    ServerResponse<Cart> addone(Integer productId, Integer count,Integer uid);
    //购物车List列表
    ServerResponse<Cart> lists(Integer uid);
    //更新购物车某个产品数量
    ServerResponse<Cart> update(Integer productId, Integer count, Integer id);
    //移除购物车某个产品
    ServerResponse<Cart> deleteproduct(String productId, Integer id);
    //查询在购物车里的产品数量
    ServerResponse<Cart> getcartproductcount(Integer id);
    //全选
    ServerResponse<Cart> select_all(Integer check,Integer productId ,Integer uid);
}
