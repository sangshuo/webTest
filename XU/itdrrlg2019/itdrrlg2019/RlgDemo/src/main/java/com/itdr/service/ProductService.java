package com.itdr.service;

import com.itdr.commom.ServerResponse;

public interface ProductService {
    //获取产品分类
    ServerResponse topcategory(Integer sid);
    //产品detail
    ServerResponse details(Integer productId, Integer is_new, Integer is_hot, Integer is_banner);
    //产品搜索及动态排序List
    ServerResponse list(Integer productId, String keyword, Integer pageNum, Integer pageSize, String orderBy);
    //产品Hot/New/Banner
    ServerResponse detailsNewOrHotOrBanner(Integer isnew, Integer ishot, Integer isbanner);
}
