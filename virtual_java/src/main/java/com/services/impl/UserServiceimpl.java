package com.services.impl;

import com.Utils.MD5Utils;
import com.common.ServiceResponse;

import com.common.TokenCache;
import com.mappers.UsersMapper;
import com.pojo.Users;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return ServiceResponse.successRs(users);

    }
}
