package com.dl.order.api;

import java.util.List;
import javax.validation.Valid;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.dl.base.result.BaseResult;
import com.dl.order.dto.IssueDTO;
import com.dl.order.param.DateStrParam;

@FeignClient(value="order-service")
public interface IOrderDetailService {

	/**
	 * 查询 用户当天所下的订单 包含某天的比赛ID集合
	 * @param dateStr
	 * @param userId
	 * @return
	 */
    @PostMapping("/order/detail/selectMatchIdsInSomeDayOrder")
	public BaseResult<List<String>> selectMatchIdsInSomeDayOrder(@Valid @RequestBody DateStrParam dateStrParam);
    
}
