package com.itdr.service;

import com.itdr.commom.ServerResponse;
import com.itdr.pojo.Users;

public interface PayInfoService {
    //查询订单支付状态
    ServerResponse queryorderpaystatus(Long orderNo, Users u);
}
