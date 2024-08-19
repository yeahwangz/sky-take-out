package com.sky.controller.user;

import com.sky.constant.MessageConstant;
import com.sky.context.BaseContext;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.entity.AddressBook;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import com.sky.entity.ShoppingCart;
import com.sky.exception.AddressBookBusinessException;
import com.sky.mapper.AddressBookMapper;
import com.sky.mapper.OrderDetailMapper;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user/order")
@Api(tags = "C端-催单")
@Slf4j
public class OrderController {

   @Autowired
   private AddressBookMapper addressBookMapper;

   @Autowired
   private ShoppingCartMapper shoppingCartMapper;

   @Autowired
   private OrderMapper orderMapper;

   @Autowired
   private OrderDetailMapper orderDetailMapper;

   @Autowired
   private OrderService orderService;

   /**
    * 催单
    * @param id
    * @return
    */
   //
   @GetMapping("/reminder/{id}")
   @ApiOperation("催单")
   public Result remind(@PathVariable Long id){
      orderService.reminder(id);
      return Result.success();
   }

   /**
    * 用户下单
    * @param ordersSubmitDTO
    * @return
    */
   @PostMapping("/submit")
   @ApiOperation("用户下单")
   @Transactional
   public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO){
      log.info("用户下单：{}",ordersSubmitDTO);
      AddressBook byId = addressBookMapper.getById(ordersSubmitDTO.getAddressBookId());
      if (byId == null){
         throw new AddressBookBusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL);
      }
      Long currentId = BaseContext.getCurrentId();
      List<ShoppingCart> shoppingCarts = shoppingCartMapper.getByUserId(currentId);
      if (shoppingCarts == null || shoppingCarts.size() == 0){
         throw new AddressBookBusinessException(MessageConstant.SHOPPING_CART_IS_NULL);
      }
      Orders orders = new Orders();
      BeanUtils.copyProperties(ordersSubmitDTO,orders);
      orders.setOrderTime(LocalDateTime.now());
      orders.setPayStatus(Orders.UN_PAID);
      orders.setStatus(Orders.PENDING_PAYMENT);
      orders.setNumber(String.valueOf(System.currentTimeMillis()));
      orders.setPhone(byId.getPhone());
      orders.setConsignee(byId.getConsignee());
      orders.setUserId(currentId);
      orderMapper.insert(orders);
      ArrayList<OrderDetail> orderDetailArrayList = new ArrayList<>();
      for (ShoppingCart shoppingCart : shoppingCarts) {
         OrderDetail orderDetail = new OrderDetail();
         BeanUtils.copyProperties(shoppingCart,orderDetail);
         orderDetail.setOrderId(orders.getId());
         orderDetailArrayList.add(orderDetail);
      }
      orderDetailMapper.insertBatch(orderDetailArrayList);
      shoppingCartMapper.deleteByUserId(currentId);
      OrderSubmitVO build = OrderSubmitVO.builder()
              .id(orders.getId())
              .orderAmount(orders.getAmount())
              .orderNumber(orders.getNumber())
              .orderTime(orders.getOrderTime())
              .build();
      return Result.success(build);
   }

   /**
    * 订单支付
    *
    * @param ordersPaymentDTO
    * @return
    */
   @PutMapping("/payment")
   @ApiOperation("订单支付")
   public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
      log.info("订单支付：{}", ordersPaymentDTO);
      OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
      log.info("生成预支付交易单：{}", orderPaymentVO);
      return Result.success(orderPaymentVO);
   }
}
