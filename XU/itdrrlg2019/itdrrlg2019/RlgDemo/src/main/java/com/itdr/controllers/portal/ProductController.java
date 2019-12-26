package com.itdr.controllers.portal;


import com.itdr.commom.ServerResponse;
import com.itdr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@ResponseBody
    @Controller
    @RequestMapping("/product/")
    public class ProductController {

    @Autowired
    ProductService productService;

    //获取产品分类
    @RequestMapping("topcategory.do")
    public ServerResponse topcategory(Integer sid){

        ServerResponse rs=productService.topcategory(sid);
        return  rs;
    }

    //一个产品detail
    @RequestMapping("detail.do")
    public ServerResponse details(Integer productId,
                                  @RequestParam(value = "is_new",required = false,defaultValue = "0") Integer isnew,
                                  @RequestParam(value = "is_hot",required = false,defaultValue = "0")Integer ishot,
                                  @RequestParam(value = "is_banner",required = false,defaultValue = "0")Integer isbanner){

        ServerResponse rs=productService.details(productId,isnew,ishot,isbanner);
        return  rs;
    }
    //产品detail
    @RequestMapping("detailsNewOrHotOrBanner.do")
    public ServerResponse detailsNewOrHotOrBanner(
                                  @RequestParam(value = "is_new",required = false,defaultValue = "0") Integer isnew,
                                  @RequestParam(value = "is_hot",required = false,defaultValue = "0")Integer ishot,
                                  @RequestParam(value = "is_banner",required = false,defaultValue = "0")Integer isbanner){

        ServerResponse rs=productService.detailsNewOrHotOrBanner(isnew,ishot,isbanner);
        return  rs;
    }




    //产品搜索及动态排序List+
    @RequestMapping("list.do")
    public ServerResponse list(Integer productId,String keyword,Integer pageNum,Integer pageSize,String orderBy){

        ServerResponse rs=productService.list( productId, keyword, pageNum, pageSize, orderBy);
        return  rs;
    }
}
