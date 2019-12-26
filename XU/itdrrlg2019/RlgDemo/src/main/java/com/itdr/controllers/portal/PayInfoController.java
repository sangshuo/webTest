package com.itdr.controllers.portal;


import com.itdr.commom.Consts;
import com.itdr.commom.ServerResponse;
import com.itdr.pojo.Users;
import com.itdr.service.PayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/portal/order/")

public class PayInfoController {

    @Autowired
    PayInfoService payInfoService;

    //查询订单支付状态
    @RequestMapping("query_order_pay_status.do")
    public ServerResponse queryorderpaystatus(String orderNo , HttpSession session){
         long s=Long.valueOf(orderNo);
        //判断用户是否登录
        Users u=(Users)session.getAttribute(Consts.CURRENTUSER);
        if (u==null){
            return ServerResponse.defeatedRS("请登录后进行此操作");
        }
        //判断非空
        if (orderNo==null||orderNo.equals("")){
            return  ServerResponse.defeatedRS(Consts.ISNULL,"订单号不能为空");
        }

        ServerResponse sr=payInfoService.queryorderpaystatus(s,u);
        return  sr;

    }

}
