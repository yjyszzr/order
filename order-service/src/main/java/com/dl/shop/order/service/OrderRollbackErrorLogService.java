package com.dl.shop.order.service;
import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dl.base.service.AbstractService;
import com.dl.shop.order.dao.OrderRollbackErrorLogMapper;
import com.dl.shop.order.enums.OrderRollBackBizType;
import com.dl.shop.order.enums.OrderRollBackErrorType;
import com.dl.shop.order.model.OrderRollbackErrorLog;

@Service
@Transactional("transactionManager1")
public class OrderRollbackErrorLogService extends AbstractService<OrderRollbackErrorLog> {
    @Resource
    private OrderRollbackErrorLogMapper orderRollbackErrorLogMapper;

    @Async
	public void saveAccountRollbackErrorLog(Integer orderId) {
		OrderRollbackErrorLog log = new OrderRollbackErrorLog();
		log.setOrderId(orderId);
		log.setBizType(OrderRollBackBizType.SUMBIT_ORDER.getCode());
		log.setErrorType(OrderRollBackErrorType.ACCOUNT_ROLLBACK_ERROR.getCode());
		save(log);
	}
}
