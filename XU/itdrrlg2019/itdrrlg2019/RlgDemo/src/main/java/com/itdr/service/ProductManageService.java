package com.itdr.service;


import com.itdr.commom.ServerResponse;
import com.itdr.pojo.Product;
import org.springframework.stereotype.Service;



public interface ProductManageService {
    //产品列表
    ServerResponse AllList(Integer pageNum, Integer pageSize);
    //产品详情
    ServerResponse detail(Integer productId);
    //获取用户登录权限 用户
    int Search(Integer Id);
    //获取用户登录权限 管理员
    int Searchs(Integer Id);
    //产品上下架
    ServerResponse setsalestatus(Integer productId, Integer status);
    //新增OR更新产品
    ServerResponse save(Product product);
    //产品搜索
    ServerResponse search(String productName, Integer productId,Integer pageNum,Integer pageSize);
}
