package com.itdr.service.Iml;

import com.itdr.commom.ServerResponse;
import com.itdr.mappers.CartMapper;
import com.itdr.mappers.OrdersMapper;
import com.itdr.mappers.Orders_itemMapper;
import com.itdr.mappers.ProductMapper;
import com.itdr.pojo.Cart;
import com.itdr.pojo.Orders;
import com.itdr.pojo.Orders_item;
import com.itdr.pojo.Product;
import com.itdr.service.OrderService;
import com.itdr.utils.BigDecimalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceIml implements OrderService {

    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    Orders_itemMapper ordersItemMapper;


    //创建订单
    @Override
    public ServerResponse creates(Integer uid, Integer shippingId) {
        //参数验证
        if ((shippingId == null || shippingId.equals(""))) {
            return ServerResponse.defeatedRS("参数非法！");
        }
        //产品数据对象集合
        List<Product> productList=new ArrayList<>();
        //在购物车根据订单号查询所有订单对象集合
        List<Cart> li = cartMapper.selectByUid(uid);
        if (li.size() == 0) {
            return ServerResponse.defeatedRS("至少选择一件商品");
        }
        //计算订单总价
        BigDecimal payment = new BigDecimal("0");
        for (Cart cart : li) {
            //判断商品是否失效
            Integer productId = cart.getProductId();
            //根据商品ID 获取商品对象
            Product re = productMapper.selectByProducrId(productId);
            if (re.getStatus() == 1 && re != null) {
                return ServerResponse.defeatedRS("商品已经下架");
            }
            //校验库存
             if (cart.getQuantity()>re.getStock()){
                return ServerResponse.defeatedRS("库存不足");
             }

            //计算一条购物车信息的总价
            BigDecimal yt = BigDecimalUtils.mul(cart.getQuantity().doubleValue(), re.getPrice().doubleValue());
            payment = BigDecimalUtils.add(payment.doubleValue(), yt.doubleValue());
            //存放到集合中
            productList.add(re);
        }

        //创建订单，存到数据库
        Orders orders = this.GetOrder(uid, shippingId);
        int insert = ordersMapper.insert(orders);
        if (insert<=0){
            return ServerResponse.defeatedRS("订单插入数据失败！");
        }
        //创建订单详情，存到数据库，使用批量插入
        List<Orders_item> oderItem = this.getOderItem(uid, orders.getOrderNo(), productList, li);
        int insertAll=ordersItemMapper.insertAll(oderItem);
        if (insertAll<=0){
            return  ServerResponse.defeatedRS("订单详情数据插入失败！");
        }
        
        //插入成功，减少库存
        for (Orders_item ordersItem : oderItem) {
            for (Product product : productList) {
              if (ordersItem.getProductId()==product.getId()) {
               int count= product.getStock()-ordersItem.getQuantity();
               if (count<0){
                 return ServerResponse.defeatedRS("库存非法") ;
               }
                  product.setStock(count);
               //更新数据到数据库
                int v= productMapper.updateByProductId(product.getId(),product.getStock());

              }
            }
        }



        return null;
    }



    //计算订单总价
    private BigDecimal getpayment(List<Cart> li){


        return null;
    }

    //创建订单详情
    private List<Orders_item> getOderItem(Integer uid,Long orderNo,List<Product> productList, List<Cart> li){
        List<Orders_item> ItemList=new ArrayList<>();
        Orders_item vf=new Orders_item();
        for (Cart cart : li) {
            vf.setQuantity(cart.getQuantity());
            for (Product product : productList) {
                if (cart.getProductId()==product.getId()){
                    vf.setUserId(uid);
                    vf.setOrderNo(orderNo);
                    vf.setProductId(product.getId());
                    vf.setProductName(product.getName());
                    vf.setProductImage(product.getMain_image());
                    vf.setCurrentUnitPrice(product.getPrice());
                    BigDecimal yt = BigDecimalUtils.mul(cart.getQuantity().doubleValue(), product.getPrice().doubleValue());
                    vf.setTotalPrice(yt);
                }
            }
        }




        return ItemList;
    }




    //创建一个订单对象
    private Orders GetOrder(Integer uid, Integer shippingId) {
        Orders o = new Orders();
        o.setUserId(uid);
        o.setOrderNo(this.getOrderNo());
        o.setShippingId(shippingId);
        o.setPaymentType(1);
        o.setPostage(0);
        o.setStatus(10);
        return o;

    }

    //创建订单号
    private Long getOrderNo() {
        long l = System.currentTimeMillis();
        long orderNo = l + Math.round(Math.random() * 100);
        return orderNo;
    }


}
