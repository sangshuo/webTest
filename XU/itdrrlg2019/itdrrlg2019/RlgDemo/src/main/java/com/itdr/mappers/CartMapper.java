package com.itdr.mappers;

import com.itdr.commom.ServerResponse;
import com.itdr.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface CartMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Cart record);

    //购物车添加商品
    int insert(Cart record);
   //判断购物车中是否有将要添加数据存在，依据用户Id和产品Id
     Cart selectByUidAndProductId(@Param("uid")Integer uid, @Param("productId")Integer productId);
    //更新数据
    int updateByPrimaryKeySelective(Cart record);
    //依据用户ID查询此用户所有购物车商品
    ArrayList<Cart> selectByUid(@Param("uid")Integer uid);
    //id查询购物车中商品是否被全选
    int selectcheckeds(@Param("uid")Integer uid);
    //移除购物车某个产品
    int delectByProductIds(@Param("sr")List<String> sr,@Param("uid")Integer uid);
    //选择
    int updateBySelect( @Param("check")Integer check, @Param("productId")Integer productId,@Param("uid")Integer uid);


}