package com.dl.shop.order.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dl.base.result.BaseResult;
import com.dl.base.result.ResultGenerator;
import com.dl.base.service.AbstractService;
import com.dl.order.dto.ServDTO;
import com.dl.shop.order.dao.ServMapper;
import com.dl.shop.order.model.Serv;
import com.dl.shop.order.model.Store;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional("transactionManager1")
public class ServService extends AbstractService<Store>{
	@Resource
	private ServMapper servMapper;
	
	public BaseResult<List<ServDTO>> listAll(){
		List<ServDTO> rList = new ArrayList<ServDTO>();
		List<Serv> mList = servMapper.selectAll();
		if(mList != null && mList.size() > 0) {
			for(Serv item : mList) {
				if(item != null && item.getIsShow() != null && item.getIsShow() == 1) {
					ServDTO serv = new ServDTO();
					serv.setServId(item.getServId());
					serv.setLogo(item.getLogo());
					serv.setName(item.getName());
					serv.setUrl(item.getUrl());
					rList.add(serv);	
				}
			}
		}
		return ResultGenerator.genSuccessResult("succ",rList);
	}
}
