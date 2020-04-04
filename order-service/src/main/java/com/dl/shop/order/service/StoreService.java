package com.dl.shop.order.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.dl.base.result.BaseResult;
import com.dl.base.result.ResultGenerator;
import com.dl.base.service.AbstractService;
import com.dl.member.api.ISysConfigService;
import com.dl.member.dto.SysConfigDTO;
import com.dl.member.param.SysConfigParam;
import com.dl.order.dto.StoreDetailDTO;
import com.dl.order.dto.StoreListItemDTO;
import com.dl.shop.order.dao.StoreMapper;
import com.dl.shop.order.model.Store;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional("transactionManager1")
public class StoreService extends AbstractService<Store>{
	@Resource
	private StoreMapper storeMapper;
	@Resource
	private ISysConfigService sysConfigService;
	@Resource
	private DlUserAuthsService userAuthService;
	
	/**
	 * 校验参数及是否可以创建订单
	 * @param param
	 */
	public List<StoreListItemDTO> listAll(Integer userId,String token,Integer time){
//		boolean isBind = false;
		String url = null;
//		if(userId != null && userId > 0) {
//			isBind = userAuthService.queryBindThird(userId);
//		}
		SysConfigParam sysCfgParams = new SysConfigParam();
		sysCfgParams.setBusinessId(49);
		BaseResult<SysConfigDTO> bResult = sysConfigService.querySysConfig(sysCfgParams);
		if(bResult != null && bResult.getData() != null) {
			url = bResult.getData().getValueTxt();
//			if(isBind) {
//				url = url + "/lottery";//进入到主页面
//			}
		}
		log.info("[storeDetail]" + "userId:" + userId + " url:" + url);
		List<Store> rList = storeMapper.selectAll();
		List<StoreListItemDTO> mList = new ArrayList<StoreListItemDTO>();
		if(rList != null && rList.size() > 0) {
			for(int i = 0;i < rList.size();i++) {
				Store store = rList.get(i);
				Integer storeId = store.getStoreId();
				StoreListItemDTO storeDTO = new StoreListItemDTO();
				storeDTO.setCollNum(store.getCollNum());
				storeDTO.setCooperAuth(store.getCooperAuth());
				storeDTO.setLogo(store.getLogo());
				storeDTO.setName(store.getName());
				storeDTO.setStoreId(store.getStoreId());
				String jumpUrl = buildJumpUrl(url, storeId, token, time);
				storeDTO.setJumpUrl(jumpUrl);
				mList.add(storeDTO);
			}
		}
		return mList;
	}
	
	
	/**
	 * 店铺详情
	 * @param id
	 * @return
	 */
	public BaseResult<StoreDetailDTO> storeDetail(int storeId,String token,int time){
		StoreDetailDTO storeDetail = null;
		Store store = new Store();
		store.setStoreId(storeId);
		Store rStore = storeMapper.selectOne(store);
		if(rStore != null) {
			storeDetail = new StoreDetailDTO();
			storeDetail.setBizPermit(rStore.getBizPermit());
			storeDetail.setCollNum(rStore.getCollNum());
			storeDetail.setCooperAuth(rStore.getCooperAuth());
			storeDetail.setStoreId(storeId);
			storeDetail.setImgWechat(rStore.getImgWechat());
			storeDetail.setLogo(rStore.getLogo());
			storeDetail.setName(rStore.getName());
			storeDetail.setWechat(rStore.getWechat());
			storeDetail.setBizPermitUrl(rStore.getBizPermitUrl());
			SysConfigParam sysCfgParams = new SysConfigParam();
			sysCfgParams.setBusinessId(49);
			BaseResult<SysConfigDTO> bResult = sysConfigService.querySysConfig(sysCfgParams);
			String url = null;
			if(bResult != null && bResult.getData() != null) {
				url = bResult.getData().getValueTxt();
			}
			log.info("[storeDetail]" + " url:" + url);
			url = buildJumpUrl(url, storeId, token, time);
			log.info("[storeDetail]" + " jump url:" + url);
			storeDetail.setJumpUrl(url);
		}
		return ResultGenerator.genSuccessResult("succ", storeDetail);
	}
	
	private String buildJumpUrl(String url,int storeId,String token,int time) {
		StringBuilder builder = new StringBuilder();
		builder.append(url+"?");
		builder.append("storeId=" + storeId + "&");
		if(!StringUtils.isEmpty(token)) {
			builder.append("token=" + token + "&");
		}
		builder.append("_t=" + time);
		return builder.toString();
	}
}
