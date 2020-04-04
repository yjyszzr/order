package com.dl.shop.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dl.base.mapper.Mapper;
import com.dl.order.dto.OrderInfoListDTO;
import com.dl.order.dto.OrderWithUserDTO;
import com.dl.order.param.UpdateOrderInfoParam;
import com.dl.order.param.UpdateOrderPayStatusParam;
import com.dl.shop.order.model.ChannelOperationLog;
import com.dl.shop.order.model.DlChannelConsumer;
import com.dl.shop.order.model.DlChannelDistributor;
import com.dl.shop.order.model.Order;
import com.dl.shop.order.model.User;

public interface OrderMapper extends Mapper<Order> {

	/**
	 * 查询多个订单
	 * @param orderSnList
	 * @return
	 */
	public List<Order> queryOrderList(@Param("orderSnList") List<String> orderSnList);
	
	/**
	 * 保存订单数据
	 * 
	 * @param order
	 */
	public int insertOrderData(Order order);

	/**
	 * 根据订单状态查询订单列表
	 * 
	 * @param statusList
	 * @return
	 */
	public List<OrderInfoListDTO> getOrderInfoList(@Param("statusList") List<Integer> statusList, @Param("userId") Integer userId, @Param("lotteryClassifyId") Integer lotteryClassifyId);
	public List<OrderInfoListDTO> ngetOrderInfoList(@Param("statusList") List<Integer> statusList, @Param("userId") Integer userId);
	public List<OrderInfoListDTO> ngetOrderInfoListV2(@Param("statusList") List<Integer> statusList, @Param("userId") Integer userId);
	
	/**
	 * 支付成功修改订单信息
	 * 
	 * @param param
	 */
	public int updateOrderInfo(UpdateOrderInfoParam param);

	/**
	 * 通过orderSn读取order
	 * 
	 * @param orderSn
	 * @return
	 */
	public Order getOrderInfoByOrderSn(@Param("orderSn") String orderSn);

	/**
	 * 根据期次获取中奖用户及奖金
	 * 
	 * @param issue
	 * @return
	 */
	public List<OrderWithUserDTO> selectOpenedAllRewardOrderList();

	/**
	 * 待出票订单列表
	 * 
	 * @return
	 */
	public List<Order> ordersListGoPrintLottery();
	/**
	 * 未完全出票订单列表
	 * 
	 * @return
	 */
	public List<Order> ordersListNoFinishAllPrintLottery();

	/**
	 * 查询符合条件的订单号
	 * 
	 * @return
	 */
	public List<String> queryOrderSnListByStatus(Order order);

	/**
	 * 查询符合条件的订单集合
	 * 
	 * @return
	 */
	public List<Order> queryOrderListBySelective(@Param("nowTime") Integer nowTime);

	int updateOrderStatus(Order order);

	/**
	 * 更新出票相关信息
	 * 
	 * @param order
	 */
	public int updateOrderTicketInfo(Order order);
	
	
	/**
	 * 根据某个订单状态修改为其他状态，入参均不为空
	 * 
	 * @param order
	 */
	public int updateOrderStatusByAnotherStatus(@Param("orderSns") List<String> orderSns,@Param("orderStatusAfter") String orderStatusAfter,@Param("orderStatusBefore") String orderStatusBefore);

	/**
	 * 更新中奖金额
	 * 
	 * @param order
	 * @return
	 */
	public int updateWiningMoney(Order order);

	public List<DlChannelConsumer> selectConsumers(@Param("userIds") List<Integer> userIds);

	public List<User> findAllUser(@Param("userIds") List<Integer> userIds);

	public List<DlChannelDistributor> channelDistributorList(@Param("channelDistributorIds") List<Integer> channelDistributorIds);

	public void saveChannelOperation(ChannelOperationLog channelOperationLog);

	//支付成功
	public int updateOrderPayStatus(UpdateOrderPayStatusParam param);

	public int deleteOrderByOrderSn(@Param("orderSn") String orderSn);
	
	public int deleteOrderByOrderSnAndIsdelete(@Param("orderSn") String orderSn,@Param("orderSn_new") String orderSn_new,@Param("isDelete") Integer isdelete);

	//获取用户购彩金额
	public Double getUserMoneyPay(@Param("userId")Integer userId);
	
	
	public int updateOrderPicByOrderSn(@Param("orderSn") String orderSn,@Param("pic") String pic,@Param("orderStatus") Integer orderStatus);

	public List<OrderInfoListDTO> getOrderInfoListForStoreProject(@Param("statusList") List<Integer> statusList, @Param("userIdList")List<Integer> userIdList, @Param("storeIdList")List<Integer> storeIdList );

	public Order  getOrderInfoByOrderId(@Param("orderId") Integer orderId);

}