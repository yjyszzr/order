package com.dl.shop.order.web;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dl.base.result.BaseResult;
import com.dl.base.result.ResultGenerator;
import com.dl.base.util.SessionUtil;
import com.dl.order.param.DateStrParam;
import com.dl.shop.order.service.OrderDetailService;

import io.swagger.annotations.ApiOperation;

/**
* Created by CodeGenerator on 2018/03/26.
*/
@RestController
@RequestMapping("/order/detail")
public class OrderDetailController {
	
    @Resource
    private OrderDetailService orderDetailService;
    
	/**
	 * 查询 用户当天所下的订单 包含某天的比赛ID集合
	 * @param dateStr
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "查询 用户当天所下的订单 包含某天的比赛ID集合 ", notes = "查询 用户当天所下的订单 包含某天的比赛ID集合")
    @PostMapping("/selectMatchIdsInSomeDayOrder")
	public BaseResult<List<String>> selectMatchIdsInSomeDayOrder(@Valid @RequestBody DateStrParam dateStrParam){
		Integer userId = SessionUtil.getUserId();
		List<String> matchIdList = orderDetailService.selectMatchIdsInSomeDayOrder(dateStrParam.getDateStr(), userId);
		if(CollectionUtils.isEmpty(matchIdList)) {
			return ResultGenerator.genSuccessResult("查询 用户当天所下的订单 包含某天的比赛ID集合成功 ", new ArrayList<String>());
		}
		return ResultGenerator.genSuccessResult("查询 用户当天所下的订单 包含某天的比赛ID集合成功 ", matchIdList);
    }
	
}
