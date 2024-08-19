package com.sky.mapper;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.vo.ShoppingCartVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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

    /**
     * 查看购物车
     * @return
     */
    @Select("select * from sky_take_out.shopping_cart")
    List<ShoppingCartVO> getByShoppingCarts();

    /**
     * 清空购物车
     */
    @Delete("delete from sky_take_out.shopping_cart")
    void cleanShoppingCarts();

    /**
     * 删除购物车中一个商品
     * @param shoppingCartDTO
     */
    @Delete("delete from sky_take_out.shopping_cart where dish_flavor = #{dishFlavor} and dish_id = #{dishId} and setmeal_id = #{setmealId}")
    void cleanShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 获取用户的购物车信息
     * @param currentId
     * @return
     */
    @Select("select * from sky_take_out.shopping_cart where user_id = #{currentId}")
    List<ShoppingCart> getByUserId(Long currentId);

    /**
     * 删除用户购物车
     * @param currentId
     */
    @Delete("delete from sky_take_out.shopping_cart where user_id = #{currentId}")
    void deleteByUserId(Long currentId);
}
