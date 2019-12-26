package com.itdr.service;

        import com.itdr.commom.ServerResponse;
        import com.itdr.pojo.Category;

        import javax.servlet.http.HttpSession;

public interface ICategoryService {

    //获取当前分类id及递归子节点categoryId
    ServerResponse getdeepcategory(Integer categoryId);
    //修改品类名字
    ServerResponse setcategoryname(Integer categoryId, String categoryName);
    //增加节点
    ServerResponse addcategory(Integer parentId,String categoryName);
    //获取品类子节点(平级)
    ServerResponse getcategory(Integer categoryId);
}
