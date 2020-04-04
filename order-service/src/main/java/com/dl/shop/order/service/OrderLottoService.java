package com.dl.shop.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.dl.base.enums.MatchBetTypeEnum;
import com.dl.base.enums.MatchPlayTypeEnum;
import com.dl.base.enums.MatchResultCrsEnum;
import com.dl.base.enums.MatchResultHadEnum;
import com.dl.base.enums.MatchResultHafuEnum;
import com.dl.base.enums.RespStatusEnum;
import com.dl.base.exception.ServiceException;
import com.dl.base.model.UserDeviceInfo;
import com.dl.base.result.BaseResult;
import com.dl.base.util.DateUtil;
import com.dl.base.util.SessionUtil;
import com.dl.lottery.api.ILotteryMatchService;
import com.dl.lottery.dto.DLBetLottoInfoDTO;
import com.dl.lottery.param.GetBetInfoByOrderSn;
import com.dl.member.api.ISysConfigService;
import com.dl.member.dto.SysConfigDTO;
import com.dl.member.param.SysConfigParam;
import com.dl.order.dto.LottoCathecticCell;
import com.dl.order.dto.LottoCathecticResult;
import com.dl.order.dto.LottoOrderDetailDTO;
import com.dl.order.dto.LottoTicketSchemeDTO;
import com.dl.order.dto.LottoTicketSchemeDTO.LottoTicketSchemeDetailDTO;
import com.dl.order.dto.ManualLottoOrderDetailDTO;
import com.dl.order.dto.OrderAppendInfoDTO;
import com.dl.order.dto.OrderDetailDTO.Cathectic;
import com.dl.order.dto.OrderDetailDTO.CathecticResult;
import com.dl.order.param.OrderDetailParam;
import com.dl.order.param.TicketSchemeParam;
import com.dl.shop.order.core.ProjectConstant;
import com.dl.shop.order.dao.OrderDetailMapper;
import com.dl.shop.order.dao.OrderMapper;
import com.dl.shop.order.model.LotteryClassifyTemp;
import com.dl.shop.order.model.Order;
import com.dl.shop.order.model.OrderDetail;
import com.dl.shop.order.service.base.BaseOrderService;
import com.dl.shop.order.utils.MathUtil;
import com.dl.shop.order.utils.TermDateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional("transactionManager1")
public class OrderLottoService extends BaseOrderService{
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private OrderDetailMapper orderDetailMapper;
	@Value("${dl.img.file.pre.url}")
	private String imgFilePreUrl;
	@Resource
	private ISysConfigService sysCfgService;
	@Resource
	private ILotteryMatchService lotteryMatchService;


