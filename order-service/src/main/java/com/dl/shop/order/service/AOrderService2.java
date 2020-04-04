package com.dl.shop.order.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dl.base.service.AbstractService;
import com.dl.shop.order.dao2.AOrderInfoMapper;
import com.dl.shop.order.model.AOrder;
import com.dl.shop.order.model.AOrderDetail;
import com.dl.shop.order.model.Order;


@Service
@Transactional("transactionManager2")
public class AOrderService2 extends AbstractService<AOrder> {
 
	@Resource
	private AOrderInfoMapper orderInfoMapper;
 
	@Resource
	private OrderDetailService2 orderDetailService2;

	public void insertOrderInfo(AOrder order) {
		orderInfoMapper.insertOrder(order);
	}

	public void insertOrderDetailsList(List<AOrderDetail> orderDetails) {
		orderDetailService2.insertOrderDetailsList(orderDetails);
	}
 
	/***
	 * 根据orderSn获取订单列表
	 * @param orderSnList
	 * @param storeId
	 * @return
	 */
	public List<AOrder> queryOrderList(List<String> orderSnList,Integer storeId){
		return orderInfoMapper.queryOrderList(orderSnList);
	}
	
	
	public AOrder getOrderInfoByOrderSn(String orderSn,Integer storeId) {
		return orderInfoMapper.getOrderInfoByOrderSn(orderSn);
	}
	
	public int updateOrderPicByOrderSn(String orderSn,String pic,Integer storeId) {
		return orderInfoMapper.updateOrderPicByOrderSn(orderSn, pic, storeId);
	}
}
