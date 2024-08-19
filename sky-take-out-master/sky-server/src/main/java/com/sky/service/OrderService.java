package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import com.sky.vo.OrderPaymentVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
    /**
     * 订单支付
      * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    /**
     * 催单
     * @param id
     */
    void reminder(Long id);
}
