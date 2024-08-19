package com.sky.mapper;

import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface OrderDetailMapper {

    /**
     * 批量插入订单细节信息
     * @param orderDetailArrayList
     */
    void insertBatch(ArrayList<OrderDetail> orderDetailArrayList);
}
