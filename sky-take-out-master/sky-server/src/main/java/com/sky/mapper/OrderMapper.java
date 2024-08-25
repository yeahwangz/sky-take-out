package com.sky.mapper;

import com.sky.dto.GoodsSalesDTO;
import com.sky.entity.Orders;
import com.sky.vo.TurnoverReportVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    /**
     * 插入订单数据
     * @param orders
     */
    void insert(Orders orders);

    /**
     * 根据订单号查询订单
     * @param orderNumber
     */
    @Select("select * from sky_take_out.orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 修改订单信息
     * @param orders
     */
    void update(Orders orders);

    /**
     * 查询超时未支付订单和超时未确认收货订单
     * @param pendingPayment
     * @param localDateTime
     * @return
     */
    @Select("select * from sky_take_out.orders where status = #{pendingPayment} and order_time < #{localDateTime}")
    List<Orders> getByStatusAndOrderTimeLT(Integer pendingPayment, LocalDateTime localDateTime);

    /**
     * 通过订单id查询订单
     * @param id
     * @return
     */
    @Select("select * from sky_take_out.orders where id = #{id}")
    Orders getById(Long id);

    /**
     * 根据动态条件统计营业额数据
     * @param map
     * @return
     */
    Double sumByMap(Map map);

    /**
     * 订单统计接口
     * @param map
     * @return
     */
    Integer getOrderCount(Map map);

    /**
     * 统计指定时间区间内的销量排名前十
     * @param begin
     * @param end
     * @return
     */
    List<GoodsSalesDTO> getTop10Sales(LocalDateTime begin,LocalDateTime end);
}
