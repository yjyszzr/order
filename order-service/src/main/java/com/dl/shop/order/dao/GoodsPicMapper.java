package com.dl.shop.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dl.base.mapper.Mapper;
import com.dl.shop.order.model.GoodsPic;

public interface GoodsPicMapper extends Mapper<GoodsPic> {

	List<GoodsPic> findByGoodsId(@Param("goodsId")Integer goodsId);
}