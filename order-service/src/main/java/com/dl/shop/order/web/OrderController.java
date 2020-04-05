package com.dl.shop.order.web;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dl.base.param.EmptyParam;
import com.dl.base.result.BaseResult;
import com.dl.base.result.ResultGenerator;
import com.dl.lottery.api.ILotteryPrintService;
import com.dl.order.dto.GetUserMoneyDTO;
import com.dl.order.dto.LottoOrderDetailDTO;
import com.dl.order.dto.LottoTicketSchemeDTO;
import com.dl.order.dto.ManualLottoOrderDetailDTO;
import com.dl.order.dto.ManualOrderDTO;
import com.dl.order.dto.ManualOrderDetailDTO;
import com.dl.order.dto.OrderDTO;
import com.dl.order.dto.OrderDetailDTO;
import com.dl.order.dto.OrderInfoAndDetailDTO;
import com.dl.order.dto.OrderInfoListDTO;
import com.dl.order.dto.OrderShareDTO;
import com.dl.order.dto.OrderWithUserDTO;
import com.dl.order.dto.TicketSchemeDTO;
import com.dl.order.param.GetUserMoneyPayParam;
import com.dl.order.param.LotteryPrintMoneyParam;
import com.dl.order.param.LotteryPrintParam;
import com.dl.order.param.LotteryPrintRewardParam;
import com.dl.order.param.MerchantOrderSnParam;
import com.dl.order.param.OrderCondtionParam;
import com.dl.order.param.OrderDetailByOrderSnPara;
import com.dl.order.param.OrderDetailParam;
import com.dl.order.param.OrderIdParam;
import com.dl.order.param.OrderInfoListParam;
import com.dl.order.param.OrderPicParam;
import com.dl.order.param.OrderQueryParam;
import com.dl.order.param.OrderSnListGoPrintLotteryParam;
import com.dl.order.param.OrderSnListParam;
import com.dl.order.param.OrderSnParam;
import com.dl.order.param.OrderWithUserParam;
import com.dl.order.param.SubmitOrderParam;
import com.dl.order.param.TicketSchemeParam;
import com.dl.order.param.UpdateOrderInfoParam;
import com.dl.order.param.UpdateOrderPayStatusParam;
import com.dl.order.param.UpdateOrderStatusByAnotherStatusParam;
import com.dl.shop.order.model.Order;
import com.dl.shop.order.service.OrderLottoService;
import com.dl.shop.order.service.OrderService;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Resource
	private OrderService orderService;
	@Resource
	private ILotteryPrintService lotteryPrintService;
    @Resource
    private OrderLottoService lottoService;


    
	@ApiOperation(value = "准备出票的订单列表", notes = "准备出票的订单列表")
	@PostMapping("/orderSnListGoPrintLottery")
	public BaseResult<List<String>> orderSnListGoPrintLottery(@RequestBody OrderSnListGoPrintLotteryParam param) {
		List<String> orderSnListGoPrintLottery = orderService.orderSnListGoPrintLottery();
		return ResultGenerator.genSuccessResult("success", orderSnListGoPrintLottery);
	}
	
	@ApiOperation(value = "创建订单", notes = "创建订单")
    @PostMapping("/createOrder")
    public BaseResult<OrderDTO> createOrder(@Valid @RequestBody SubmitOrderParam param) {
		return orderService.createOrder(param);
    }
	@ApiOperation(value = "修改订单编号", notes = "创建订单")
    @PostMapping("/updateOrderToOrderSn")
    public BaseResult<OrderDTO> updateOrderToOrderSn(@RequestBody SubmitOrderParam param) {
		return orderService.updateOrderToOrderSn(param);
    }
	@ApiOperation(value = "支付成功修改订单信息", notes = "支付成功修改订单信息,同时生成预出票信息")
    @PostMapping("/updateOrderInfo")
    public BaseResult<String> updateOrderInfo(@Valid @RequestBody UpdateOrderInfoParam param) {
		BaseResult<String> updateOrderInfo = orderService.updateOrderInfo(param);
		return updateOrderInfo;
    }
	
	@ApiOperation(value = "根据订单状态查询订单列表", notes = "根据订单状态查询订单列表")
    @PostMapping("/getOrderInfoList")
    public BaseResult<PageInfo<OrderInfoListDTO>> getOrderInfoList(@Valid @RequestBody OrderInfoListParam param) {
		PageInfo<OrderInfoListDTO> orderInfoListDTOs = orderService.getOrderInfoList(param);
		return ResultGenerator.genSuccessResult("订单列表查询成功", orderInfoListDTOs);
    }
	
	
	@ApiOperation(value = "根据订单状态查询订单列表", notes = "根据订单状态查询订单列表")
	@PostMapping("/getOrderInfoListForStoreProject")
	public BaseResult<PageInfo<OrderInfoListDTO>> getOrderInfoListForStoreProject(@Valid @RequestBody OrderInfoListParam param) {
		PageInfo<OrderInfoListDTO> orderInfoListDTOs = orderService.getOrderInfoListForStoreProject(param);
		return ResultGenerator.genSuccessResult("订单列表查询成功", orderInfoListDTOs);
	}
	
	@ApiOperation(value = "新的根据订单状态查询订单列表", notes = "根据订单状态查询订单列表")
    @PostMapping("/ngetOrderInfoList")
    public BaseResult<PageInfo<OrderInfoListDTO>> ngetOrderInfoList(@Valid @RequestBody OrderInfoListParam param) {
		PageInfo<OrderInfoListDTO> orderInfoListDTOs = orderService.ngetOrderInfoList(param);
		return ResultGenerator.genSuccessResult("订单列表查询成功", orderInfoListDTOs);
    }
	
	@ApiOperation(value = "新的根据订单状态查询订单列表", notes = "根据订单状态查询订单列表")
    @PostMapping("/ngetOrderInfoListV2")
    public BaseResult<PageInfo<OrderInfoListDTO>> ngetOrderInfoListV2(@Valid @RequestBody OrderInfoListParam param) {
		PageInfo<OrderInfoListDTO> orderInfoListDTOs = orderService.ngetOrderInfoListV2(param);
		return ResultGenerator.genSuccessResult("订单列表查询成功", orderInfoListDTOs);
    }
	
	@ApiOperation(value = "查询订单详情", notes = "查询订单详情")
    @PostMapping("/getOrderDetail")
    public BaseResult<OrderDetailDTO> getOrderDetail(@Valid @RequestBody OrderDetailParam param) {
		OrderDetailDTO orderDetailDTO = orderService.getOrderDetail(param);
		return ResultGenerator.genSuccessResult("订单详情查询成功", orderDetailDTO);
    }
	
	@ApiOperation(value = "根据ordersn查询订单详情", notes = "根据ordersn查询订单详情")
    @PostMapping("/getOrderDetailByOrderSn")
    public BaseResult<OrderDetailDTO> getOrderDetailByOrderSn(@Valid @RequestBody OrderDetailByOrderSnPara param) {
		OrderDetailDTO orderDetailDTO = orderService.getOrderDetailByOrderSn(param);
		return ResultGenerator.genSuccessResult("订单详情查询成功", orderDetailDTO);
    }
	
	@ApiOperation(value = "根据ordersn查询订单详情", notes = "根据ordersn查询订单详情")
    @PostMapping("/getOrderDetailByShare")
    public BaseResult<OrderShareDTO> getOrderDetailByShare(@Valid @RequestBody OrderDetailByOrderSnPara param) {
		OrderShareDTO orderDetailDTO = orderService.getOrderShareByOrderSn(param);
		return ResultGenerator.genSuccessResult("订单详情查询成功", orderDetailDTO);
	}
	
	@ApiOperation(value = "查询手工出票的订单列表", notes = "查询手工出票的订单列表")
    @PostMapping("/getManualOrderList")
    public BaseResult<List<ManualOrderDTO>> getManualOrderList(@Valid @RequestBody OrderSnListParam param) {
		List<ManualOrderDTO> orderDetailDTO = orderService.getManualOrderList(param);
		return ResultGenerator.genSuccessResult("订单详情查询成功", orderDetailDTO);
    }
	
	@ApiOperation(value = "查询手工出票的乐透订单详情", notes = "查询手工出票的乐透订单详情")
    @PostMapping("/getManualLottoOrderDetail")
    public BaseResult<ManualLottoOrderDetailDTO> getManualLottoOrderList(@Valid @RequestBody OrderSnListParam param) {
		List<String> list = param.getOrderSnlist();
		log.info("[getManualLottoOrderList]" + " list:" + list);
		if(list == null || list.size() <= 0) {
			return ResultGenerator.genFailResult("list参数为空");
		}
		String orderSn = list.get(0);
		ManualLottoOrderDetailDTO lottoDto = lottoService.getManualLottoOrderList(orderSn);
		return ResultGenerator.genSuccessResult("订单详情查询成功",lottoDto);
    }
	
	
	@ApiOperation(value = "查询手工出票的记录详情", notes = "查询手工出票的记录详情")
    @PostMapping("/getManualOrderDetail")
    public BaseResult<ManualOrderDetailDTO> getManualOrderDetail(@Valid @RequestBody OrderSnParam param) {
		ManualOrderDetailDTO dto = orderService.getManualOrderDetail(param.getOrderSn());
		return ResultGenerator.genSuccessResult("订单详情查询成功", dto);
    }
	
	@ApiOperation(value = "查询篮彩订单详情", notes = "查询订单详情")
    @PostMapping("/getBasketBallOrderDetail")
    public BaseResult<OrderDetailDTO> getBasketBallOrderDetail(@Valid @RequestBody OrderDetailParam param) {
		OrderDetailDTO orderDetailDTO = orderService.getBasketBallOrderDetail(param);
		return ResultGenerator.genSuccessResult("订单详情查询成功", orderDetailDTO);
    }
	
	@ApiOperation(value = "查询大乐透订单详情", notes = "查询大乐透订单详情")
	@PostMapping("/getLottoOrderDetail")
	public BaseResult<LottoOrderDetailDTO> getLottoOrderDetail(@Valid @RequestBody OrderDetailParam param) {
		LottoOrderDetailDTO dto = lottoService.getLottoOrderDetail(param);
		return ResultGenerator.genSuccessResult("订单详情查询成功", dto);
	}
	
	@ApiOperation(value = "查询大乐透模拟订单详情", notes = "查询大乐透模拟订单详情")
	@PostMapping("/getLottoOrderDetailSimulat")
	public BaseResult<LottoOrderDetailDTO> getLottoOrderDetailSimulat(@Valid @RequestBody OrderDetailParam param) {
		log.info("[getLottoOrderDetailSimulat]" + " orderId:" + param.getOrderId());
		LottoOrderDetailDTO dto = lottoService.getLottoOrderDetailSimulate(param,false);
		return ResultGenerator.genSuccessResult("订单详情查询成功", dto);
	}
	
	@ApiOperation(value = "查询大乐透模拟订单详情", notes = "查询大乐透模拟订单详情")
	@PostMapping("/getLottoOrderDetailSimulatByStore")
	public BaseResult<LottoOrderDetailDTO> getLottoOrderDetailSimulatByStore(@Valid @RequestBody OrderDetailParam param) {
		log.info("[getLottoOrderDetailSimulat]" + " orderId:" + param.getOrderId());
		LottoOrderDetailDTO dto = lottoService.getLottoOrderDetailSimulate(param,true);
		return ResultGenerator.genSuccessResult("订单详情查询成功", dto);
	}
	
	
	@ApiOperation(value = "根据订单Id查询订单信息", notes = "根据订单Id查询订单信息")
	@PostMapping("/getOrderInfoByOrderId")
	public BaseResult<OrderDTO> getOrderInfoByOrderId(@Valid @RequestBody OrderIdParam param) {
		Order  order = orderService.getOrderInfoByOrderId(param.getOrderId());
		OrderDTO dto=new OrderDTO();
		dto.setStoreId(order.getStoreId());
		dto.setOrderSn(order.getOrderSn());
		return ResultGenerator.genSuccessResult("订单信息查询成功", dto);
	}
	
	@ApiOperation(value = "查询出票方案", notes = "查询出票方案")
    @PostMapping("/getTicketScheme")
    public BaseResult<TicketSchemeDTO> getTicketScheme(@Valid @RequestBody TicketSchemeParam param) {
		TicketSchemeDTO ticketSchemeDTO = orderService.getTicketScheme(param,null);
		return ResultGenerator.genSuccessResult("出票方案查询成功", ticketSchemeDTO);
    }
	
	@ApiOperation(value = "查询篮球出票方案", notes = "查询篮球出票方案")
    @PostMapping("/getBasketBallTicketScheme")
    public BaseResult<TicketSchemeDTO> getBasketBallTicketScheme(@Valid @RequestBody TicketSchemeParam param) {
		TicketSchemeDTO ticketSchemeDTO = orderService.getBasketBallTicketScheme(param);
		return ResultGenerator.genSuccessResult("出票方案查询成功", ticketSchemeDTO);
    }
	
	@ApiOperation(value = "查询大乐透出票方案", notes = "查询大乐透出票方案")
	@PostMapping("/getLottoTicketScheme")
	public BaseResult<LottoTicketSchemeDTO> getLottoTicketScheme(@Valid @RequestBody TicketSchemeParam param) {
		LottoTicketSchemeDTO dto = lottoService.getLottoTicketScheme(param);
		return ResultGenerator.genSuccessResult("出票方案查询成功", dto);
	}
	
	@ApiOperation(value = "根据订单编号查询订单数据", notes = "根据订单编号查询订单数据")
    @PostMapping("/getOrderInfoByOrderSn")
    public BaseResult<OrderDTO> getOrderInfoByOrderSn(@Valid @RequestBody OrderSnParam snParam) {
		OrderDTO orderDTO = orderService.getOrderInfoByOrderSn(snParam);
		return ResultGenerator.genSuccessResult("查询订单成功", orderDTO);
    }

	@ApiOperation(value = "根据商户订单编号查询订单数据", notes = "根据商户订单编号查询订单数据")
	@PostMapping("/getOrderInfoByMerchantOrderSn")
	public BaseResult<OrderDTO> getOrderInfoByMerchantOrderSn(@Valid @RequestBody MerchantOrderSnParam snParam) {
		OrderDTO orderDTO = orderService.getOrderInfoByMerchantOrderSn(snParam.getMerchantOrderSn(),snParam.getMerchant());
		return ResultGenerator.genSuccessResult("查询订单成功", orderDTO);
	}

	@ApiOperation(value = "根订单编号查询订单详情中的多个ticketData", notes = "根订单编号查询订单详情中的多个ticketData")
	@PostMapping("/getOrderTicketDatasByOrderSn")
	public BaseResult<String> getOrderTicketDatasByOrderSn(@Valid @RequestBody OrderSnParam snParam) {
		String ticketDatas = orderService.queryTicketDtas(snParam.getOrderSn());
		return ResultGenerator.genSuccessResult("查询ticketDatas成功", ticketDatas);
	}

	@ApiOperation(value = "出票成功，修改订单数据", notes = "出票成功，修改订单数据")
    @PostMapping("/updateOrderInfoByPrint")
    public BaseResult<String> updateOrderInfoByPrint(@Valid @RequestBody List<LotteryPrintParam> lotteryPrintParams) {
		orderService.updateOrderInfoByPrint(lotteryPrintParams);
		return ResultGenerator.genSuccessResult("出票完成，修改订单数据成功");
    }
	
	@ApiOperation(value = "兑奖时，修改订单数据", notes = "兑奖时，修改订单数据")
    @PostMapping("/updateOrderInfoByExchangeReward")
    public BaseResult<Integer> updateOrderInfoByExchangeReward(@Valid @RequestBody LotteryPrintMoneyParam param) {
		int rst = orderService.updateOrderInfoByExchangeReward(param);
		return ResultGenerator.genSuccessResult("兑奖时，修改订单数据成功", rst);
    }
	
	@ApiOperation(value = "开奖后，修改订单数据", notes = "开奖后，修改订单数据")
    @PostMapping("/updateOrderInfoByMatchResult")
    public BaseResult<String> updateOrderInfoByMatchResult(@Valid @RequestBody LotteryPrintRewardParam param) {
		int rst = orderService.updateOrderInfoByMatchResult(param.getIssue());
		if(rst == 0) {
			return ResultGenerator.genFailResult();
		}
		return ResultGenerator.genSuccessResult("开奖后，修改订单数据成功");
    }
	
	@ApiOperation(value = "获取中奖用户及奖金", notes = "获取中奖用户及奖金")
    @PostMapping("/getOrderWithUserAndMoney")
    public BaseResult<List<OrderWithUserDTO>> getOrderWithUserAndMoney(@Valid @RequestBody OrderWithUserParam param) {
		List<OrderWithUserDTO> orderWithUserDTOs = orderService.getOrderWithUserAndMoney(param);
		return ResultGenerator.genSuccessResult("获取中奖用户及奖金成功", orderWithUserDTOs);
    }
	
	@ApiOperation(value = "根据订单编号查询订单及订单详情", notes = "根据订单编号查询订单及订单详情")
    @PostMapping("/getOrderWithDetailByOrderSn")
    public BaseResult<OrderInfoAndDetailDTO> getOrderWithDetailByOrderSn(@Valid @RequestBody OrderSnParam param) {
    	OrderInfoAndDetailDTO orderInfoAndDetailDTO = orderService.getOrderWithDetailByOrderSn(param);
		return ResultGenerator.genSuccessResult("根据订单编号查询订单及订单详情成功", orderInfoAndDetailDTO);
    }
	
	@ApiOperation(value = "根据订单状态和支付状态查询订单号", notes = "根据订单状态和支付状态查询订单号")
	@RequestMapping(path="/queryOrderSnListByStatus", method=RequestMethod.POST)
	public BaseResult<List<String>> queryOrderSnListByStatus(@Valid @RequestBody OrderQueryParam param){
		return orderService.queryOrderSnListByStatus(param);
	}
	
	@ApiOperation(value = "更新订单状态公共方法", notes = "更新订单状态公共方法")
	@RequestMapping(path="/updateOrderStatusRewarded", method=RequestMethod.POST)
	public BaseResult<Integer> updateOrderStatusRewarded(@Valid @RequestBody OrderSnListParam orderParamListParam){
		Integer rst = 0;//orderService.updateOrderStatusRewarded(orderParamListParam.getOrderSnlist()); 
		if(1 != rst) {
			return ResultGenerator.genFailResult("批量更新已派奖订单状态失败");
		}
		return ResultGenerator.genSuccessResult("批量更新已派奖订单状态 成功",rst);
	}	
	
	@ApiOperation(value = "根据某个订单状态更新订单状态为另一个状态", notes = "根据某个订单状态更新订单状态为另一个状态")
	@RequestMapping(path="/updateOrderStatusAnother", method=RequestMethod.POST)
	public BaseResult<Integer> updateOrderStatusAnother(@Valid @RequestBody UpdateOrderStatusByAnotherStatusParam param){
		Integer rst = orderService.updateOrderStatusByAnotherStatus(param.getOrderSnlist(),param.getOrderStatusAfter(),param.getOrderStatusBefore()); 
		if(1 != rst) {
			return ResultGenerator.genFailResult("更新订单状态失败");
		}
		return ResultGenerator.genSuccessResult("更新订单状态 成功",rst);
	}	
	
	@ApiOperation(value = "更新订单状态", notes = "更新订单状态")
	@RequestMapping(path="/updateOrderStatusByCondition", method=RequestMethod.POST)
	public BaseResult<String> updateOrderStatusByCondition(@Valid @RequestBody UpdateOrderInfoParam updateOrderInfoParam){
		return orderService.updateOrderStatusByCondition(updateOrderInfoParam); 
	}
	
	@ApiOperation(value = "根据查询条件查询订单集合", notes = "根据查询条件查询订单集合")
	@RequestMapping(path="/queryOrderListByCondition", method=RequestMethod.POST)
	public BaseResult<List<OrderDTO>> queryOrderListByCondition(@Valid @RequestBody OrderCondtionParam orderCondtionParam){
		return orderService.queryOrderByCondition(orderCondtionParam);
	}
	
	@ApiOperation(value = "更新订单状态", notes = "更新订单状态")
	@RequestMapping(path="/updateOrderInfoStatus", method=RequestMethod.POST)
	public BaseResult<String> updateOrderInfoStatus(@Valid @RequestBody UpdateOrderInfoParam updateOrderInfoParam){
		return orderService.updateOrderInfoStatus(updateOrderInfoParam);
	}

	@ApiOperation(value = "更新比赛结果,提供给定时任务用户", notes = "更新比赛结果,提供给定时任务用户")
	@RequestMapping(path="/updateOrderMatchResult", method=RequestMethod.POST)
	public BaseResult<String> updateOrderMatchResult(@Valid @RequestBody EmptyParam emptyParam){
		orderService.updateOrderMatchResult();
		return ResultGenerator.genSuccessResult("更新比赛结果 成功");
	}
	
	@ApiOperation(value = "订单出票结果 ,这里暂时主要处理出票失败的订单", notes = "订单出票结果 ,这里暂时主要处理出票失败的订单")
	@RequestMapping(path="/refreshOrderPrintStatus", method=RequestMethod.POST)
	public BaseResult<String> refreshOrderPrintStatus(@Valid @RequestBody EmptyParam emptyParam){
		orderService.refreshOrderPrintStatus();
		return ResultGenerator.genSuccessResult("订单出票结果 ,这里暂时主要处理出票失败的订单成功");
	}
	
	@ApiOperation(value = "订单出票结果 ,这里暂时主要处理出票失败的订单", notes = "订单出票结果 ,这里暂时主要处理出票失败的订单")
	@RequestMapping(path="/addRewardMoneyToUsers", method=RequestMethod.POST)
	public BaseResult<String> addRewardMoneyToUsers(@Valid @RequestBody EmptyParam emptyParam){
		orderService.addRewardMoneyToUsers();
		return ResultGenerator.genSuccessResult("更新中奖用户的账户成功");
	}
	
	@ApiOperation(value = "支付成功回调", notes = "支付成功回调")
	@RequestMapping(path="/updateOrderPayStatus", method=RequestMethod.POST)
	public BaseResult<Integer> updateOrderPayStatus(@Valid @RequestBody UpdateOrderPayStatusParam param){
		Integer rst = orderService.updateOrderPayStatus(param);
		return ResultGenerator.genSuccessResult("",rst);
	}
	
	@ApiOperation(value = "支付成功回调", notes = "支付成功回调")
	@RequestMapping(path="/getUserMoneyPay", method=RequestMethod.POST)
	public BaseResult<GetUserMoneyDTO> getUserMoneyPay(@Valid @RequestBody GetUserMoneyPayParam param){
		GetUserMoneyDTO dto = orderService.getUserMoneyPay(param.getUserId());
		return ResultGenerator.genSuccessResult("",dto);
	}
	
	@ApiOperation(value = "保存订单图片", notes = "保存订单图片")
	@RequestMapping(path="/saveOrderPicByOrderSn", method=RequestMethod.POST)
    public BaseResult<String> saveOrderPicByOrderSn(@Valid @RequestBody OrderPicParam param){
		orderService.saveOrderPicByOrderSn(param.getOrderSn(),param.getOrderPic(),param.getStoreId(),param.getOrderStatus());
		return ResultGenerator.genSuccessResult("保存订单图片成功");
	}

}
