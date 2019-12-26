package com.itdr.mappers;

import com.itdr.pojo.Category;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    //获取当前分类id及递归子节点categoryId
    ArrayList<Category> selectByParentID(@Param("pId")Integer pId);
    //修改品类名字
     int updateByIDAndName(@Param("categoryId")Integer categoryId, @Param("categoryName")String categoryName);
     //根据ID查询是否存在分类
    int selectById(@Param("categoryId")Integer categoryId);
    //根据category_id查找是否存在
    int selectByID(@Param("sid")Integer sid);
    //增加品类
    int inserts(@Param("parentId")Integer parentId,@Param("name") String name);
    //获取品类子节点(平级)
    ArrayList<Category> selectBycategoryId(@Param("categoryId")Integer categoryId);
    //获取产品分类
    ArrayList<Category> selectAllById(@Param("sid")Integer sid);
}