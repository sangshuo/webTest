package com.itdr.mappers;

import com.itdr.pojo.Payinfo;
import org.apache.ibatis.annotations.Param;

public interface PayinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Payinfo record);

    int insertSelective(Payinfo record);

    Payinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Payinfo record);

    int updateByPrimaryKey(Payinfo record);
    //判断此订单号是否存在
    int selectByOrder_no(@Param("orderNo")Long orderNo,@Param("id") Integer id);
    //查询订单支付状态
    Payinfo selectPlatformStatusByOrderNo(@Param("orderNo")Long orderNo);


}