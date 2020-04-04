package com.dl.shop.order.service;
import com.dl.shop.order.model.Goods;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

import com.dl.shop.order.dao.GoodsMapper;
import com.dl.base.service.AbstractService;
import com.dl.order.dto.GoodsDTO;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

@Slf4j
@Service
@Transactional("transactionManager1")
public class GoodsService extends AbstractService<Goods> {
    @Resource
    private GoodsMapper goodsMapper;

	public PageInfo<GoodsDTO> findGoodsList() {
		List<Goods> goodsList= goodsMapper.findGoodsList();
		PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);
		if (null == goodsList) {
			return new PageInfo<GoodsDTO>();
		}
		PageInfo<GoodsDTO> result = new PageInfo<GoodsDTO>();
		List<GoodsDTO> goodsDTOList= new ArrayList<GoodsDTO>();
		for (int i = 0; i < goodsList.size(); i++) {
			GoodsDTO goodsDTO=new GoodsDTO();
			Goods goods =goodsList.get(i); 
			goodsDTO.setGoodsId(goodsList.get(i).getId().toString());
			goodsDTO.setDescription(goods.getDescription());
			String historyPrice = goods.getHistoryPrice().toString();
			goodsDTO.setHistoryPrice(historyPrice.replaceAll(".00", ""));
			goodsDTO.setMainPic(goods.getMainPic());
			goodsDTO.setPaidNum(goods.getPaidNum().toString());
			String presentPrice = goods.getPresentPrice().toString();
			goodsDTO.setPresentPrice(presentPrice.replaceAll(".00", ""));
			goodsDTOList.add(goodsDTO);
		}
		try {
			BeanUtils.copyProperties(pageInfo, result);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		result.setList(goodsDTOList);
		return result;
	}

}
