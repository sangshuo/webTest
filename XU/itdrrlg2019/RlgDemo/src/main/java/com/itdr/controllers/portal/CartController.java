package com.itdr.controllers.portal;


import com.itdr.commom.Consts;
import com.itdr.commom.ServerResponse;
import com.itdr.pojo.Cart;
import com.itdr.pojo.Users;
import com.itdr.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/portal/cart/")
public class CartController {

    @Autowired
    CartService cartService;

    //购物车添加商品
    @RequestMapping("add.do")
    public ServerResponse<Cart> addone(Integer productId, Integer count, HttpSession session){
       Users u= (Users) session.getAttribute(Consts.CURRENTUSER);
        if (u==null){
        return ServerResponse.defeatedRS(Consts.UsersEnum.NO_PRIVILEGE.getCode(),
                Consts.UsersEnum.NO_PRIVILEGE.getDesc());
        }else {
            return cartService.addone(productId,count,u.getId());
        }
    }



    //购物车List列表
    @RequestMapping("list.do")
    public ServerResponse<Cart> lists(HttpSession session){
        Users u= (Users) session.getAttribute(Consts.CURRENTUSER);
        if (u==null){
            return ServerResponse.defeatedRS(Consts.UsersEnum.NO_PRIVILEGE.getCode(),
                    Consts.UsersEnum.NO_PRIVILEGE.getDesc());
        }else {
            return cartService.lists(u.getId());
        }
    }


    //更新购物车某个产品数量
    @RequestMapping("update.do")
    public ServerResponse<Cart> update(Integer productId,Integer count, HttpSession session){
        Users u= (Users) session.getAttribute(Consts.CURRENTUSER);
        if (u==null){
            return ServerResponse.defeatedRS(Consts.UsersEnum.NO_PRIVILEGE.getCode(),
                    Consts.UsersEnum.NO_PRIVILEGE.getDesc());
        }else {
            return cartService.update(productId,count,u.getId());
        }
    }

    //移除购物车某个产品
    @RequestMapping("delete_product.do")
    public ServerResponse<Cart> deleteproduct(String productIds,HttpSession session){
        Users u= (Users) session.getAttribute(Consts.CURRENTUSER);
        if (u==null){
            return ServerResponse.defeatedRS(Consts.UsersEnum.NO_PRIVILEGE.getCode(),
                    Consts.UsersEnum.NO_PRIVILEGE.getDesc());
        }else {
            return cartService.deleteproduct(productIds,u.getId());
        }
    }


    //查询在购物车里的产品数量
    @RequestMapping("get_cart_product_count.do")
    public ServerResponse<Cart> get_cartproductcount(HttpSession session){
        Users u= (Users) session.getAttribute(Consts.CURRENTUSER);
        if (u==null){
            return ServerResponse.defeatedRS(Consts.UsersEnum.NO_PRIVILEGE.getCode(),
                    Consts.UsersEnum.NO_PRIVILEGE.getDesc());
        }else {
            return cartService.getcartproductcount(u.getId());
        }
    }
    //购物车全选
    @RequestMapping("select_all.do")
    public ServerResponse<Cart> select_all(Integer check,HttpSession session){
        Users u= (Users) session.getAttribute(Consts.CURRENTUSER);
        if (u==null){
            return ServerResponse.defeatedRS(Consts.UsersEnum.NO_PRIVILEGE.getCode(),
                    Consts.UsersEnum.NO_PRIVILEGE.getDesc());
        }else {
            return cartService.select_all(check,u.getId(),null);
        }
    }
 //取消全选
    @RequestMapping("un_select_all.do")
     public ServerResponse<Cart> un_select_all(Integer check,HttpSession session){
        Users u= (Users) session.getAttribute(Consts.CURRENTUSER);
        if (u==null){
            return ServerResponse.defeatedRS(Consts.UsersEnum.NO_PRIVILEGE.getCode(),
                    Consts.UsersEnum.NO_PRIVILEGE.getDesc());
        }else {
            return cartService.select_all(check,u.getId(),null);
        }
    }

    //单个商品选

    @RequestMapping("select.do")
    public ServerResponse<Cart> select(Integer check,HttpSession session,Integer productId){
        Users u= (Users) session.getAttribute(Consts.CURRENTUSER);
        if (u==null){
            return ServerResponse.defeatedRS(Consts.UsersEnum.NO_PRIVILEGE.getCode(),
                    Consts.UsersEnum.NO_PRIVILEGE.getDesc());
        }else {
            return cartService.select_all(check,u.getId(),productId);
        }
    }

    //单个商品取消全选
    @RequestMapping("un_select.do")
    public ServerResponse<Cart> un_select(Integer check,Integer productId,HttpSession session){
        Users u= (Users) session.getAttribute(Consts.CURRENTUSER);
        if (u==null){
            return ServerResponse.defeatedRS(Consts.UsersEnum.NO_PRIVILEGE.getCode(),
                    Consts.UsersEnum.NO_PRIVILEGE.getDesc());
        }else {
            return cartService.select_all(check,productId,u.getId());
        }
    }
}
