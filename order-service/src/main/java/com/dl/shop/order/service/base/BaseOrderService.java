package com.dl.shop.order.service.base;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dl.base.model.UserDeviceInfo;
import com.dl.base.result.BaseResult;
import com.dl.base.service.AbstractService;
import com.dl.member.api.ISysConfigService;
import com.dl.member.dto.SysConfigDTO;
import com.dl.member.param.SysConfigParam;
import com.dl.order.dto.OrderAppendInfoDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BaseOrderService{
	@Resource
	private ISysConfigService sysCfgService;
	
	
	protected List<OrderAppendInfoDTO> getOrderDetailAppendInfo(UserDeviceInfo userDevice){
		log.info("[getOrderDetailAppendInfo]" + "");
		List<OrderAppendInfoDTO> appendInfoList = new ArrayList<OrderAppendInfoDTO>();
		//是否展示店铺入口
		int showStore = 0;
		if(userDevice != null) {
			log.info("[getOrderDetailAppendInfo]" + " channel:" + userDevice.getChannel());
		}
		if(userDevice != null && !"h5".equals(userDevice.getChannel())) {
			SysConfigParam sysCfgParams = new SysConfigParam();
			sysCfgParams.setBusinessId(21);
			BaseResult<SysConfigDTO> baseR = sysCfgService.querySysConfig(sysCfgParams);
			if(baseR != null && baseR.isSuccess() && baseR.getData() != null) {
				BigDecimal val = baseR.getData().getValue();
				showStore = val.intValue();
				OrderAppendInfoDTO appendDto = new OrderAppendInfoDTO();
				log.info("[getOrderDetailAppendInfo]" + " showStore:" + showStore);
				if(showStore == 1){
					appendDto.setType("0");
					appendDto.setImgurl("http://static.jinngu.club/uploadImgs/20180913/money_@2.gif");
					appendDto.setPhone("");
					appendDto.setPushurl("");
					appendDto.setWechat("");
					appendInfoList.add(appendDto);
				}else{
					sysCfgParams.setBusinessId(48);//是否展示店主信息
					BaseResult<SysConfigDTO> baseShop = sysCfgService.querySysConfig(sysCfgParams);
					if(baseShop.getCode() == 0){
						SysConfigDTO shopSysDto = baseShop.getData();
						if(shopSysDto.getValue().intValue() == 1){
							appendDto.setType("1");
							appendDto.setImgurl("https://szcq-biz.oss-cn-beijing.aliyuncs.com/20181212184220.png");
							appendDto.setPhone("17718518356");
							appendDto.setPushurl("");
							appendDto.setWechat("xiancaipaidian");
							appendInfoList.add(appendDto);
						}
					}
				}
			}
		}
		return appendInfoList;
	}
}
