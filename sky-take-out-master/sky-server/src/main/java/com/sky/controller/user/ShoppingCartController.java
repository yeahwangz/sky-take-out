package com.sky.controller.user;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.service.SetmealService;
import com.sky.service.ShoppingCartService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
@Api(tags = "C端-购物车接口")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private DishService dishService;

    /**
     * 添加购物车
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加购物车")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setCreateTime(now);
        shoppingCart.setUserId(currentId);
        List<ShoppingCart> shoppingCarts = shoppingCartService.getShoppingCart(shoppingCart);
        //其实查出来的要么没有，要么只有一条
        if (shoppingCarts != null && shoppingCarts.size() > 0){
            log.info("增加原来相同购物车内容数量：{}",shoppingCartDTO);
            Long CartId = shoppingCarts.get(0).getId();
            Integer oldNumber = shoppingCarts.get(0).getNumber();
            shoppingCartService.increaseNumber(CartId,oldNumber+1);
        }else{
            log.info("添加新的购物车内容：{}",shoppingCartDTO);
            if (shoppingCart.getSetmealId() != null){
                Setmeal setmeal = setmealService.getSetmealById(shoppingCart.getSetmealId());
                String name = setmeal.getName();
                String image = setmeal.getImage();
                BigDecimal price = setmeal.getPrice();
                shoppingCart.setName(name);
                shoppingCart.setImage(image);
                shoppingCart.setAmount(price);
            }else {
                DishVO dishVO = dishService.getByIdWithFlavor(shoppingCart.getDishId());
                shoppingCart.setName(dishVO.getName());
                shoppingCart.setImage(dishVO.getImage());
                shoppingCart.setDishId(shoppingCart.getDishId());
                shoppingCart.setDishFlavor(shoppingCart.getDishFlavor());
                shoppingCart.setAmount(dishVO.getPrice());
            }
            shoppingCart.setNumber(1);
            shoppingCartService.add(shoppingCart);
        }
        return Result.success();
    }
}
