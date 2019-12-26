package com.itdr.service.Iml;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itdr.commom.Consts;
import com.itdr.commom.ServerResponse;
import com.itdr.mappers.CategoryMapper;
import com.itdr.mappers.ProductMapper;
import com.itdr.pojo.Category;
import com.itdr.pojo.Product;
import com.itdr.pojo.vo.ProductVO;
import com.itdr.service.ProductService;
import com.itdr.utils.PojoToVo;
import com.itdr.utils.PropertiesUTils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceIml implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryMapper categoryMapper;


    //获取产品分类
    @Override
    public ServerResponse topcategory(Integer sid) {
        if (sid==null||sid.equals("")||sid<0){
            return  ServerResponse.defeatedRS("参数为空");
        }

    //判断是否存在此category_id
        int i=categoryMapper.selectByID(sid);

        if (i<=0){
            return  ServerResponse.defeatedRS("不存在此Id");
        }

        ArrayList<Category> li =categoryMapper.selectAllById(sid);

        if (li==null){
            return  ServerResponse.defeatedRS("无");
        }
        if (li.size()==0){
            return  ServerResponse.defeatedRS("没有子分类");
        }
        return ServerResponse.successRS(li);
    }

    //产品detail
    @Override
    public ServerResponse details(Integer productId,
                   Integer is_new,
                   Integer is_hot,
                   Integer is_banner) {
        //判断非空

//         if(productId==null||productId.equals("")){
//            return  ServerResponse.defeatedRS(2,"产品ID为空！");
//        }
//        if(is_new==null||is_new.equals("")){
//            return  ServerResponse.defeatedRS(2,"产品最新状态为空！");
//        }
//        if(is_hot==null||"".equals(is_hot)){
//            return  ServerResponse.defeatedRS(2,"产品最热状态为空！");
//        }
//        if(is_banner==null||is_banner.equals("")){
//            return  ServerResponse.defeatedRS(2,"产品轮播状态为空！");
//        }
         //判断产品id存在
        int i=productMapper.selectAllDetailById(productId);
         if (i<=0){
             return  ServerResponse.defeatedRS(1,"不存在此ID商品，请检查后重新输入！");
         }

        //判断产品是否已经下架
        int  v=productMapper.selectStatusById(productId);
         if (v<=0){
             return  ServerResponse.defeatedRS(4,"所查询商品已经下架！");
         }

        //获取产品详情

         Product li=productMapper.selectGetAllDetailById(productId,is_new,is_hot,is_banner);
         if (li==null){
             return  ServerResponse.defeatedRS(Consts.DEFEAT,"不存在符合此要求的商品！");
         }

        ProductVO se=null;
         try{
             se= PojoToVo.ProductToProductVo(li);
         }catch (Exception e){
             e.printStackTrace();
         }

        return ServerResponse.successRS(Consts.SUCCESS,se);
    }
    //产品搜索及动态排序List
    @Override
    public ServerResponse list(Integer productId, String keyword, Integer pageNum, Integer pageSize, String orderBy) {

     if ((productId==null||productId.equals(""))&&( keyword==null|| keyword.equals(""))){
        return ServerResponse.defeatedRS("参数非法！");
     }
        String[] split = orderBy.split("_");
        String sk="%"+keyword+"%";

        PageHelper.startPage(pageNum,pageSize,split[0]+" "+split[1]);
        ArrayList li=productMapper.selectByIdOrName( productId,sk,split[0],split[1]);
        PageInfo pf=new PageInfo(li);

        return  ServerResponse.successRS(pf);
    }

    //Hot New Banner
    @Override
    public ServerResponse detailsNewOrHotOrBanner(Integer isnew, Integer ishot, Integer isbanner) {
        List<Product> li=productMapper.selectNewOrHotOrBanner(isnew,ishot,isbanner);
        List<ProductVO> voList=new ArrayList<>();
        for (Product product : li) {
            try {
                ProductVO vo=PojoToVo.ProductToProductVo(product);
                voList.add(vo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ServerResponse.successRS(Consts.SUCCESS,voList);
    }
}
