package com.dl.shop.order.dao2;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dl.base.mapper.Mapper;
import com.dl.shop.order.model.AOrder;
import com.dl.shop.order.model.Order;

public interface AOrderInfoMapper extends Mapper<AOrder> {

	/**
	 * 保存订单数据
	 * 
	 * @param order
	 */
	public void insertOrder(AOrder order); 
 

	/**
	 * 查询多个订单
	 * @param orderSnList
	 * @return
	 */
	public List<AOrder> queryOrderList(@Param("orderSnList") List<String> orderSnList);
	
	
	/**
	 * 通过orderSn读取order
	 * 
	 * @param orderSn
	 * @return
	 */
	public AOrder getOrderInfoByOrderSn(@Param("orderSn") String orderSn);
	
	public int updateOrderPicByOrderSn(@Param("orderSn") String orderSn,@Param("pic") String pic,@Param("storeId") Integer storeId);
}