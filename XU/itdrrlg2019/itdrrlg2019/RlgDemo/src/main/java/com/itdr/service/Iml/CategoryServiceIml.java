package com.itdr.service.Iml;

import com.itdr.commom.Consts;
import com.itdr.commom.ServerResponse;
import com.itdr.mappers.CategoryMapper;
import com.itdr.pojo.Category;
import com.itdr.pojo.Users;
import com.itdr.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceIml implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;


    //获取当前分类id及递归子节点categoryId
    @Override
    public ServerResponse getdeepcategory(Integer categoryId) {
        if (categoryId==null||categoryId<0){
            return  ServerResponse.defeatedRS("参数非法");
        }

        List<Integer> se = new ArrayList();
        findAllChildCategory(se, categoryId);
        ServerResponse rs = ServerResponse.successRS(se);
        return rs;


    }

    private void findAllChildCategory(List<Integer> list, Integer PId) {

        List<Category> li = categoryMapper.selectByParentID(PId);

        if (li != null && li.size() != 0) {
            for (Category o : li) {
                list.add(o.getId());
                findAllChildCategory(list, o.getId());
            }
        }

    }
    //修改品类名字
    @Override
    public ServerResponse setcategoryname(Integer categoryId, String categoryName) {
        if (categoryId==null||categoryId.equals("")){
            return  ServerResponse.defeatedRS("参数ID为空");
        }
        if (categoryName==null||categoryName.equals("")){
            return  ServerResponse.defeatedRS("参数名为空");
        }

        //查询ID是否存在
        int v=categoryMapper.selectById(categoryId);
        if (v<=0){
            return  ServerResponse.defeatedRS("不存在此分类！");
        }

        int s=categoryMapper.updateByIDAndName(categoryId,categoryName);

        if(s<=0){
            return  ServerResponse.defeatedRS("更新品类名字失败！");
        }

        return ServerResponse.successRS("更新品类名字成功！");
    }



    //增加节点
    @Override
    public ServerResponse addcategory(Integer parentId,String categoryName) {
        //判断非空
        if (parentId==null||parentId.equals("")){
            return  ServerResponse.defeatedRS("父类ID为空");
        }

        if ( categoryName==null|| categoryName.equals("")){
            return  ServerResponse.defeatedRS("类别名为空");
        }

        //判断节点是否已经存在
        int v=categoryMapper.selectById(parentId);
        if (v>0){
            return  ServerResponse.defeatedRS("此节点已经存在！");
        }

        int  s=categoryMapper.inserts(parentId,categoryName);
        if (s<=0){
            return  ServerResponse.defeatedRS("添加品类失败！");
        }

        return ServerResponse.successRS("添加品类成功！");
    }



    //获取品类子节点(平级)
    @Override
    public ServerResponse getcategory(Integer categoryId) {
        //判断非空
        System.out.println(categoryId);

        if (categoryId==null||categoryId.equals("")){
            return  ServerResponse.defeatedRS("ID为空");
        }
        //判断ID是否已经存在
        int v=categoryMapper.selectById(categoryId);
        if (v<=0){
            return  ServerResponse.defeatedRS("此品类不存在！");
        }

        ArrayList<Category> li=new ArrayList<Category>();
        li=categoryMapper.selectBycategoryId(categoryId);
        return ServerResponse.successRS(li);
    }
}
