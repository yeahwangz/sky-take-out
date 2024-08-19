package com.sky.service.impl;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import com.sky.vo.ShoppingCartVO;
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

    /**
     * 查看购物车
     */
    public List<ShoppingCartVO> getShoppingCarts() {
        return shoppingCartMapper.getByShoppingCarts();
    }

    /**
     * 清空购物车
     */
    @Transactional
    public void cleanShoppingCarts() {
        shoppingCartMapper.cleanShoppingCarts();
    }

    /**
     * 删除购物车中一个商品
     * @param shoppingCartDTO
     */
    @Transactional
    public void cleanShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        shoppingCartMapper.cleanShoppingCart(shoppingCartDTO);
    }
}
