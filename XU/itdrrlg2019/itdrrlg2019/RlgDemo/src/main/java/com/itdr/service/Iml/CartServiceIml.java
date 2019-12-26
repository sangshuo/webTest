package com.itdr.service.Iml;


import com.itdr.commom.Consts;
import com.itdr.commom.ServerResponse;
import com.itdr.mappers.CartMapper;
import com.itdr.mappers.ProductMapper;
import com.itdr.pojo.Cart;
import com.itdr.pojo.Product;
import com.itdr.pojo.vo.CartProductVO;
import com.itdr.pojo.vo.CartVO;
import com.itdr.service.CartService;
import com.itdr.utils.BigDecimalUtils;
import com.itdr.utils.PojoToVo;
import com.itdr.utils.PropertiesUTils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CartServiceIml implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductMapper productMapper;

//购物车添加商品
    @Override
    public ServerResponse<Cart> addone(Integer productId, Integer count,Integer uid) {
      //参数非空判断
        if(productId==null||productId<0||count<=0){
            return  ServerResponse.defeatedRS("非法参数");
        }
        //创建一个Cart对象
        Cart c=new Cart();
        //向购物车中传输数据
        c.setUserId(uid);
        c.setProductId(productId);
        c.setQuantity(count);
        //如果购物车中有这条信息即为更新，没有则为插入
       Cart tr= cartMapper.selectByUidAndProductId(uid,productId);
        if (tr!=null){
            //更新数据
            tr.setQuantity(tr.getQuantity()+count);
            int i= cartMapper.updateByPrimaryKeySelective(tr);
        }else{
            //插入数据
            int i= cartMapper.insert(c);
        }
        return lists(uid);
    }

//登录用户购物车List列表
    @Override
    public ServerResponse<Cart> lists(Integer uid) {
        CartVO vo = getCartVo(uid);
       return  ServerResponse.successRS(vo);
    }
//更新购物车某个产品数量
@Override
public ServerResponse<Cart> update(Integer productId, Integer count, Integer uid) {
    //参数非空判断
    if(productId==null||productId<0||count<=0){
        return  ServerResponse.defeatedRS("非法参数");
    }
    //如果购物车中有这条信息即为更新，没有则为插入
    Cart tr= cartMapper.selectByUidAndProductId(uid,productId);
    tr.setQuantity(count);
    int i= cartMapper.updateByPrimaryKeySelective(tr);
     return lists(uid);
   }



    //移除购物车某个产品
    @Override
    public ServerResponse<Cart> deleteproduct(String productIds, Integer uid) {
        //参数非空判断
        if(productIds==null){
            return  ServerResponse.defeatedRS("非法参数");
        }
        String[] split = productIds.split("，");
        List<String> sr = Arrays.asList(split);
        int i= cartMapper.delectByProductIds(sr,uid);
        return lists(uid);
    }


    //查询在购物车里的产品数量
    @Override
    public ServerResponse<Cart> getcartproductcount(Integer uid) {
        ArrayList<Cart> li= cartMapper.selectByUid(uid);
        return ServerResponse.successRS(li.size());
    }

//  全选
    @Override
    public ServerResponse<Cart> select_all(Integer check,Integer uid,Integer productId) {
       boolean flag=selectchecked(uid);
       int i= cartMapper.updateBySelect(check,uid,productId);
        return lists(uid);
    }

    //购物车高可复用方法
    private CartVO getCartVo(Integer uid){
        //创建VO对象
        CartVO cartVO=new CartVO();
        //存储购物车总价
        BigDecimal CartTotalPrice=new BigDecimal("0");

        //根据用户名得到购物车中信息对象
        List<Cart> liCart=cartMapper.selectByUid(uid);

        //存放CartProductVo集合
        List<CartProductVO> cartProductVOList=new ArrayList<>();


        //从购物车中的信息中依据产品ID查询产品信息
        //遍历购物车对象
        if (liCart.size()!=0){
            for (Cart cart : liCart) {

                System.out.println(cart.getProductId());
            //产品对象
           Product pr= productMapper.selectGetOneById(cart.getProductId());
           //封装
           CartProductVO one = PojoToVo.getOne(cart, pr);
           //购物车更新有效库存
                Cart cartQuanttity =new Cart();
                cartQuanttity.setId(cart.getId());
                cartQuanttity.setQuantity(one.getQuantity());
                cartMapper.updateByPrimaryKeySelective(cartQuanttity);
           //存入集合
            cartProductVOList.add(one);

                CartTotalPrice= BigDecimalUtils.add(CartTotalPrice.doubleValue(),one.getProductTotalPrice()
                        .doubleValue());


            }
        }else {

        }
        cartVO.setAllchecked(selectchecked(uid));
        try {
            cartVO.setImageHost(PropertiesUTils.getValues("imageHost"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        cartVO.setCartTotalPrice(CartTotalPrice);
        cartVO.setCartProductVOList(cartProductVOList);

        return cartVO;

    }



    //查询购物车中商品是否被全选
    private boolean selectchecked(Integer uid){
       int s= cartMapper.selectcheckeds(uid);
       if (s!=0){
           //非全选
           return false;
       }
       return true;
    }



}
