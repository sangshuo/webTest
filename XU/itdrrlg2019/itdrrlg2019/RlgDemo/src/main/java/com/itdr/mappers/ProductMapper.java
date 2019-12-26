package com.itdr.mappers;

import com.itdr.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    //判断产品id存在
    int selectAllDetailById(@Param("productId")Integer productId);
    //判断产品是否已经下架
    int selectStatusById(@Param("productId")Integer productId);
    //获取产品详情
    Product selectGetAllDetailById(@Param("productId")Integer productId, @Param("is_new")Integer is_new,
                                              @Param("is_hot")Integer is_hot,@Param("is_banner") Integer is_banner);
    //获取产品列表
    ArrayList<Product> selectListAll();
    //获取产品详情
    Product selectGetOneById(@Param("productId")Integer productId);
    //判断产品状态是否已经处于此状态
    int selectByIdAndStatus(@Param("productId")Integer productId, @Param("status")Integer status);
    //产品上下架
    int updateByStatus(@Param("productId")Integer productId,@Param("status") Integer status);
    //新增商品
    int insert(Product record);
    //更新商品
    int updateByPrimaryKey(@Param("record")Product record);
    //产品搜索及动态排序List
    ArrayList selectByIdOrName(@Param("productId")Integer productId, @Param("keyword")String keyword, @Param("col")String col, @Param("order")String order);
    //依照产品名模糊查询
    ArrayList<Product> selectDataByProductName(@Param("productNames")String productNames);
    //只输入ID模糊查询
    ArrayList<Product> selectDataByProductId(@Param("productIds") String productIds);
    //依照产品名/ID模糊查询
    ArrayList<Product> selectDataByProductNameProductId(@Param("productNames")String productNames, @Param("productIds")String productIds);
    //根据商品ID获取商品对象
    Product selectByProducrId(@Param("productId")Integer productId);
    //更新库存
    int updateByProductId(@Param("ProductId")Integer ProductId,@Param("stock")Integer stock);
    //Hot New Banner
    List<Product> selectNewOrHotOrBanner(@Param("isnew")Integer isnew,@Param("ishot") Integer ishot,@Param("isbanner") Integer isbanner);
}