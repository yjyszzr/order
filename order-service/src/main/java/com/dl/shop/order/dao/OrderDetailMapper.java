package com.dl.shop.order.dao;

import com.dl.base.mapper.Mapper;
import com.dl.shop.order.model.LotteryClassifyTemp;
import com.dl.shop.order.model.LotteryPlayClassifyTemp;
import com.dl.shop.order.model.OrderDetail;
import com.dl.shop.order.model.PlayTypeName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDetailMapper extends Mapper<OrderDetail> {
	
	public List<OrderDetail> queryListByOrderSn(@Param("orderSn")String orderSn);


	public List<String> queryTicketDataListByOrderSn(@Param("orderSn")String orderSn);

	
	/**
	 * @param orderId
	 * @return
	 */
	public List<OrderDetail> queryListByOrderId(@Param("orderId")Integer orderId);
	
	public int deleteOrderTicketByOrderSn(@Param("orderSn") String orderSn,@Param("orderSn_new") String orderSn_new);
	/**
	 * 
	 * @param orderId
	 * @param userId
	 * @return
	 */
	public List<OrderDetail> selectByOrderId(@Param("orderId")Integer orderId );
//	public List<OrderDetail> selectByOrderId(@Param("orderId")Integer orderId, @Param("userId")Integer userId);
	
	
	/**
	 * 查询用户某天所下的订单中包含比赛id集合
	 * @param orderId
	 * @param userId
	 * @return
	 */
	public List<String> selectMatchIdsInSomeDayOrder(@Param("dateStr") String dateStr, @Param("userId")Integer userId);
	


	/**
	 * 获取 没有赛事结果 的订单详情
	 * @return
	 */
	public List<OrderDetail> unMatchResultOrderDetails();
	
	/**
	 * 获取玩法名称
	 * @param lotteryClassifyId
	 * @return
	 */
	public List<PlayTypeName> getPlayTypes(@Param("lotteryClassifyId")Integer lotteryClassifyId);
	/**
	 * 获取玩法内容
	 * @param playCode
	 * @param playType
	 * @return
	 */
//	public String getPlayContent(@Param("playCode")String playCode, @Param("playType")Integer playType);


	public void updateTicketData(OrderDetail orderDetail);
	/**
	 * 
	 * @param classifyId
	 * @param playClassifyId
	 * @return  status, redirectUrl
	 */
	public LotteryPlayClassifyTemp lotteryPlayClassifyStatusAndUrl(@Param("classifyId") int classifyId, @Param("playClassifyId") int playClassifyId);


	public LotteryClassifyTemp lotteryClassify(@Param("classifyId")Integer lotteryClassifyId);
	
}