	/**
	 * 查询订单详情(大乐透)
	 * @param param
	 * @return
	 */
	public LottoOrderDetailDTO getLottoOrderDetail(OrderDetailParam param) {
		String orderId = param.getOrderId();
		String orderSn = param.getOrderSn();
		if (StringUtils.isBlank(orderId) && StringUtils.isEmpty(orderSn)) {
			log.error("订单id：为空，该订单不存在");
			throw new ServiceException(RespStatusEnum.FAIL.getCode(), "订单不能为空");
		}
		Order order = null;
		LottoOrderDetailDTO lottoOrderDetailDTO = new LottoOrderDetailDTO();
		if(!StringUtils.isEmpty(orderId)) {
			Order orderParam = new Order();
//			Integer userId = SessionUtil.getUserId();
//			orderParam.setUserId(userId);
			orderParam.setOrderId(Integer.valueOf(param.getOrderId()));
			order = orderMapper.getOrderInfoByOrderId(Integer.valueOf(param.getOrderId()));
		}else{
			order = orderMapper.getOrderInfoByOrderSn(orderSn);
		}
		if (null == order) {
			log.error("订单id：" + param.getOrderId() + "，该订单不存在");
			throw new ServiceException(RespStatusEnum.FAIL.getCode(), "订单不存在");
		}
		Integer lotteryClassifyId = order.getLotteryClassifyId();
		LotteryClassifyTemp lotteryClassify = orderDetailMapper.lotteryClassify(lotteryClassifyId);
		if (lotteryClassify != null) {
			lottoOrderDetailDTO.setLotteryClassifyImg(imgFilePreUrl+lotteryClassify.getLotteryImg());
			lottoOrderDetailDTO.setLotteryClassifyName(lotteryClassify.getLotteryName());
		} else {
			lottoOrderDetailDTO.setLotteryClassifyImg("");
			lottoOrderDetailDTO.setLotteryClassifyName("");
		}
		if (ProjectConstant.ORDER_STATUS_NOT_PAY.equals(order.getOrderStatus())&&!ProjectConstant.PAY_STATUS_ALREADY.equals(order.getPayStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc("支付中");
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_NOT_PAY.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("支付中");
			lottoOrderDetailDTO.setProcessResult("处理中");
		} else if ((ProjectConstant.ORDER_STATUS_NOT_PAY.equals(order.getOrderStatus())&&ProjectConstant.PAY_STATUS_ALREADY.equals(order.getPayStatus()))||
				ProjectConstant.ORDER_STATUS_PAY_FAIL_LOTTERY.equals(order.getOrderStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc("等待出票");
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_PAY_FAIL_LOTTERY.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("支付成功");
			lottoOrderDetailDTO.setProcessResult("处理中");
		} else if (ProjectConstant.ORDER_STATUS_FAIL_LOTTERY.equals(order.getOrderStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc("彩金已退回");
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_FAIL_LOTTERY.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("出票失败");
			lottoOrderDetailDTO.setProcessResult("");
		} else if (ProjectConstant.ORDER_STATUS_STAY.equals(order.getOrderStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc("");
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_STAY.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("待开奖");
			lottoOrderDetailDTO.setProcessResult("等待开奖");
		} else if (ProjectConstant.ORDER_STATUS_NOT.equals(order.getOrderStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc("感谢您助力公益事业");
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_NOT.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("未中奖");
			lottoOrderDetailDTO.setProcessResult("再接再厉");
		} else if (ProjectConstant.ORDER_STATUS_ALREADY.equals(order.getOrderStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc(order.getWinningMoney().toString());
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_ALREADY.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("已中奖");
			lottoOrderDetailDTO.setProcessResult("恭喜中奖");
		} else if (ProjectConstant.ORDER_STATUS_REWARDING.equals(order.getOrderStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc(order.getWinningMoney().toString());
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_REWARDING.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("派奖中");
			lottoOrderDetailDTO.setProcessResult("派奖中");
		} else if (ProjectConstant.ORDER_STATUS_REWARDED.equals(order.getOrderStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc(order.getWinningMoney().toString());
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_REWARDED.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("派奖中");
			lottoOrderDetailDTO.setProcessResult("派奖中");
		} else if (ProjectConstant.ORDER_STATUS_PAY_FAIL_LOTTERY.equals(order.getOrderStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc("");
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_PAY_FAIL_LOTTERY.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("待出票");
			lottoOrderDetailDTO.setProcessResult("出票中");
		}
		BigDecimal moneyPaid = order.getMoneyPaid();
		lottoOrderDetailDTO.setMoneyPaid(moneyPaid != null ? moneyPaid.toString() : "");
		BigDecimal ticketAmount = order.getTicketAmount();
		lottoOrderDetailDTO.setTicketAmount(ticketAmount != null ? ticketAmount.toString() : "");
		BigDecimal surplus = order.getSurplus();
		lottoOrderDetailDTO.setSurplus(surplus != null ? surplus.toString() : "");
		BigDecimal userSurplusLimit = order.getUserSurplusLimit();
		lottoOrderDetailDTO.setUserSurplusLimit(userSurplusLimit != null ? userSurplusLimit.toString() : "");
		BigDecimal userSurplus = order.getUserSurplus();
		lottoOrderDetailDTO.setUserSurplus(userSurplus != null ? userSurplus.toString() : "");
		BigDecimal thirdPartyPaid = order.getThirdPartyPaid();
		lottoOrderDetailDTO.setThirdPartyPaid(thirdPartyPaid != null ? thirdPartyPaid.toString() : "");
		BigDecimal bonus = order.getBonus();
		lottoOrderDetailDTO.setBonus(bonus != null ? bonus.toString() : "");
		lottoOrderDetailDTO.setTermNum(order.getIssue());
		lottoOrderDetailDTO.setOrderSn(order.getOrderSn());
		
		lottoOrderDetailDTO.setWinningMoney(order.getWinningMoney().toString());
		lottoOrderDetailDTO.setPayName(order.getPayName());
		lottoOrderDetailDTO.setLotteryClassifyId(String.valueOf(lotteryClassifyId));
		lottoOrderDetailDTO.setProgrammeSn(order.getOrderSn());
		lottoOrderDetailDTO.setCreateTime(DateUtil.getCurrentTimeString(order.getAddTime().longValue(), DateUtil.datetimeFormat));
		Integer acceptTime = order.getAcceptTime();
		if (acceptTime != null && acceptTime > 0) {
			lottoOrderDetailDTO.setAcceptTime(DateUtil.getCurrentTimeString(acceptTime.longValue(), DateUtil.datetimeFormat));
		} else {
			lottoOrderDetailDTO.setAcceptTime("--");
		}
		Integer ticketTime = order.getTicketTime();
		if (ticketTime != null && ticketTime > 0) {
			lottoOrderDetailDTO.setTicketTime(DateUtil.getCurrentTimeString(ticketTime.longValue(),DateUtil.datetimeFormat));
		} else {
			lottoOrderDetailDTO.setTicketTime("--");
		}
		List<OrderDetail> orderDetails = null;
		if(!StringUtils.isEmpty(orderId)) {
			orderDetails = orderDetailMapper.selectByOrderId(Integer.valueOf(param.getOrderId()));
		}else {
			orderDetails = orderDetailMapper.queryListByOrderSn(orderSn);
		}
//		List<OrderDetail> orderDetails = orderDetailMapper.selectByOrderId(Integer.valueOf(param.getOrderId()), userId);
		String prize="";
		if(orderDetails.size()>0 ) {
			if(orderDetails.get(0).getMatchResult()!=null && !orderDetails.get(0).getMatchResult().equals("")) {
				prize=orderDetails.get(0).getMatchResult();
				lottoOrderDetailDTO.setPrizeNum(Arrays.asList(prize.split(",")));
			}else {
				lottoOrderDetailDTO.setPrePrizeInfo("预计 "+TermDateUtil.getTermEndTime()+" 开奖");
			}
		}
		final Order orderF = order;
		List<LottoCathecticResult>  cathecticResults = new ArrayList<>();
		orderDetails.forEach(item->{
			LottoCathecticResult dto = new LottoCathecticResult();
			List<String> preNum = new ArrayList<>();
			List<String> postNum = new ArrayList<>();
			
			if(item.getMatchResult()!=null && !item.getMatchResult().equals("")) {
			List<String> prizeNum = Arrays.asList(item.getMatchResult().split(","));
			preNum.addAll(prizeNum.subList(0, 5));
			postNum.addAll(prizeNum.subList(5, 7));
			}
			dto.setPlayType(Integer.parseInt(item.getBetType()));
			dto.setCathectic(orderF.getCathectic());
			String ticketData = item.getTicketData();
			if(ticketData.contains("$")) {
				List<LottoCathecticCell> redDanCathectics = new ArrayList<>();
				List<LottoCathecticCell> blueDanCathectics = new  ArrayList<>();
				List<LottoCathecticCell> redTuoCathectics = new ArrayList<>();
				List<LottoCathecticCell> blueTuoCathectics = new  ArrayList<>();
				String[] nums = item.getTicketData().split("\\|");
				if(nums[0].contains("$")) {//如果前区有$
					String[] preNums = nums[0].split("\\$");
					List<String> preDanList = Arrays.asList(preNums[0].split(","));
					preDanList.forEach(preDan->{
						LottoCathecticCell cell = new LottoCathecticCell();
						cell.setCathectic(preDan);
						if(preNum.size()>0 && preNum.contains(preDan)) {cell.setIsGuess("1");} else{cell.setIsGuess("0");}
						redDanCathectics.add(cell);
					});
					List<String> preTuoList = Arrays.asList(preNums[1].split(","));
					preTuoList.forEach(preTuo->{
						LottoCathecticCell cell = new LottoCathecticCell();
						cell.setCathectic(preTuo);
						if(preNum.size()>0 && preNum.contains(preTuo)) {cell.setIsGuess("1");} else{cell.setIsGuess("0");}
						redTuoCathectics.add(cell);
					});
				} 
				if(nums[1].contains("$")) {//如果前区有$
					String[] postNums = nums[1].split("\\$");
					List<String> postDanList = Arrays.asList(postNums[0].split(","));
					postDanList.forEach(postDan->{
						LottoCathecticCell cell = new LottoCathecticCell();
						cell.setCathectic(postDan);
						if(postNum.size()>0 && postNum.contains(postDan)) {cell.setIsGuess("1");} else{cell.setIsGuess("0");}
						blueDanCathectics.add(cell);
					});
					List<String> postTuoList = Arrays.asList(postNums[1].split(","));
					postTuoList.forEach(postTuo->{
						LottoCathecticCell cell = new LottoCathecticCell();
						cell.setCathectic(postTuo);
						if(postNum.size()>0 && postNum.contains(postTuo)) {cell.setIsGuess("1");} else{cell.setIsGuess("0");}
						blueTuoCathectics.add(cell);
					});
				}else {
					List<String> postTuoList = Arrays.asList(nums[1].split(","));
					postTuoList.forEach(postTuo->{
						LottoCathecticCell cell = new LottoCathecticCell();
						cell.setCathectic(postTuo);
						if(postNum.size()>0 && postNum.contains(postTuo)) {cell.setIsGuess("1");} else{cell.setIsGuess("0");}
						blueTuoCathectics.add(cell);
					});
				}
				dto.setRedDanCathectics(redDanCathectics);
				dto.setRedTuoCathectics(redTuoCathectics);
				dto.setBlueDanCathectics(blueDanCathectics);
				dto.setBlueTuoCathectics(blueTuoCathectics);
			}else {
				List<LottoCathecticCell> redCathectics = new ArrayList<>();
				List<LottoCathecticCell> blueCathectics = new  ArrayList<>();
				String[] nums = item.getTicketData().split("\\|");
				List<String> preList = Arrays.asList(nums[0].split(","));
				List<String> postList = Arrays.asList(nums[1].split(","));
				preList.forEach(pre->{ 
					LottoCathecticCell cell = new LottoCathecticCell(); 
					cell.setCathectic(pre);
					if(preNum.size()>0 && preNum.contains(cell.getCathectic())) {cell.setIsGuess("1");} else{cell.setIsGuess("0");}
					redCathectics.add(cell); 
				});
				postList.forEach(post->{ 
					LottoCathecticCell cell = new LottoCathecticCell(); 
					cell.setCathectic(post);
					if(postNum.size()>0 && postNum.contains(cell.getCathectic())) {cell.setIsGuess("1");} else{cell.setIsGuess("0");}
					blueCathectics.add(cell); 
				});
				dto.setRedCathectics(redCathectics);
				dto.setBlueCathectics(blueCathectics);
			}
			if(item.getBetType().equals("00")) {
				dto.setBetNum(1);
			}
			if(item.getBetType().equals("01")) {
				int betNum = MathUtil.getCathecticsCount(dto.getRedCathectics().size(), dto.getBlueCathectics().size());
				dto.setBetNum(betNum);
			}
			if(item.getBetType().equals("02")) {
				int betNum = MathUtil.getDanTuoCathecticsCount(dto.getRedTuoCathectics().size(), dto.getRedDanCathectics().size(), dto.getBlueTuoCathectics().size(), dto.getBlueDanCathectics().size());
				dto.setBetNum(betNum);
			}
			if(orderF.getPlayType().equals("05") ) {
				dto.setIsAppend(1);
				BigDecimal amount = new BigDecimal(dto.getBetNum()*3*orderF.getCathectic());
				dto.setAmount(amount.toString());
			}else {
				dto.setIsAppend(0);
				BigDecimal amount = new BigDecimal(dto.getBetNum()*2*orderF.getCathectic());
				dto.setAmount(amount.toString());
			}
			cathecticResults.add(dto);
		});
		lottoOrderDetailDTO.setCathecticResults(cathecticResults);
		Integer cathectic = order.getCathectic();
		Integer betNum = order.getBetNum();
		log.info("[getLottoOrderDetail]" + " cathectic:" + cathectic + " betNum:" + betNum);
		lottoOrderDetailDTO.setCathectic(cathectic);
		lottoOrderDetailDTO.setBetNum(betNum);
		return lottoOrderDetailDTO;
	}
	
	
	public ManualLottoOrderDetailDTO getManualLottoOrderList(String orderSn) {
		ManualLottoOrderDetailDTO detailDTO = new ManualLottoOrderDetailDTO();
		//lotto order detail
		OrderDetailParam orderDetailParam = new OrderDetailParam();
		orderDetailParam.setOrderSn(orderSn);
		LottoOrderDetailDTO lottoOrderDetailDto = getLottoOrderDetailSimulate(orderDetailParam,false);
		detailDTO.setOrderDetailDto(lottoOrderDetailDto);
		log.info("[getManualLottoOrderList]" + " lottoDetail:" + lottoOrderDetailDto);
//		//ticket scheme
//		TicketSchemeParam ticketSParam = new TicketSchemeParam();
//		ticketSParam.setOrderSn(orderSn);
//		LottoTicketSchemeDTO ticketSchemeDto = getLottoTicketScheme(ticketSParam);
//		detailDTO.setTicketSchemeDTO(ticketSchemeDto);
//		log.info("[getManualLottoOrderList]" + " scheme:" + ticketSchemeDto);
		return detailDTO;
	}
	
	/**
	 * 查询出票方案(大乐透)
	 * @param param
	 * @return
	 */
	public LottoTicketSchemeDTO getLottoTicketScheme(TicketSchemeParam param) {
		LottoTicketSchemeDTO lottoTicketSchemeDTO = new LottoTicketSchemeDTO();
		lottoTicketSchemeDTO.setProgrammeSn(param.getOrderSn());
		Order orderInfoByOrderSn = orderMapper.getOrderInfoByOrderSn(param.getOrderSn());
		Integer orderStatus = orderInfoByOrderSn.getOrderStatus();
		List<LottoTicketSchemeDetailDTO> LottoTicketSchemeDetailDTOs = new ArrayList<>();
		GetBetInfoByOrderSn getBetInfoByOrderSn = new GetBetInfoByOrderSn();
		getBetInfoByOrderSn.setOrderSn(param.getOrderSn());
		BaseResult<List<DLBetLottoInfoDTO>> result = lotteryMatchService.getBetInfoByLotto(getBetInfoByOrderSn);
		if (result.getCode() == 0) {
			List<DLBetLottoInfoDTO> infoList = result.getData();
			if (infoList.size()>0) {
				infoList.forEach(item->{
					LottoTicketSchemeDetailDTO dto = new LottoTicketSchemeDetailDTO();
					dto.setTicketSn(item.getTicketId());
					dto.setStatus(item.getStatus());
					dto.setAmount((item.getAmount()/100)+"0");//1.0页面显示两位小数1.00
					dto.setCathectic(item.getTimes());
					dto.setPlayType(Integer.parseInt(item.getBetType())); 
					if(item.getPlayType().equals("00")) {
						dto.setIsAppend(0);
					}else {
						dto.setIsAppend(1);
					}
					String ticketData = item.getStakes();
					if(ticketData.contains("$")) {
						List<LottoCathecticCell> redDanCathectics = new ArrayList<>();
						List<LottoCathecticCell> blueDanCathectics = new  ArrayList<>();
						List<LottoCathecticCell> redTuoCathectics = new ArrayList<>();
						List<LottoCathecticCell> blueTuoCathectics = new  ArrayList<>();
						String[] nums = ticketData.split("\\|");
						if(nums[0].contains("$")) {//如果前区有$
							String[] preNums = nums[0].split("\\$");
							List<String> preDanList = Arrays.asList(preNums[0].split(","));
							preDanList.forEach(preDan->{
								LottoCathecticCell cell = new LottoCathecticCell();
								cell.setCathectic(preDan);
								redDanCathectics.add(cell);
							});
							List<String> preTuoList = Arrays.asList(preNums[1].split(","));
							preTuoList.forEach(preTuo->{
								LottoCathecticCell cell = new LottoCathecticCell();
								cell.setCathectic(preTuo);
								redTuoCathectics.add(cell);
							});
						} 
						if(nums[1].contains("$")) {//如果前区有$
							String[] postNums = nums[1].split("\\$");
							List<String> postDanList = Arrays.asList(postNums[0].split(","));
							postDanList.forEach(postDan->{
								LottoCathecticCell cell = new LottoCathecticCell();
								cell.setCathectic(postDan);
								blueDanCathectics.add(cell);
							});
							List<String> postTuoList = Arrays.asList(postNums[1].split(","));
							postTuoList.forEach(postTuo->{
								LottoCathecticCell cell = new LottoCathecticCell();
								cell.setCathectic(postTuo);
								blueTuoCathectics.add(cell);
							});
						}else {
							List<String> postTuoList = Arrays.asList(nums[1].split(","));
							postTuoList.forEach(postTuo->{
								LottoCathecticCell cell = new LottoCathecticCell();
								cell.setCathectic(postTuo);
								blueTuoCathectics.add(cell);
							});
						}
						dto.setRedDanCathectics(redDanCathectics);
						dto.setRedTuoCathectics(redTuoCathectics);
						dto.setBlueDanCathectics(blueDanCathectics);
						dto.setBlueTuoCathectics(blueTuoCathectics);
					}else {
						List<LottoCathecticCell> redCathectics = new ArrayList<>();
						List<LottoCathecticCell> blueCathectics = new  ArrayList<>();
						String[] nums = ticketData.split("\\|");
						List<String> preList = Arrays.asList(nums[0].split(","));
						List<String> postList = Arrays.asList(nums[1].split(","));
						preList.forEach(pre->{ 
							LottoCathecticCell cell = new LottoCathecticCell(); 
							cell.setCathectic(pre);
							redCathectics.add(cell); 
						});
						postList.forEach(post->{ 
							LottoCathecticCell cell = new LottoCathecticCell(); 
							cell.setCathectic(post);
							blueCathectics.add(cell); 
						});
						dto.setRedCathectics(redCathectics);
						dto.setBlueCathectics(blueCathectics);
					}
					if(item.getBetType().equals("00")) {
						dto.setBetNum(1);
					}
					if(item.getBetType().equals("01")) {
						int betNum = MathUtil.getCathecticsCount(dto.getRedCathectics().size(), dto.getBlueCathectics().size());
						dto.setBetNum(betNum);
					}
					if(item.getBetType().equals("02")) {
						int betNum = MathUtil.getDanTuoCathecticsCount(dto.getRedTuoCathectics().size(), dto.getRedDanCathectics().size(), dto.getBlueTuoCathectics().size(), dto.getBlueDanCathectics().size());
						dto.setBetNum(betNum);
					}
					LottoTicketSchemeDetailDTOs.add(dto);
				});
			}
			lottoTicketSchemeDTO.setLottoTicketSchemeDetailDTOs(LottoTicketSchemeDetailDTOs);
		}else if (orderStatus.equals(0)||orderStatus.equals(1)) {
			LottoTicketSchemeDetailDTO dto = new LottoTicketSchemeDetailDTO();
			dto.setNumber("1");
//			dto.setTickeContent("-");
//			dto.setPassType("-");
//			dto.setCathectic("-");
			dto.setStatus(0);
			LottoTicketSchemeDetailDTOs.add(dto);
			lottoTicketSchemeDTO.setLottoTicketSchemeDetailDTOs(LottoTicketSchemeDetailDTOs);
		} else {
			LottoTicketSchemeDetailDTO dto = new LottoTicketSchemeDetailDTO();
			dto.setNumber("1");
//			dto.setTickeContent("-");
//			dto.setPassType("-");
//			dto.setMultiple("-");
			dto.setStatus(2);
			LottoTicketSchemeDetailDTOs.add(dto);
			lottoTicketSchemeDTO.setLottoTicketSchemeDetailDTOs(LottoTicketSchemeDetailDTOs);
		}
		return lottoTicketSchemeDTO;
	}
	
	/**
	 * 查询模拟订单详情(大乐透)
	 * @param param
	 * @return
	 */
	public LottoOrderDetailDTO getLottoOrderDetailSimulate(OrderDetailParam param,boolean isStore) {
		String orderId = param.getOrderId();
		String orderSn = param.getOrderSn();
		log.info("[getLottoOrderDetailSimulate]" + " orderId:" + orderId + " orderSn:" + orderSn);
		if (StringUtils.isBlank(orderId) && StringUtils.isBlank(orderSn)) {
			log.error("订单id：为空，该订单不存在");
			throw new ServiceException(RespStatusEnum.FAIL.getCode(), "订单不能为空");
		}
		Order order = null;
		LottoOrderDetailDTO lottoOrderDetailDTO = new LottoOrderDetailDTO();
		if(!StringUtils.isBlank(orderSn)) {
			order = orderMapper.getOrderInfoByOrderSn(orderSn);
		}else {
			Order orderParam = new Order();
			orderParam.setOrderId(Integer.valueOf(param.getOrderId()));
			order = orderMapper.selectOne(orderParam);
		}
		if (null == order) {
			log.error("订单id：" + param.getOrderId() + "，该订单不存在");
			throw new ServiceException(RespStatusEnum.FAIL.getCode(), "订单不存在");
		}
		orderId = order.getOrderId()+"";
		orderSn = order.getOrderSn();
		Integer storeId = order.getStoreId();
		lottoOrderDetailDTO.setStoreId(storeId);
		Integer lotteryClassifyId = order.getLotteryClassifyId();
		LotteryClassifyTemp lotteryClassify = orderDetailMapper.lotteryClassify(lotteryClassifyId);
		if (lotteryClassify != null) {
			lottoOrderDetailDTO.setLotteryClassifyImg(imgFilePreUrl+lotteryClassify.getLotteryImg());
			lottoOrderDetailDTO.setLotteryClassifyName(lotteryClassify.getLotteryName());
		} else {
			lottoOrderDetailDTO.setLotteryClassifyImg("");
			lottoOrderDetailDTO.setLotteryClassifyName("");
		}
		if (ProjectConstant.ORDER_STATUS_NOT_PAY.equals(order.getOrderStatus())&&!ProjectConstant.PAY_STATUS_ALREADY.equals(order.getPayStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc("模拟支付中");
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_NOT_PAY.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("模拟支付中");
			lottoOrderDetailDTO.setProcessResult("处理中");
		} else if ((ProjectConstant.ORDER_STATUS_NOT_PAY.equals(order.getOrderStatus())&&ProjectConstant.PAY_STATUS_ALREADY.equals(order.getPayStatus()))||
				ProjectConstant.ORDER_STATUS_PAY_FAIL_LOTTERY.equals(order.getOrderStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc("等待出票");
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_PAY_FAIL_LOTTERY.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("支付成功");
			lottoOrderDetailDTO.setProcessResult("处理中");
		} else if (ProjectConstant.ORDER_STATUS_FAIL_LOTTERY.equals(order.getOrderStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc("彩金已退回");
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_FAIL_LOTTERY.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("出票失败");
			lottoOrderDetailDTO.setProcessResult("");
		} else if (ProjectConstant.ORDER_STATUS_STAY.equals(order.getOrderStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc("");
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_STAY.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("待开奖");
			lottoOrderDetailDTO.setProcessResult("等待开奖");
		} else if (ProjectConstant.ORDER_STATUS_NOT.equals(order.getOrderStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc("感谢您助力公益事业");
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_NOT.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("未中奖");
			lottoOrderDetailDTO.setProcessResult("再接再厉");
		} else if (ProjectConstant.ORDER_STATUS_ALREADY.equals(order.getOrderStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc(order.getWinningMoney().toString());
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_ALREADY.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("已中奖");
			lottoOrderDetailDTO.setProcessResult("恭喜中奖");
		} else if (ProjectConstant.ORDER_STATUS_REWARDING.equals(order.getOrderStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc(order.getWinningMoney().toString());
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_REWARDING.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("派奖中");
			lottoOrderDetailDTO.setProcessResult("派奖中");
		} else if (ProjectConstant.ORDER_STATUS_REWARDED.equals(order.getOrderStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc(order.getWinningMoney().toString());
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_REWARDED.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("派奖中");
			lottoOrderDetailDTO.setProcessResult("派奖中");
		} else if (ProjectConstant.ORDER_STATUS_PAY_FAIL_LOTTERY.equals(order.getOrderStatus())) {
			lottoOrderDetailDTO.setProcessStatusDesc("");
			lottoOrderDetailDTO.setOrderStatus(ProjectConstant.ORDER_STATUS_PAY_FAIL_LOTTERY.toString());
			lottoOrderDetailDTO.setOrderStatusDesc("待出票");
			lottoOrderDetailDTO.setProcessResult("出票中");
		}
		BigDecimal moneyPaid = order.getMoneyPaid();
		lottoOrderDetailDTO.setMoneyPaid(moneyPaid != null ? moneyPaid.toString() : "");
		BigDecimal ticketAmount = order.getTicketAmount();
		lottoOrderDetailDTO.setTicketAmount(ticketAmount != null ? ticketAmount.toString() : "");
		BigDecimal surplus = order.getSurplus();
		lottoOrderDetailDTO.setSurplus(surplus != null ? surplus.toString() : "");
		BigDecimal userSurplusLimit = order.getUserSurplusLimit();
		lottoOrderDetailDTO.setUserSurplusLimit(userSurplusLimit != null ? userSurplusLimit.toString() : "");
		BigDecimal userSurplus = order.getUserSurplus();
		lottoOrderDetailDTO.setUserSurplus(userSurplus != null ? userSurplus.toString() : "");
		BigDecimal thirdPartyPaid = order.getThirdPartyPaid();
		lottoOrderDetailDTO.setThirdPartyPaid(thirdPartyPaid != null ? thirdPartyPaid.toString() : "");
		BigDecimal bonus = order.getBonus();
		lottoOrderDetailDTO.setBonus(bonus != null ? bonus.toString() : "");
		lottoOrderDetailDTO.setTermNum(order.getIssue());
		lottoOrderDetailDTO.setOrderSn(order.getOrderSn());
		if (order.getMaxLevel() != null && 1== order.getMaxLevel()) {
			lottoOrderDetailDTO.setWinningMoney("一等奖");
		}else if(order.getMaxLevel() != null && 2== order.getMaxLevel()){
			lottoOrderDetailDTO.setWinningMoney("二等奖");
		}else {
			lottoOrderDetailDTO.setWinningMoney(order.getWinningMoney().toString());
		}
		lottoOrderDetailDTO.setPayName(order.getPayName());
		lottoOrderDetailDTO.setLotteryClassifyId(String.valueOf(lotteryClassifyId));
		lottoOrderDetailDTO.setProgrammeSn(order.getOrderSn());
		lottoOrderDetailDTO.setCreateTime(DateUtil.getCurrentTimeString(order.getAddTime().longValue(), DateUtil.datetimeFormat));
		Integer acceptTime = order.getAcceptTime();
		if (acceptTime != null && acceptTime > 0) {
			lottoOrderDetailDTO.setAcceptTime(DateUtil.getCurrentTimeString(acceptTime.longValue(), DateUtil.datetimeFormat));
		} else {
			lottoOrderDetailDTO.setAcceptTime("--");
		}
		Integer ticketTime = order.getTicketTime();
		if (ticketTime != null && ticketTime > 0) {
			lottoOrderDetailDTO.setTicketTime(DateUtil.getCurrentTimeString(ticketTime.longValue(), DateUtil.datetimeFormat));
		} else {
			lottoOrderDetailDTO.setTicketTime("--");
		}
		List<OrderDetail> orderDetails = orderDetailMapper.selectByOrderId(Integer.valueOf(orderId));
//		List<OrderDetail> orderDetails = orderDetailMapper.selectByOrderId(Integer.valueOf(param.getOrderId()), userId);
		log.info("[getLottoOrderDetailSimulate]" + " orderId:" + orderId + " orderDetails:" + orderDetails.size());
		String prize="";
		if(orderDetails.size()>0 ) {
			if(orderDetails.get(0).getMatchResult()!=null && !orderDetails.get(0).getMatchResult().equals("")) {
				prize=orderDetails.get(0).getMatchResult();
				lottoOrderDetailDTO.setPrizeNum(Arrays.asList(prize.split(",")));
			}else {
				lottoOrderDetailDTO.setPrePrizeInfo("预计 "+TermDateUtil.getTermEndTime()+" 开奖");
			}
		}
		final Order orderF = order;
		List<LottoCathecticResult>  cathecticResults = new ArrayList<>();
		orderDetails.forEach(item->{
			LottoCathecticResult dto = new LottoCathecticResult();
			List<String> preNum = new ArrayList<>();
			List<String> postNum = new ArrayList<>();
			
			if(item.getMatchResult()!=null && !item.getMatchResult().equals("")) {
			List<String> prizeNum = Arrays.asList(item.getMatchResult().split(","));
			preNum.addAll(prizeNum.subList(0, 5));
			postNum.addAll(prizeNum.subList(5, 7));
			}
			dto.setPlayType(Integer.parseInt(item.getBetType()));
			dto.setCathectic(orderF.getCathectic());
			dto.setBetNum(orderF.getBetNum());
			String ticketData = item.getTicketData();
			if(ticketData.contains("$")) {
				List<LottoCathecticCell> redDanCathectics = new ArrayList<>();
				List<LottoCathecticCell> blueDanCathectics = new  ArrayList<>();
				List<LottoCathecticCell> redTuoCathectics = new ArrayList<>();
				List<LottoCathecticCell> blueTuoCathectics = new  ArrayList<>();
				String[] nums = item.getTicketData().split("\\|");
				if(nums[0].contains("$")) {//如果前区有$
					String[] preNums = nums[0].split("\\$");
					List<String> preDanList = Arrays.asList(preNums[0].split(","));
					preDanList.forEach(preDan->{
						LottoCathecticCell cell = new LottoCathecticCell();
						cell.setCathectic(preDan);
						if(preNum.size()>0 && preNum.contains(preDan)) {cell.setIsGuess("1");} else{cell.setIsGuess("0");}
						redDanCathectics.add(cell);
					});
					List<String> preTuoList = Arrays.asList(preNums[1].split(","));
					preTuoList.forEach(preTuo->{
						LottoCathecticCell cell = new LottoCathecticCell();
						cell.setCathectic(preTuo);
						if(preNum.size()>0 && preNum.contains(preTuo)) {cell.setIsGuess("1");} else{cell.setIsGuess("0");}
						redTuoCathectics.add(cell);
					});
				} 
				if(nums[1].contains("$")) {//如果前区有$
					String[] postNums = nums[1].split("\\$");
					List<String> postDanList = Arrays.asList(postNums[0].split(","));
					postDanList.forEach(postDan->{
						LottoCathecticCell cell = new LottoCathecticCell();
						cell.setCathectic(postDan);
						if(postNum.size()>0 && postNum.contains(postDan)) {cell.setIsGuess("1");} else{cell.setIsGuess("0");}
						blueDanCathectics.add(cell);
					});
					List<String> postTuoList = Arrays.asList(postNums[1].split(","));
					postTuoList.forEach(postTuo->{
						LottoCathecticCell cell = new LottoCathecticCell();
						cell.setCathectic(postTuo);
						if(postNum.size()>0 && postNum.contains(postTuo)) {cell.setIsGuess("1");} else{cell.setIsGuess("0");}
						blueTuoCathectics.add(cell);
					});
				}else {
					List<String> postTuoList = Arrays.asList(nums[1].split(","));
					postTuoList.forEach(postTuo->{
						LottoCathecticCell cell = new LottoCathecticCell();
						cell.setCathectic(postTuo);
						if(postNum.size()>0 && postNum.contains(postTuo)) {cell.setIsGuess("1");} else{cell.setIsGuess("0");}
						blueTuoCathectics.add(cell);
					});
				}
				dto.setRedDanCathectics(redDanCathectics);
				dto.setRedTuoCathectics(redTuoCathectics);
				dto.setBlueDanCathectics(blueDanCathectics);
				dto.setBlueTuoCathectics(blueTuoCathectics);
			}else {
				List<LottoCathecticCell> redCathectics = new ArrayList<>();
				List<LottoCathecticCell> blueCathectics = new  ArrayList<>();
				String[] nums = item.getTicketData().split("\\|");
				List<String> preList = Arrays.asList(nums[0].split(","));
				List<String> postList = Arrays.asList(nums[1].split(","));
				preList.forEach(pre->{ 
					LottoCathecticCell cell = new LottoCathecticCell(); 
					cell.setCathectic(pre);
					if(preNum.size()>0 && preNum.contains(cell.getCathectic())) {cell.setIsGuess("1");} else{cell.setIsGuess("0");}
					redCathectics.add(cell); 
				});
				postList.forEach(post->{ 
					LottoCathecticCell cell = new LottoCathecticCell(); 
					cell.setCathectic(post);
					if(postNum.size()>0 && postNum.contains(cell.getCathectic())) {cell.setIsGuess("1");} else{cell.setIsGuess("0");}
					blueCathectics.add(cell); 
				});
				dto.setRedCathectics(redCathectics);
				dto.setBlueCathectics(blueCathectics);
			}
			if(item.getBetType().equals("00")) {
				dto.setBetNum(1);
			}
			if(item.getBetType().equals("01")) {
				int betNum = MathUtil.getCathecticsCount(dto.getRedCathectics().size(), dto.getBlueCathectics().size());
				dto.setBetNum(betNum);
			}
			if(item.getBetType().equals("02")) {
				int betNum = MathUtil.getDanTuoCathecticsCount(dto.getRedTuoCathectics().size(), dto.getRedDanCathectics().size(), dto.getBlueTuoCathectics().size(), dto.getBlueDanCathectics().size());
				dto.setBetNum(betNum);
			}
			if(orderF.getPlayType().equals("05") ) {
				dto.setIsAppend(1);
				BigDecimal amount = new BigDecimal(dto.getBetNum()*3*orderF.getCathectic());
				dto.setAmount(amount.toString());
			}else {
				dto.setIsAppend(0);
				BigDecimal amount = new BigDecimal(dto.getBetNum()*2*orderF.getCathectic());
				dto.setAmount(amount.toString());
			}
			cathecticResults.add(dto);
		});
		lottoOrderDetailDTO.setCathecticResults(cathecticResults);
		//bet num
		if(cathecticResults != null && cathecticResults.size() > 0) {
			LottoCathecticResult lotteryCR = cathecticResults.get(0);
			lottoOrderDetailDTO.setBetNum(lotteryCR.getBetNum());
			lottoOrderDetailDTO.setIsAppend(lotteryCR.getIsAppend());;
		}
		
		SysConfigParam sysCfgParams = new SysConfigParam();
		sysCfgParams.setBusinessId(17);
		BaseResult<SysConfigDTO> baseR = sysCfgService.querySysConfig(sysCfgParams);
		if(baseR != null && baseR.isSuccess() && baseR.getData() != null) {
			String desc = baseR.getData().getDescribtion();
			List<String> picList = JSONObject.parseArray(desc,String.class);
			String url = picList.get(0);
			log.info("[getLottoOrderDetailSimulate]" + " desc url:" + baseR.getData().getDescribtion());
			lottoOrderDetailDTO.setAddFriendsQRBarUrl(url);
		}
		UserDeviceInfo userDevice = SessionUtil.getUserDevice();
		String channel = "";
		if(userDevice != null) {
			channel = userDevice.getChannel();
			List<OrderAppendInfoDTO> appendInfoList = getOrderDetailAppendInfo(userDevice);
			lottoOrderDetailDTO.setAppendInfoList(appendInfoList);
			log.info("[getLottoOrderDetailSimulate]" + " appendInfoList:" + appendInfoList.size());
		}
		//获取分享链接
		if(!isStore) {
			sysCfgParams.setBusinessId(55);
			baseR = sysCfgService.querySysConfig(sysCfgParams);
			if(baseR != null && baseR.isSuccess()) {
				String shareUrl = baseR.getData().getValueTxt();
				shareUrl = shareUrl + "?" + "id=" + orderSn+"&qd=" + channel;
				lottoOrderDetailDTO.setOrderShareUrl(shareUrl);
			}
		}
		Integer cathectic = order.getCathectic();
		lottoOrderDetailDTO.setCathectic(cathectic);
		log.info("[getLottoOrderDetailSimulate]"+ " cathectic:" + cathectic);
		return lottoOrderDetailDTO;
	}
	
	
	/**
	 * 组装通过方式字符串
	 * @param passType
	 * @return
	 */
	private String getPassType(String passType) {
		String[] passTypes = passType.split(",");
		String passTypeStr = "";
		for (int i = 0; i < passTypes.length; i++) {
			passTypeStr += MatchBetTypeEnum.getName(passTypes[i]) + ",";
		}
		return passTypeStr.substring(0, passTypeStr.length() - 1);
	}
	
	
	/**
	 * 组装投注、赛果列数据
	 * 
	 * @param ticketData
	 * @param matchResult
	 * @return
	 */
	private List<CathecticResult> getCathecticResults(String fixedodds, String ticketData, String matchResult, Map<Integer, String> types) {
		List<CathecticResult> cathecticResults = new LinkedList<CathecticResult>();
		if (StringUtils.isEmpty(ticketData))
			return cathecticResults;
		List<String> ticketDatas = Arrays.asList(ticketData.split(";"));
		List<String> matchResults = null;
		if (StringUtils.isNotEmpty(matchResult) && !ProjectConstant.ORDER_MATCH_RESULT_CANCEL.equals(matchResult)) {
			matchResults = Arrays.asList(matchResult.split(";"));
		}
		if (CollectionUtils.isNotEmpty(ticketDatas)) {
			for (String temp : ticketDatas) {
				CathecticResult cathecticResult = new CathecticResult();
				List<Cathectic> cathectics = new LinkedList<Cathectic>();
				String matchResultStr = "";
				String playType = temp.substring(0, temp.indexOf("|"));
				String playCode = temp.substring(temp.indexOf("|") + 1, temp.lastIndexOf("|"));
				String betCells = temp.substring(temp.lastIndexOf("|") + 1);
				String[] betCellArr = betCells.split(",");
				for (int i = 0; i < betCellArr.length; i++) {
					Cathectic cathectic = new Cathectic();
					String betCellCode = betCellArr[i].substring(0, betCellArr[i].indexOf("@"));
					String betCellOdds = betCellArr[i].substring(betCellArr[i].indexOf("@") + 1);
					String cathecticStr = getCathecticData(playType, betCellCode);
					cathectic.setCathectic(cathecticStr + "[" + String.format("%.2f", Double.valueOf(betCellOdds)) + "]");
					if (null != matchResults) {
						for (String matchStr : matchResults) {
							String rstPlayType = matchStr.substring(0, matchStr.indexOf("|"));
							String rstPlayCode = matchStr.substring(matchStr.indexOf("|") + 1, matchStr.lastIndexOf("|"));
							String rstPlayCells = matchStr.substring(matchStr.lastIndexOf("|") + 1);
							if (playType.equals(rstPlayType) && playCode.equals(rstPlayCode)) {
								if (rstPlayCells.equals(betCellCode)) {
									cathectic.setIsGuess("1");
								} else {
									cathectic.setIsGuess("0");
								}
								matchResultStr = getCathecticData(playType, rstPlayCells);
							}
						}
					} else {
						cathectic.setIsGuess("0");
					}
					cathectics.add(cathectic);
				}
				if (StringUtils.isBlank(matchResultStr)) {
					matchResultStr = "待定";
				}
				if (ProjectConstant.ORDER_MATCH_RESULT_CANCEL.equals(matchResult)) {
					matchResultStr = "#?";
				}
				String playName = types.getOrDefault(Integer.valueOf(playType), playType);
				if (Integer.valueOf(playType).equals(MatchPlayTypeEnum.PLAY_TYPE_HHAD.getcode())) {
					playName = StringUtils.isBlank(fixedodds) ? playName : ("[" + fixedodds + "]" + playName);
				}
				cathecticResult.setPlayType(playName);
				cathecticResult.setCathectics(cathectics);
				cathecticResult.setMatchResult(matchResultStr);
				cathecticResults.add(cathecticResult);
			}
		}
		return cathecticResults;
	}

	
	/**
	 * 通过玩法code与投注内容，进行转换
	 * 
	 * @param playCode
	 * @param cathecticStr
	 * @return
	 */
	private String getCathecticData(String playTypeStr, String cathecticStr) {
		int playType = Integer.parseInt(playTypeStr);
		String cathecticData = "";
		if (MatchPlayTypeEnum.PLAY_TYPE_HHAD.getcode() == playType || MatchPlayTypeEnum.PLAY_TYPE_HAD.getcode() == playType) {
			cathecticData = MatchResultHadEnum.getName(Integer.valueOf(cathecticStr));
		} else if (MatchPlayTypeEnum.PLAY_TYPE_CRS.getcode() == playType) {
			cathecticData = MatchResultCrsEnum.getName(cathecticStr);
		} else if (MatchPlayTypeEnum.PLAY_TYPE_TTG.getcode() == playType) {
			cathecticData = cathecticStr + "球";
			if ("7".equals(cathecticStr)) {
				cathecticData = cathecticStr + "+球";
			}
		} else if (MatchPlayTypeEnum.PLAY_TYPE_HAFU.getcode() == playType) {
			cathecticData = MatchResultHafuEnum.getName(cathecticStr);
		}
		return cathecticData;
	}
	
}
