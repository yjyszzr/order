package com.dl.order.api;

import com.dl.base.param.EmptyParam;
import com.dl.base.result.BaseResult;
import com.dl.order.dto.*;
import com.dl.order.param.*;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@FeignClient(value="order-service")
public interface IOrderService {

	@RequestMapping(path="/order/createOrder", method=RequestMethod.POST)
	public BaseResult<OrderDTO> createOrder(@Valid @RequestBody SubmitOrderParam param);
	
	@RequestMapping(path="/order/updateOrderToOrderSn", method=RequestMethod.POST)
	public BaseResult<OrderDTO> updateOrderToOrderSn(@RequestBody SubmitOrderParam param);
	
	@RequestMapping(path="/order/updateOrderInfo", method=RequestMethod.POST)
	public BaseResult<String> updateOrderInfo(@Valid @RequestBody UpdateOrderInfoParam param);
	
	@RequestMapping(path="/order/getOrderInfoByOrderSn", method=RequestMethod.POST)
	public BaseResult<OrderDTO> getOrderInfoByOrderSn(@Valid @RequestBody OrderSnParam snParam);


	@RequestMapping(path="/order/getOrderInfoByMerchantOrderSn", method=RequestMethod.POST)
	public BaseResult<OrderDTO> getOrderInfoByMerchantOrderSn(@Valid @RequestBody MerchantOrderSnParam snParam);

	@RequestMapping(path="/order/getOrderTicketDatasByOrderSn", method=RequestMethod.POST)
	public BaseResult<String> getOrderTicketDatasByOrderSn(@Valid @RequestBody OrderSnParam snParam);
	
	@RequestMapping(path="/order/updateOrderInfoByPrint", method=RequestMethod.POST)
    public BaseResult<String> updateOrderInfoByPrint(@Valid @RequestBody List<LotteryPrintParam> lotteryPrintParams);
	
	@RequestMapping(path="/order/updateOrderInfoByExchangeReward", method=RequestMethod.POST)
    public BaseResult<Integer> updateOrderInfoByExchangeReward(@Valid @RequestBody LotteryPrintMoneyParam param);
	
	@RequestMapping(path="/order/getOrderWithUserAndMoney", method=RequestMethod.POST)
    public BaseResult<List<OrderWithUserDTO>> getOrderWithUserAndMoney(@Valid @RequestBody OrderWithUserParam param);
	
	@RequestMapping(path="/order/getOrderWithDetailByOrderSn", method=RequestMethod.POST)
    public BaseResult<OrderInfoAndDetailDTO> getOrderWithDetailByOrderSn(@Valid @RequestBody OrderSnParam param);
	
	@RequestMapping(path="/order/orderSnListGoPrintLottery", method=RequestMethod.POST)
	public BaseResult<List<String>> orderSnListGoPrintLottery(@RequestBody OrderSnListGoPrintLotteryParam param);
	
	@RequestMapping(path="/order/updateOrderInfoByMatchResult", method=RequestMethod.POST)
	public BaseResult<String> updateOrderInfoByMatchResult(@Valid @RequestBody LotteryPrintRewardParam param);
	
	@RequestMapping(path="/order/queryOrderListByCondition", method=RequestMethod.POST)
	public BaseResult<List<OrderDTO>> queryOrderListByCondition(@Valid @RequestBody OrderCondtionParam param);
	
	@RequestMapping(path="/order/queryOrderSnListByStatus", method=RequestMethod.POST)
	public BaseResult<List<String>> queryOrderSnListByStatus(@Valid @RequestBody OrderQueryParam param);

	@RequestMapping(path="/order/updateOrderStatusRewarded", method=RequestMethod.POST)
	public BaseResult<Integer> updateOrderStatusRewarded(@Valid @RequestBody OrderSnListParam param);
	
	@RequestMapping(path="/order/updateOrderInfoStatus", method=RequestMethod.POST)
	public BaseResult<String> updateOrderInfoStatus(@Valid @RequestBody UpdateOrderInfoParam updateOrderInfoParam);
	
	@RequestMapping(path="/order/updateOrderMatchResult", method=RequestMethod.POST)
	public BaseResult<String> updateOrderMatchResult(@RequestBody EmptyParam emptyParam);
	
	@RequestMapping(path="/order/refreshOrderPrintStatus", method=RequestMethod.POST)
	public BaseResult<String> refreshOrderPrintStatus(@RequestBody EmptyParam emptyParam);
	
	@RequestMapping(path="/order/addRewardMoneyToUsers", method=RequestMethod.POST)
	public BaseResult<String> addRewardMoneyToUsers(@RequestBody EmptyParam emptyParam);

	@RequestMapping(path="/order/updateOrderStatusAnother", method=RequestMethod.POST)
	public BaseResult<Integer> updateOrderStatusAnother(@Valid @RequestBody UpdateOrderStatusByAnotherStatusParam param);
	
	@RequestMapping(path="/order/updateOrderPayStatus", method=RequestMethod.POST)
	public BaseResult<Integer> updateOrderPayStatus(@Valid @RequestBody UpdateOrderPayStatusParam param);
	
	@RequestMapping(path="/order/getUserMoneyPay", method=RequestMethod.POST)
	public BaseResult<GetUserMoneyDTO> getUserMoneyPay(@Valid @RequestBody GetUserMoneyPayParam param);

	@RequestMapping(path="/order/getManualOrderList", method=RequestMethod.POST)
    public BaseResult<List<ManualOrderDTO>> getManualOrderList(@Valid @RequestBody OrderSnListParam param);
	
	@RequestMapping(path="/order/getManualLottoOrderDetail", method=RequestMethod.POST)
    public BaseResult<ManualLottoOrderDetailDTO> getManualLottoOrderList(@Valid @RequestBody OrderSnListParam param);
	
	@RequestMapping(path="/order/saveOrderPicByOrderSn", method=RequestMethod.POST)
    public BaseResult<String> saveOrderPicByOrderSn(@Valid @RequestBody OrderPicParam param);

	@RequestMapping(path="/order", method=RequestMethod.POST)
	public  BaseResult<OrderDetailDTO> getOrderDetail(OrderDetailParam param);

	@RequestMapping(path="/order/getOrderInfoListForStoreProject", method=RequestMethod.POST)
	public  BaseResult<PageInfo<OrderInfoListDTO>> getOrderInfoListForStoreProject(OrderInfoListParam param);

	@RequestMapping(path="/order/getTicketScheme", method=RequestMethod.POST)
	public BaseResult<TicketSchemeDTO> getTicketScheme(TicketSchemeParam param);

	@RequestMapping(path="/order/getOrderInfoByOrderId", method=RequestMethod.POST)
	public BaseResult<OrderDTO> getOrderInfoByOrderId(OrderIdParam snParam);

	@RequestMapping(path="/order/getLottoOrderDetailSimulatByStore", method=RequestMethod.POST)
	public BaseResult<LottoOrderDetailDTO> getLottoOrderDetailSimulatByStore(@Valid @RequestBody OrderDetailParam param);
	
	@RequestMapping(path="/order/getLottoTicketScheme", method=RequestMethod.POST)
	public BaseResult<LottoTicketSchemeDTO> getLottoTicketScheme(@Valid @RequestBody TicketSchemeParam param);
	
    @RequestMapping(path="/order/getOrderDetailByShare", method=RequestMethod.POST)
    public BaseResult<OrderShareDTO> getOrderDetailByShare(@Valid @RequestBody OrderDetailByOrderSnPara param);
}
