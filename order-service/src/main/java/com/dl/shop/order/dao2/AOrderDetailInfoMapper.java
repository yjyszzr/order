package com.dl.shop.order.dao2;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dl.base.mapper.Mapper;
import com.dl.shop.order.model.AOrderDetail;
import com.dl.shop.order.model.LotteryClassifyTemp;
import com.dl.shop.order.model.LotteryPlayClassifyTemp;
import com.dl.shop.order.model.OrderDetail;
import com.dl.shop.order.model.PlayTypeName;

public interface AOrderDetailInfoMapper extends Mapper<AOrderDetail> {
	
	public LotteryClassifyTemp lotteryClassify(@Param("classifyId")Integer lotteryClassifyId); 

	
	/**
	 * 获取玩法名称
	 * @param lotteryClassifyId
	 * @return
	 */
	public List<PlayTypeName> getPlayTypes(@Param("lotteryClassifyId")Integer lotteryClassifyId);
	
	
	/**
	 * 
	 * @param classifyId
	 * @param playClassifyId
	 * @return  status, redirectUrl
	 */
	public LotteryPlayClassifyTemp lotteryPlayClassifyStatusAndUrl(@Param("classifyId") int classifyId, @Param("playClassifyId") int playClassifyId);

	/**
	 * @param orderId
	 * @return
	 */
	public List<OrderDetail> queryListByOrderId(@Param("orderId")Integer orderId);
	
	
	/**
	 * 
	 * @param orderId
	 * @param userId
	 * @return
	 */
	public List<OrderDetail> selectByOrderId(@Param("orderId")Integer orderId, @Param("userId")Integer userId);
}