package com.dl.shop.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dl.shop.order.enums.OrderPayStateEnum;
import com.dl.shop.order.enums.OrderStateEnum;
import com.dl.shop.order.model.OrderLog;

@Service
@Transactional("transactionManager1")
public class OrderLogContentService {
	
	public static final String PAY_STATE_TYPE = "1";
	public static final String ORDER_STATE_TYPE = "2";
	public static final String SHIPPING_STATE_TYPE = "3";

	public String getOrderContent(OrderLog orderLog,Integer beforeState,String stateType,boolean isNew,boolean isSysOpt,Integer userId) {
		StringBuffer content = new StringBuffer();
		if(isSysOpt) {
			content.append("【系统操作】");
		} else {
			content.append("会员【");
			content.append(userId);
			content.append("】");
		}
		
		if(isNew) {
			content.append("生成");
		} else {
			content.append("修改");
		}
		content.append("订单【");
		content.append(orderLog.getOrderId());
		content.append("】");
		if(ORDER_STATE_TYPE.equals(stateType)) {
			if(beforeState == null) {
				content.append("订单状态为【");
				content.append(OrderStateEnum.getStateName(orderLog.getStatus()));
				content.append("】");
			}else {
				content.append("订单状态由【");
				content.append(OrderStateEnum.getStateName(beforeState));
				content.append("】更新为【");
				content.append(OrderStateEnum.getStateName(orderLog.getStatus()));
				content.append("】");
			}
		}
		
		if(PAY_STATE_TYPE.equals(stateType)) {
			if(beforeState == null) {
				content.append("支付状态为【");
				content.append(OrderPayStateEnum.getStateName(orderLog.getStatus()));
				content.append("】");
			}else {
				content.append("支付状态由【");
				content.append(OrderPayStateEnum.getStateName(beforeState));
				content.append("】更新为【");
				content.append(OrderPayStateEnum.getStateName(orderLog.getStatus()));
				content.append("】");
			}
		}
		
		return content.toString();
	}
	
}
