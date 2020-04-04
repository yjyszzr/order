package com.dl.shop.order.service;

import java.util.List;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dl.base.service.AbstractService;
import com.dl.shop.order.dao2.AOrderDetailInfoMapper;
import com.dl.shop.order.model.AOrderDetail;
import com.dl.shop.order.model.LotteryClassifyTemp;
import com.dl.shop.order.model.LotteryPlayClassifyTemp;
import com.dl.shop.order.model.OrderDetail;
import com.dl.shop.order.model.PlayTypeName;

@Service
@Transactional("transactionManager2")
public class OrderDetailService2 extends AbstractService<OrderDetail> {
 
	@Resource
	private AOrderDetailInfoMapper aOrderDetailInfoMapper;

	public void insertOrderDetailsList(List<AOrderDetail> orderDetails) {
		aOrderDetailInfoMapper.insertList(orderDetails);
	}
 
	
	public LotteryClassifyTemp lotteryClassify(Integer lotteryClassifyId,Integer storeId) {
		return aOrderDetailInfoMapper.lotteryClassify(lotteryClassifyId);
	}
	
	
	/**
	 * 获取玩法名称
	 * @param lotteryClassifyId
	 * @param storeId
	 * @return
	 */
	public List<PlayTypeName> getPlayTypes(Integer lotteryClassifyId,Integer storeId){
		return aOrderDetailInfoMapper.getPlayTypes(lotteryClassifyId);
	}
	
	/**
	 * 
	 * @param classifyId
	 * @param playClassifyId
	 * @return  status, redirectUrl
	 */
	public LotteryPlayClassifyTemp lotteryPlayClassifyStatusAndUrl(int classifyId,int playClassifyId,Integer storeId){
		return aOrderDetailInfoMapper.lotteryPlayClassifyStatusAndUrl(classifyId, playClassifyId);
	}
	
	/**
	 * @param orderId
	 * @return
	 */
	public List<OrderDetail> queryListByOrderId(Integer orderId,Integer storeId){
		return aOrderDetailInfoMapper.queryListByOrderId(orderId);
	}
	
	
	/**
	 * 
	 * @param orderId
	 * @param userId
	 * @return
	 */
	public List<OrderDetail> selectByOrderId(Integer orderId,Integer userId,Integer storeId){
		return aOrderDetailInfoMapper.selectByOrderId(orderId, userId);
	}
}
