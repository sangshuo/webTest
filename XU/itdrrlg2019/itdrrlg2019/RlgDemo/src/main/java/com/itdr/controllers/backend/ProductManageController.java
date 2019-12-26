package com.itdr.controllers.backend;


import com.itdr.commom.Consts;
import com.itdr.commom.ServerResponse;
import com.itdr.pojo.Product;
import com.itdr.pojo.Users;
import com.itdr.service.ProductManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
@ResponseBody
@RequestMapping("/manage/product/")
public class ProductManageController {

      @Autowired
      ProductManageService productManageService;

//产品列表
        @RequestMapping("list.do")
        public ServerResponse  lists(Integer pageNum, Integer pageSize, HttpSession session){
            Users re = (Users) session.getAttribute(Consts.CURRENTUSER);
            if(re==null){
                return  ServerResponse.defeatedRS(Consts.DISLOGIN,"用户未登录，请登录后再进行操作！");
            }
            ServerResponse sr=  productManageService.AllList(pageNum,pageSize);
          return  sr;
        }

//产品详情
      @RequestMapping("detail.do")
      public ServerResponse detail(Integer productId,HttpSession session){
          //获取登录状态
          Users re = (Users)session.getAttribute(Consts.CURRENTUSER);
          if (re==null){
              return  ServerResponse.defeatedRS(Consts.DISLOGIN,"用户未登录");
          }
          //查询以登录用户的权限
          int i=productManageService.Search(re.getId());
          if (i<=0){
              return ServerResponse.defeatedRS(Consts.ISNOTNULL,"用户无操作权限");
          }
          ServerResponse sr=  productManageService.detail(productId);
          return  sr;

      }
//产品上下架
    @RequestMapping("set_sale_status.do")
    public ServerResponse setsalestatus(Integer productId,Integer status,HttpSession session){
        //获取登录状态
        Users re = (Users)session.getAttribute(Consts.CURRENTUSER);
        if (re==null){
            return  ServerResponse.defeatedRS(Consts.DISLOGIN,"用户未登录");
        }
        //查询以登录用户的权限
        int i=productManageService.Searchs(re.getId());
        if (i<=0){
            return ServerResponse.defeatedRS(Consts.ISNOTNULL,"此操作需要管理员权限");
        }
        ServerResponse sr=  productManageService.setsalestatus(productId,status);
        return  sr;
    }

//    新增OR更新产品
    @RequestMapping("save.do")
    public ServerResponse save(Product product, HttpSession session){
        //获取登录状态
        Users re = (Users)session.getAttribute(Consts.CURRENTUSER);
        if (re==null){
            return  ServerResponse.defeatedRS(Consts.DISLOGIN,"用户未登录");
        }
        //查询以登录用户的权限
        int i=productManageService.Searchs(re.getId());
        if (i<=0){
            return ServerResponse.defeatedRS(Consts.ISNOTNULL,"此操作需要管理员权限");
        }

        //判断非空
        if (product.getCategory_id()==null){
            return  ServerResponse.defeatedRS(Consts.ISNULL,"用户分类ID不能为空");
        }

        if (product.getName()==null){
            return  ServerResponse.defeatedRS(Consts.ISNULL,"用户名不能为空！");
        }
        //判断是新增商品还是更新商品
        ServerResponse sr=  productManageService.save(product);
        return  sr;
    }

//产品搜索
    @RequestMapping("search.do")
    public ServerResponse search(String  productName,Integer productId,
                               @RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                               @RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize,
                               HttpSession session){
    //获取登录状态
    Users re = (Users)session.getAttribute(Consts.CURRENTUSER);
    if (re==null){
        return  ServerResponse.defeatedRS(Consts.DISLOGIN,"用户未登录");
    }

    ServerResponse sr=  productManageService.search(productName,productId,pageNum,pageSize);
    return  sr;
}


}
