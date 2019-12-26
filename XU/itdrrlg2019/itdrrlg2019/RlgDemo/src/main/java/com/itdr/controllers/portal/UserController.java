package com.itdr.controllers.portal;


import com.itdr.commom.ServerResponse;
import com.itdr.pojo.Users;
import com.itdr.commom.Consts;
import com.itdr.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user/")
@ResponseBody
public class UserController {

   @Autowired
   Userservice userservice;

    //用户登录
    @RequestMapping("login.do")
    public ServerResponse login(String username, String password, HttpSession session){

      ServerResponse sr= userservice.login(username,password);

       if(sr.isSuccess()){
           Users u= (Users) sr.getData();
           session.setAttribute(Consts.CURRENTUSER,u);

           Users u2=new Users();
           u2.setId(u.getId());
           u2.setUsername(u.getUsername());
           u2.setEmail(u.getEmail());
           u2.setPhone(u.getPhone());
//           u2.setPassword("");
           u2.setCreateTime(u.getCreateTime());
           u2.setUpdateTime(u.getUpdateTime());
           sr.setData(u2);
       }
        return  sr;
    }


    //用户注册
    @RequestMapping("register.do")
    public ServerResponse register(Users u){
        ServerResponse sr= userservice.register(u);
        return  sr;
    }

    //检查用户名是否有效
    @RequestMapping("check_valid.do")
    public ServerResponse checkUserName(String str,String type){
        ServerResponse sr= userservice.checkUserName( str, type);
        return  sr;
    }

    //获取用户登录信息
    @RequestMapping("get_user_info.do")
    public ServerResponse getUsers(HttpSession session){

     Users u= (Users) session.getAttribute(Consts.CURRENTUSER);

     if(u==null){
        return ServerResponse.defeatedRS(Consts.UsersEnum.NO_PRIVILEGE.getCode(),Consts.UsersEnum.NO_PRIVILEGE.getDesc() );
     }else{
         ServerResponse rs = ServerResponse.successRS(u);
         return rs;
     }
    }
    //登录更新用户个人信息
    @RequestMapping("update_information.do")
    public ServerResponse updateUsers(Users u,HttpSession session){

        Users users= (Users) session.getAttribute(Consts.CURRENTUSER);

        if(u==null){
            return ServerResponse.defeatedRS("更新失败！");
        }else{
            u.setId(users.getId());
            u.setUsername(users.getUsername());
            ServerResponse sr= userservice.updateUsers(u);
            session.setAttribute(Consts.CURRENTUSER,u);
            return sr;
        }
    }

    //退出登录
    @RequestMapping("logout.do")
    public ServerResponse Userslogout(HttpSession session){
         session.removeAttribute(Consts.CURRENTUSER);
         return  ServerResponse.successRS("退出登录成功！");
    }
    //获取当前用户详细信息
    @RequestMapping("get_inforamtion.do")
    public ServerResponse getUserinforamtion(HttpSession session){

        Users u= (Users) session.getAttribute(Consts.CURRENTUSER);

        if(u==null){
            return ServerResponse.defeatedRS(Consts.UsersEnum.NO_PRIVILEGE.getCode(),Consts.UsersEnum.NO_PRIVILEGE.getDesc() );
        }else{
            ServerResponse rs = ServerResponse.successRS(u);
            return rs;
        }

    }
    //忘记密码
    @RequestMapping("forget_get_question.do")
    public ServerResponse forgetpassword(String username){
    ServerResponse sr= userservice.forgetpassword(username);
    return  sr;
}
    //提交问题答案
    @RequestMapping("forget_check_answer.do")
    public ServerResponse forgetcheckanswer(String username,String question,String answer){

        return  userservice.forgetcheckanswer( username, question, answer);
    }
    //忘记密码的重设密码
    @RequestMapping("forget_reset_password.do")
    public ServerResponse forgetresetpassword(String username,String passwordNew,String forgetToken){
        return  userservice.forgetresetpassword( username, passwordNew, forgetToken);
    }

    //登录中状态重置密码
    @RequestMapping("reset_password.do")
    public ServerResponse resetpassword(String passwordOld,String passwordNew,HttpSession session){
       Users users= (Users) session.getAttribute(Consts.CURRENTUSER);
       if(users==null){
           return  ServerResponse.defeatedRS("用户未登录");
       }else {
           return  userservice.resetpassword(passwordOld, passwordNew,users);
       }
    }
}
