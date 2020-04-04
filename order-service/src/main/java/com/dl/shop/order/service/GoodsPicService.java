package com.dl.shop.order.service;
import com.dl.shop.order.model.GoodsPic;
import com.dl.shop.order.dao.GoodsPicMapper;
import com.dl.base.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

@Service
@Transactional("transactionManager1")
public class GoodsPicService extends AbstractService<GoodsPic> {
    @Resource
    private GoodsPicMapper goodsPicMapper;

	public List<GoodsPic> findByGoodsId(Integer goodsId) {
		return goodsPicMapper.findByGoodsId(goodsId);
	}

}
