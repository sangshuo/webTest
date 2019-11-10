package com.services.impl;

import com.Utils.MD5Utils;
import com.common.ServiceResponse;

import com.common.TokenCache;
import com.mappers.UsersMapper;
import com.pojo.Users;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    UsersMapper usersMapper;
//登录
    @Override
    public ServiceResponse login(String username, String password) {


        if (username == null || username.equals("")) {
            return ServiceResponse.defeadetRs("用户名不能为空");
        }
        if (password == null || password.equals("")) {
            return ServiceResponse.defeadetRs("密码不能为空");
        }

        int usernum = usersMapper.selectByUsernameOrEmail(username,"username");
        if (usernum <= 0){
            return ServiceResponse.defeadetRs("用户名不存在");
        }

        String md5Password = MD5Utils.getMD5Code(password);
        Users users = usersMapper.selectByUsernameAndApassword(username,md5Password);
if (users == null){
    return ServiceResponse.defeadetRs("用户不存在");



}
        //产生随机令牌
        String token = UUID.randomUUID().toString();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("token",token);

        return ServiceResponse.successRs(users,token);

    }
}
