package com.itdr.pojo.vo;

import java.math.BigDecimal;
import java.util.List;

public class CartVO {

    private List<CartProductVO> cartProductVOList;
    private boolean allchecked;
    private BigDecimal cartTotalPrice;
    private String imageHost;

    public List<CartProductVO> getCartProductVOList() {
        return cartProductVOList;
    }

    public void setCartProductVOList(List<CartProductVO> cartProductVOList) {
        this.cartProductVOList = cartProductVOList;
    }

    public boolean isAllchecked() {
        return allchecked;
    }

    public void setAllchecked(boolean allchecked) {
        this.allchecked = allchecked;
    }

    public BigDecimal getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(BigDecimal cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }
}
