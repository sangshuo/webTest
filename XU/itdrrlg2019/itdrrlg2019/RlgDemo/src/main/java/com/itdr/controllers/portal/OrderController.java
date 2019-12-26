package com.itdr.controllers.portal;


import com.itdr.commom.Consts;
import com.itdr.commom.ServerResponse;
import com.itdr.pojo.Users;
import com.itdr.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/order/")
public class OrderController {

    @Autowired
    OrderService orderService;


    //创建订单
    @RequestMapping("create.do")
    public ServerResponse  creates(Integer shippingId, HttpSession session){
        Users u= (Users) session.getAttribute(Consts.CURRENTUSER);
        if (u==null){
            return ServerResponse.defeatedRS(Consts.UsersEnum.NO_PRIVILEGE.getCode(),
                    Consts.UsersEnum.NO_PRIVILEGE.getDesc());
        }else {
           ServerResponse sr=orderService.creates(u.getId(),shippingId);
           return  sr;
        }
  

    }
}
