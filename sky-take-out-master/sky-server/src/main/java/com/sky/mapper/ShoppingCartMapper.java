package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 添加购物车
     * @param shoppingCart
     */
    void add(ShoppingCart shoppingCart);

    /**
     * 查看是否有相同购物车内容
     * @param shoppingCart
     */
    List<ShoppingCart> getByShoppingCart(ShoppingCart shoppingCart);

    /**
     * 购物车商品数量+1
     * @param cartId
     * @param newNumber
     */
    @Update("update sky_take_out.shopping_cart set number = #{newNumber} where id = #{cartId}")
    void increaseNumber(Long cartId, Integer newNumber);
}
