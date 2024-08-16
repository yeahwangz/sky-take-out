package com.sky.service.impl;

import com.sky.entity.ShoppingCart;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 购物车业务实现
 */
@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    /**
     * 添加购物车
     * @param shoppingCart
     */
    @Transactional
    public void add(ShoppingCart shoppingCart) {
        shoppingCartMapper.add(shoppingCart);
    }

    /**
     * 查看是否有相同购物车内容
     * @param shoppingCart
     */
    public List<ShoppingCart> getShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartMapper.getByShoppingCart(shoppingCart);
    }

    /**
     * 购物车商品数量+1
     * @param cartId
     * @param newNumber
     */
    @Transactional
    public void increaseNumber(Long cartId, Integer newNumber) {
        shoppingCartMapper.increaseNumber(cartId,newNumber);
    }
}
