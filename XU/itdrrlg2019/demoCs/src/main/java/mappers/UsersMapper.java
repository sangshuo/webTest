package mappers;


import org.apache.ibatis.annotations.Param;
import pojo.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);
    //更新登录个人信息
    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
    //根据用户名和密码查询一个用户
    Users selectByusernameAndpassword(@Param("username") String username, @Param("password") String password);
    //查询用户名和邮箱是否存在
    int  selectByUserEmail(@Param("str") String str, @Param("type") String type);
   //根据邮箱查询是否存在
    int selectByEmain(@Param("email") String email, @Param("id") Integer id);
   //根据用户名查找密码问题
    String selectByusername(@Param("username") String username);

    int selectByUserAndQuestionAndAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);
    //根据用户名更新密码
    int upByUsernamrAndpassword(@Param("username") String username, @Param("passwordNew") String passwordNew);
   // 根据ID查询密码是否存在
    int selectByIdAndpassword(@Param("id") Integer id, @Param("passwordOld") String passwordOld);
    //获取登录用户权限 用户
    int selectRole(@Param("Id") Integer Id);
    //获取登录用户权限 管理员
    int selectRoles(@Param("Id") Integer Id);
}