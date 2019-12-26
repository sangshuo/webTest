package com.itdr.service.Iml;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itdr.commom.Consts;
import com.itdr.commom.ServerResponse;
import com.itdr.mappers.ProductMapper;
import com.itdr.mappers.UsersMapper;
import com.itdr.pojo.Product;
import com.itdr.service.ProductManageService;
import com.itdr.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.jsp.el.ScopedAttributeELResolver;
import java.util.ArrayList;
import java.util.HashSet;

@Service
public class ProductManageServiceIml implements ProductManageService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    UsersMapper usersMapper;

    //产品列表
    @Override
    public ServerResponse AllList(Integer pageNum, Integer pageSize) {
        if (pageSize == null || pageSize.equals("")) {
            pageSize =10;
        }
        if (pageNum == null || pageNum.equals("")) {
            pageNum =1;
        }

        ArrayList<Product> li=productMapper.selectListAll();
        if(li==null){
            return ServerResponse.defeatedRS(Consts.ISNULL,"数据非法！");
        }

        return  ServerResponse.successRS(Consts.SUCCESS,li);
    }

    //产品详情
    @Override
    public ServerResponse detail(Integer productId) {
        //判断非空
        if(productId==null||productId.equals("")){
            return  ServerResponse.defeatedRS(2,"产品ID为空！");
        }
        //判断产品id存在
        int i=productMapper.selectAllDetailById(productId);
        if (i<=0){
            return  ServerResponse.defeatedRS(1,"不存在此ID商品，请检查后重新输入！");
        }


        Product sr=productMapper.selectGetOneById(productId);
        if (sr==null){
          return  ServerResponse.defeatedRS(Consts.DEFEAT,"没有符合此ID商品！");
        }
        return ServerResponse.successRS(Consts.SUCCESS,sr);
    }
   //获取登录用户权限 用户
    @Override
    public int Search(Integer Id) {
       int t= usersMapper.selectRole(Id);
        return t;
    }

    //获取登录用户权限 管理员
    @Override
    public int Searchs(Integer Id) {
        int t= usersMapper.selectRoles(Id);
        return t;
    }

    //产品上下架
    @Override
    public ServerResponse setsalestatus(Integer productId, Integer status) {
        //判断非空
        if(productId==null||productId.equals("")){
            return  ServerResponse.defeatedRS(Consts.ISNULL,"产品ID为空！");
        }
        if(status==null||status.equals("")){
            return  ServerResponse.defeatedRS(Consts.ISNULL,"产品状态为空！");
        }

        //判断产品id存在
        int i=productMapper.selectAllDetailById(productId);
        if (i<=0){
            return  ServerResponse.defeatedRS(1,"不存在此ID商品，请检查后重新输入！");
        }

        //判断产品状态是否已经处于此状态
        int s=productMapper.selectByIdAndStatus(productId,status);
        if (s>0){
            return  ServerResponse.defeatedRS("该产品已经处于此状态，无需修改！");
        }

        //修改状态
        int v=productMapper.updateByStatus(productId,status);
        if (v<=0){
            return  ServerResponse.defeatedRS(Consts.DEFEAT,"修改产品状态失败！");
        }

        return ServerResponse.successRS(Consts.SUCCESS,"修改产品状态成功！");


    }

    //新增OR更新产品
    @Override
    public ServerResponse save(Product product) {
        //判断是增加商品还是更新商品，若有ID则是更新商品，否则为增加
        if (product.getId()==null){
            //判定新增
          int s=  productMapper.insert(product);
          if (s<=0){
              return  ServerResponse.defeatedRS("增加商品失败！");
          }
          return ServerResponse.successRS("增加商品成功！");

        }else{
            //检查是否有此商品
            int i=productMapper.selectAllDetailById(product.getId());
            if (i<=0){
                return  ServerResponse.defeatedRS(Consts.ISNOTHAVA,"不存在此ID商品，请检查后重新输入！");
            }
            //判定更新 存在ID  开始更新
            int v=productMapper.updateByPrimaryKey(product);
            if (v<=0){
                return  ServerResponse.defeatedRS(Consts.DEFEAT,"更新失败！");
            }
            return ServerResponse.successRS(Consts.SUCCESS,"产品信息更新成功！");
        }

    }

    //产品搜索
    @Override
    public ServerResponse search(String productName, Integer productId,Integer pageNum,Integer pageSize) {
        //判断非空
        if ((productName==null||productName.equals(""))&&(productId==null||productId.equals("")) ){
            return  ServerResponse.defeatedRS(Consts.ISNULL,"用户名和ID均为空！");
        }

        String productNames="%"+productName+"%";
        String productIds="%"+productId+"%";

        PageHelper.startPage(pageNum,pageSize,"id"+" "+"asc");

        ArrayList<Product> li=null;

        if (productName==null||productName.equals("")){
            //存在id
            li= productMapper.selectDataByProductId(productIds);
        }else if(productId==null||productId.equals("")){
            //存在name
            li= productMapper.selectDataByProductName(productNames);
        }else{
            //两个都在
            li=productMapper.selectDataByProductNameProductId(productNames,productIds);
        }

        PageInfo pf=new PageInfo(li);
        return ServerResponse.successRS(pf);
    }

}
