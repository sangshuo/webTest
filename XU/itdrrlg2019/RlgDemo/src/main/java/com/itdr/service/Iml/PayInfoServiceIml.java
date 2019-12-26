package com.itdr.service.Iml;


import com.itdr.commom.Consts;
import com.itdr.commom.ServerResponse;
import com.itdr.mappers.PayinfoMapper;
import com.itdr.mappers.UsersMapper;
import com.itdr.pojo.Payinfo;
import com.itdr.pojo.Users;
import com.itdr.service.PayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class PayInfoServiceIml implements PayInfoService {

    @Autowired
    PayinfoMapper payinfoMapper;

    @Autowired
    UsersMapper usersMapper;

    //查询订单支付状态
    @Override
    public ServerResponse queryorderpaystatus(Long orderNo,Users u) {
        //判断此订单号是否存在
        int i=payinfoMapper.selectByOrder_no(orderNo,u.getId());
        if (i<=0){
            return ServerResponse.defeatedRS("该用户并没有该订单,查询无效");
        }
        //查询支付状态，若是已经支付，返回true
        Payinfo rs=payinfoMapper.selectPlatformStatusByOrderNo(orderNo);


        if (!rs.getPlatformStatus().equals("TRADE_SUCCESS")){
            return ServerResponse.defeatedRS(Consts.DEFEAT,false);
        }
        return ServerResponse.successRS(Consts.SUCCESS,true);
    }





}
