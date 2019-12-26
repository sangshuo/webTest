package com.itdr.mappers;

import com.itdr.pojo.Orders_item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Orders_itemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Orders_item record);

    int insertSelective(Orders_item record);

    Orders_item selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orders_item record);

    int updateByPrimaryKey(Orders_item record);
    //批量插入订单详情
    int insertAll(@Param("oderItem") List<Orders_item> oderItem);
}