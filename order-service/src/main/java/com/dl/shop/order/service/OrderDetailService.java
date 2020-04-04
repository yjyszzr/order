package com.dl.shop.order.service;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dl.base.service.AbstractService;
import com.dl.shop.order.dao.OrderDetailMapper;
import com.dl.shop.order.model.OrderDetail;  

@Service
@Transactional("transactionManager1")
public class OrderDetailService extends AbstractService<OrderDetail> {
    @Resource
    private OrderDetailMapper orderDetailMapper;
    
	/**
	 * 查询 用户当天所下的订单 包含某天的比赛 
	 * @param dateStr
	 * @param userId
	 * @return
	 */
	public List<String> selectMatchIdsInSomeDayOrder(String dateStr, Integer userId){
		List<String> matchIdList = orderDetailMapper.selectMatchIdsInSomeDayOrder(dateStr, userId);
		if(CollectionUtils.isEmpty(matchIdList)) {
			return new ArrayList<String>();
		}
		
		return matchIdList;
	}

}
