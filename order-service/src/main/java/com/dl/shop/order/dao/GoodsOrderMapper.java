package com.dl.shop.order.dao;

import com.dl.base.mapper.Mapper;
import com.dl.shop.order.model.GoodsOrder;

public interface GoodsOrderMapper extends Mapper<GoodsOrder> {

	 void saveOrder(GoodsOrder goodsOrder);
}