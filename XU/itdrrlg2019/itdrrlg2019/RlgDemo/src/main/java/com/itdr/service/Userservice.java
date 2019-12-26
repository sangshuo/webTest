package com.itdr.service;

import com.itdr.commom.ServerResponse;
import com.itdr.pojo.Users;

import javax.servlet.http.HttpSession;

public interface Userservice {
    ServerResponse<Users> login(String username,String password);

    ServerResponse register(Users u);

    ServerResponse checkUserName(String str, String type);
    //获取当前用户详细信息
    ServerResponse getUserinforamtion(Users u);
    //登录更新用户个人信息
    ServerResponse updateUsers(Users u);
    //忘记密码
    ServerResponse forgetpassword(String username);
   //提交问题答案
    ServerResponse forgetcheckanswer(String username, String question, String answer);
    //忘记密码的重设密码
    ServerResponse forgetresetpassword(String username, String passwordNew, String forgetToken);
    //登录中状态重置密码
    ServerResponse resetpassword(String passwordOld, String passwordNew,Users users);
}
