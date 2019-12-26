package Service;


import Utils.MD5Utils;
import common.Consts;
import common.ServerResponse;
import common.TokenCache;
import mappers.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Users;

import java.util.UUID;

@Service("userservice")
public class UserServiceIml implements Userservice {

    @Autowired
    UsersMapper usersMapper;
//用户登录
    @Override
    public ServerResponse<Users> login(String username, String password) {

      if(username==null||username.equals("")){
          return ServerResponse.defeatedRS("用户名不能为空！");
      }
      if(password==null||password.equals("")){
          return  ServerResponse.defeatedRS("密码不能为空！");
      }
      //根据用户名查找是否存在
       int i= usersMapper.selectByUserEmail(username,"username");

      if(i<=0){
          return  ServerResponse.defeatedRS("用户不存在！");
      }

      //MD5加密
        String MD5password=MD5Utils.getMD5Code(password);



      //根据用户名密码查询用户是否存在

      Users u=  usersMapper.selectByusernameAndpassword(username,MD5password);
        System.out.println(u);
     if(u==null){
        return ServerResponse.defeatedRS("用户账号或密码错误！");
     }

     //封装数据返回
        ServerResponse rs = ServerResponse.successRS(200,u,"用户登录成功");
       return  rs;
    }


//用户注册
    @Override
    public ServerResponse register(Users u) {
        System.out.println("============================================");
        System.out.println(u.getQuestion());

        if(u.getUsername()==null||u.getUsername().equals("")){
            ServerResponse.defeatedRS("账户不能为空");
        }
        if(u.getPassword()==null||u.getPassword().equals("")){
            ServerResponse.defeatedRS("密码不能为空");
        }

    //MD5加密
        u.setPassword(MD5Utils.getMD5Code(u.getPassword()));
   //检查注册用户是否存在
        int i2= usersMapper.selectByUserEmail(u.getUsername(),"username");

        if(i2>0){
            return  ServerResponse.defeatedRS("要注册的用户已存在！");
        }
        int i = usersMapper.insert(u);
        if(i<=0){
            ServerResponse.defeatedRS("注册失败！");
        }


       return  ServerResponse.successRS(Consts.SUCCESS,"用户注册成功！");
    }



   //检查邮箱用户名是否存在
    @Override
    public ServerResponse checkUserName(String str, String type) {
        if(str==null||str.equals("")){
            return ServerResponse.defeatedRS("参数不能为空！");
        }
        if(type==null||type.equals("")){
            return  ServerResponse.defeatedRS("参数类型不能为空！");
        }

     int i=  usersMapper.selectByUserEmail(str,type);
        if (i>0&&type.equals("username")){
            return ServerResponse.defeatedRS("用户名已存在");
        }

        if (i>0&&type.equals("email")){
            return ServerResponse.defeatedRS("邮箱已存在");
        }
        return  ServerResponse.successRS(Consts.SUCCESS,null,"校验成功！");
    }
    //获取当前用户详细信息
    @Override
    public ServerResponse getUserinforamtion(Users u) {
       Users sr= usersMapper.selectByPrimaryKey(u.getId());
        if (sr == null) {
        return  ServerResponse.defeatedRS("用户名不存在！");
        }
        sr.setPassword("");
       return  ServerResponse.successRS(sr);

    }
    //登录更新用户个人信息
    @Override
    public ServerResponse updateUsers(Users u) {
        int i = usersMapper.updateByPrimaryKeySelective(u);

        if(i<=0){
           return ServerResponse.successRS("更新用户信息失败！");
        }
        return ServerResponse.successRS("更新用户信息成功！");
    }

//忘记密码
    @Override
    public ServerResponse forgetpassword(String username) {

        if (username == null || "".equals(username)) {
          return  ServerResponse.defeatedRS("参数不能为空");
        }
        int i=usersMapper.selectByUserEmail(username,"username");
        if(i<=0){
            return  ServerResponse.defeatedRS("用户名不存在");
        }

        String question=usersMapper.selectByusername(username);
        if (question==null||"".equals(question)){
            return  ServerResponse.defeatedRS("用户未设置忘记密码问题！");
        }
        return ServerResponse.successRS(question);
    }
    //提交问题答案
    @Override
    public ServerResponse forgetcheckanswer(String username, String question, String answer) {
        if (username==null||username.equals("")){
            return  ServerResponse.defeatedRS("用户名不能为空！");
        }
        if (question==null||question.equals("")){
            return  ServerResponse.defeatedRS("问题不能为空！");
        }
        if (answer==null||answer.equals("")){
            return  ServerResponse.defeatedRS("答案不能为空！");
        }

        //        //判断用户存在
       int c=usersMapper.selectByUserEmail(username,"username");
        if(c<=0){
            return  ServerResponse.defeatedRS("用户不存在");
        }

         int i=usersMapper.selectByUserAndQuestionAndAnswer( username,  question,  answer);

        if (i<=0){
            return  ServerResponse.defeatedRS("问题答案错误");
        }
        //产生随机字符令牌
        String token=UUID.randomUUID().toString();
        //将令牌放入缓存
        TokenCache.set("token"+username,token);

        return  ServerResponse.successRS(token);
    }
    //忘记密码的重设密码
    @Override
    public ServerResponse forgetresetpassword(String username, String passwordNew, String forgetToken) {
        if (username==null||username.equals("")){
            return  ServerResponse.defeatedRS("用户名不能为空！");
        }
        if (passwordNew==null||passwordNew.equals("")){
            return  ServerResponse.defeatedRS("问题不能为空！");
        }
        if (forgetToken==null||forgetToken.equals("")){
            return  ServerResponse.defeatedRS("非法令牌!");
        }

        //判断缓存token
        String token=TokenCache.get("token"+username);
        if (token==null||token.equals("")){
            return  ServerResponse.defeatedRS("token过期！");
        }
        if (!token.equals(forgetToken)){
            return  ServerResponse.defeatedRS("token非法！");
        }


        //MD5加密
        String MD5passwordNew=MD5Utils.getMD5Code(passwordNew);
        int i=usersMapper.upByUsernamrAndpassword( username, MD5passwordNew);

        if(i<=0){
            return  ServerResponse.defeatedRS("密码修改失败");
        }
        return ServerResponse.successRS("修改密码成功");
    }


//登录中状态重置密码
    @Override
    public ServerResponse resetpassword(String passwordOld, String passwordNew,Users users) {
        if (passwordOld==null||passwordOld.equals("")){
            return  ServerResponse.defeatedRS("旧密码不能为空！");
        }
        if (passwordNew==null||passwordNew.equals("")){
            return  ServerResponse.defeatedRS("设置密码不能为空！");
        }
        //MD5加密
        String MD5passwordNew=MD5Utils.getMD5Code(passwordOld);
        int i=usersMapper.selectByIdAndpassword(users.getId(),MD5passwordNew);
        if (i<=0){
            return  ServerResponse.defeatedRS("旧密码输入错误");
        }
        //MD5加密
        String MD5passwordNewss=MD5Utils.getMD5Code(passwordNew);
       int i2= usersMapper.upByUsernamrAndpassword(users.getUsername(),MD5passwordNewss);

        if(i2<=0){
            return  ServerResponse.defeatedRS("密码修改失败");
        }
        return ServerResponse.successRS("修改密码成功");




    }
}
