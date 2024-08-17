package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.vo.ShoppingCartVO;

import java.util.List;

public interface ShoppingCartService {
    /**
     * 添加购物车
     * @param shoppingCart
     */
    void add(ShoppingCart shoppingCart);

    /**
     * 查看是否有相同购物车内容
     * @param shoppingCart
     */
    List<ShoppingCart> getShoppingCart(ShoppingCart shoppingCart);

    /**
     * 购物车商品数量+1
     * @param cartId
     * @param newNumber
     */
    void increaseNumber(Long cartId, Integer newNumber);

    /**
     * 查看购物车
     */
    List<ShoppingCartVO> getShoppingCarts();

    /**
     * 清空购物车
     */
    void cleanShoppingCarts();

    /**
     * 删除购物车中一个商品
     * @param shoppingCartDTO
     */
    void cleanShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
