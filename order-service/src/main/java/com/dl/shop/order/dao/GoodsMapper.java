package com.dl.shop.order.dao;

import java.util.List;

import com.dl.base.mapper.Mapper;
import com.dl.shop.order.model.Goods;

public interface GoodsMapper extends Mapper<Goods> {

	List<Goods> findGoodsList();
}