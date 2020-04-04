package com.dl.shop.order.service;
import com.dl.shop.order.model.GoodsOrder;
import com.dl.shop.order.dao.GoodsOrderMapper;
import com.dl.base.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional("transactionManager1")
public class GoodsOrderService extends AbstractService<GoodsOrder> {
    @Resource
    private GoodsOrderMapper goodsOrderMapper;

	public void saveOrder(GoodsOrder goodsOrder) {
		  goodsOrderMapper.saveOrder(goodsOrder);
	}

}
