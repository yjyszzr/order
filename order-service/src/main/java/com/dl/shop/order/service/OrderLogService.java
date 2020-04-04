package com.dl.shop.order.service;
import com.dl.shop.order.model.OrderLog;
import com.dl.shop.order.dao.OrderLogMapper;
import com.dl.base.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional("transactionManager1")
public class OrderLogService extends AbstractService<OrderLog> {
    @Resource
    private OrderLogMapper orderLogMapper;

}
