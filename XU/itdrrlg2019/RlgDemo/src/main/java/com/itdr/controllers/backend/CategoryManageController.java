package com.itdr.controllers.backend;

import com.itdr.commom.Consts;
import com.itdr.commom.ServerResponse;
import com.itdr.pojo.Category;
import com.itdr.pojo.Users;
import com.itdr.service.ICategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/manage/category/")
public class CategoryManageController {

       @Autowired
       ICategoryService iCategoryService;


       //获取当前分类id及递归子节点categoryId
       @RequestMapping("get_deep_category.do")
       public ServerResponse getdeepcategory(Integer categoryId){

       ServerResponse sr=iCategoryService.getdeepcategory(categoryId);
        return  sr;
       }

       //修改品类名字

       @RequestMapping("set_category_name.do")
       public ServerResponse setcategoryname(Integer categoryId,String categoryName){
              ServerResponse sr=iCategoryService.setcategoryname(categoryId,categoryName);
              return  sr;
       }

       //增加节点
       @RequestMapping("add_category.do")
       public ServerResponse addcategory(Integer parentId,String categoryName){
              ServerResponse sr=iCategoryService.addcategory(parentId,categoryName);
              return  sr;
       }

       //获取品类子节点(平级)
       @RequestMapping("get_category.do")
       public ServerResponse getcategory(Integer  categoryId, HttpSession session){
              System.out.println(categoryId);
              Users us = (Users) session.getAttribute(Consts.CURRENTUSER);
              if (us==null){
                     return  ServerResponse.defeatedRS(Consts.DISLOGIN,"用户未登录，请登录后尝试重复此操作！");
              }

              ServerResponse sr=iCategoryService.getcategory(categoryId);
              return  sr;
       }


}
