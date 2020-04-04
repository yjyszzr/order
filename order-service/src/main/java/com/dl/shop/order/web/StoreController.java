package com.dl.shop.order.web;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dl.base.result.BaseResult;
import com.dl.base.result.ResultGenerator;
import com.dl.base.util.DateUtil;
import com.dl.base.util.SessionUtil;
import com.dl.member.api.ISysConfigService;
import com.dl.member.dto.SysConfigDTO;
import com.dl.member.param.StrParam;
import com.dl.member.param.SysConfigParam;
import com.dl.order.dto.StoreDetailDTO;
import com.dl.order.dto.StoreInfoDTO;
import com.dl.order.dto.StoreListItemDTO;
import com.dl.order.param.StoreDetailParam;
import com.dl.shop.order.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/***
 * 店铺操作
 * @date 2018.11.27
 */
@Api(value="店铺相关",hidden=true)
@Slf4j
@RestController
@RequestMapping("/store")
public class StoreController {
	@Resource
	private StoreService storeService;
	@Resource
	private ISysConfigService sysCfgService;
	
	@ApiOperation(value = "店铺列表", notes = "店铺列表")
    @PostMapping("/storelist")
    public BaseResult<StoreInfoDTO> listAll(@Valid @RequestBody StrParam param) {
		Integer userId = SessionUtil.getUserId();
		String token = SessionUtil.getToken();
		int curTime = DateUtil.getCurrentTimeLong();
		log.info("[listAll]" + " userId:" + userId + " token:" + token + " curTime:" + curTime);
		List<StoreListItemDTO> rList = storeService.listAll(userId,token,curTime);
		StoreInfoDTO storeInfo = new StoreInfoDTO();
		storeInfo.setList(rList);
		SysConfigParam sysCfgParams = new SysConfigParam();
		sysCfgParams.setBusinessId(25);
		BaseResult<SysConfigDTO> bR = sysCfgService.querySysConfig(sysCfgParams);
		if(bR != null && bR.isSuccess() && bR.getData() != null) {
			SysConfigDTO sysCfgDTO = bR.getData();
			String protocalUrl = sysCfgDTO.getValueTxt();
			BigDecimal val = sysCfgDTO.getValue();
			if(val != null && val.intValue() > 0) {
				storeInfo.setProtocalUrl(protocalUrl);
			}
		}
		return ResultGenerator.genSuccessResult("succ",storeInfo);
    }
	
	@ApiOperation(value = "店铺详情", notes = "店铺详情")
    @PostMapping("/storedetail")
	public BaseResult<StoreDetailDTO> storeDetail(@RequestBody StoreDetailParam params){
		String token = SessionUtil.getToken();
		int curTime = DateUtil.getCurrentTimeLong();
		log.info("[storeDetail]" + " token:" + token + " curTime:" + curTime);
		return storeService.storeDetail(params.getId(),token,curTime);
	}
}
