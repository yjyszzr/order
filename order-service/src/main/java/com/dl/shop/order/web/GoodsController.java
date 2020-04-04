package com.dl.shop.order.web;
import com.dl.base.param.EmptyParam;
import com.dl.base.result.BaseResult;
import com.dl.base.result.ResultGenerator;
import com.dl.base.util.JSONHelper;
import com.dl.base.util.SessionUtil;
import com.dl.lottery.api.INavBannerService;
import com.dl.lottery.dto.DlBannerPicDTO;
import com.dl.member.api.ISysConfigService;
import com.dl.member.dto.SysConfigDTO;
import com.dl.member.param.SysConfigParam;
import com.dl.order.dto.BannerEntityDTO;
import com.dl.order.dto.BannerListDTO;
import com.dl.order.dto.CalculateDTO;
import com.dl.order.dto.GoodsDTO;
import com.dl.order.dto.GoodsDetailsDTO;
import com.dl.order.dto.GoodsOrderDTO;
import com.dl.order.dto.GoodsPicDTO;
import com.dl.order.dto.PayTokenDTO;
import com.dl.order.param.CalculateParam;
import com.dl.order.param.GoodsOrderParam;
import com.dl.order.param.IdParam;
import com.dl.order.param.ListGoodsParam;
import com.dl.order.param.OrderIdParam;
import com.dl.shop.order.core.ProjectConstant;
import com.dl.shop.order.model.Goods;
import com.dl.shop.order.model.GoodsOrder;
import com.dl.shop.order.model.GoodsPic;
import com.dl.shop.order.service.GoodsOrderService;
import com.dl.shop.order.service.GoodsPicService;
import com.dl.shop.order.service.GoodsService;
import com.dl.shop.order.utils.MD5;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiOperation;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
* Created by CodeGenerator on 2018/11/28.
*/
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;
    
    @Resource
    private GoodsPicService goodsPicService;
    
    @Resource
    private GoodsOrderService goodsOrderService;
    
    @Resource
    private INavBannerService navBannerService;
    
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    
    @Resource
    private ISysConfigService sysConfigService;

    @ApiOperation(value = "提交商品(下单)", notes = "提交商品(下单)")
    @PostMapping("/orderAdd")
    public BaseResult<String> orderAdd(@RequestBody IdParam  param) {
    	Goods goods = goodsService.findById(param.getGoodsId());
    	GoodsOrder goodsOrder =new GoodsOrder();
    	if (null !=goods) {
    		goodsOrder.setDescription(goods.getDescription());
    		goodsOrder.setGoodsId(param.getGoodsId());
    		goodsOrder.setPrice(goods.getPresentPrice());
    		goodsOrder.setOrderPic(goods.getOrderPic());
    		goodsOrderService.saveOrder(goodsOrder);
		}
        return ResultGenerator.genSuccessResult(null, goodsOrder.getId().toString());
    }
 
	@ApiOperation(value = "商品信息更新", notes = "商品信息更新")
    @PostMapping("/orderUpdate")
    public BaseResult<PayTokenDTO> orderUpdate(@RequestBody GoodsOrderParam goodsOrderParam) {
		GoodsOrder goodsOrder = goodsOrderService.findById(goodsOrderParam.getOrderId());
		PayTokenDTO payTokenDTO =new PayTokenDTO();
    	if (null !=goodsOrder) {
    		Goods goods=	goodsService.findById(goodsOrder.getGoodsId());
        goodsOrder.setAddress(goodsOrderParam.getAddress());
        goodsOrder.setContactsName(goodsOrderParam.getContactsName());
        goodsOrder.setGoodsId(goodsOrder.getGoodsId());
        goodsOrder.setNum(goodsOrderParam.getNum());
        goodsOrder.setPhone(goodsOrderParam.getPhone());
        BigDecimal bdNum = new BigDecimal(goodsOrderParam.getNum());
        goodsOrder.setTotalPrice(goods.getPresentPrice().multiply(bdNum));
        goodsOrderService.update(goodsOrder);
        SysConfigParam sysConfigParam =new SysConfigParam ();
        sysConfigParam.setBusinessId(ProjectConstant.ORDER_INFO_SKIP_BISSION_ID);//商城下单跳转业务Id
        BaseResult<SysConfigDTO>  sysConfig =   sysConfigService.querySysConfig(sysConfigParam);
        if (null!=sysConfig.getData() &&sysConfig.getData().getValue().compareTo(BigDecimal.ZERO) > 0) {
	        	String goodsJson = JSONHelper.bean2json(goodsOrder);
	        	String keyStr = "bet_info_" + SessionUtil.getUserId() +"_"+ System.currentTimeMillis();
	        	String payToken = MD5.crypt(keyStr);
	        	payTokenDTO.setPayTokenDTO(payToken);
	        	stringRedisTemplate.opsForValue().set(payToken, goodsJson, ProjectConstant.ORDER_INFO_EXPIRE_TIME, TimeUnit.MINUTES);
			}
    	}
        return ResultGenerator.genSuccessResult(null,payTokenDTO);
    }

	
	@ApiOperation(value = "订单详情", notes = "订单详情")
	@PostMapping("/orderDetail")
	public BaseResult<GoodsOrderDTO> orderDetail(@RequestBody OrderIdParam param) {
		GoodsOrderDTO  goodsOrderDTO =new GoodsOrderDTO();
		GoodsOrder goodsOrder = 	goodsOrderService.findById(param.getOrderId());
		goodsOrderDTO.setDescription(goodsOrder.getDescription());
		goodsOrderDTO.setOrderId(goodsOrder.getId());
		goodsOrderDTO.setOrderPic(goodsOrder.getOrderPic());
		goodsOrderDTO.setPrice(goodsOrder.getPrice());
        SysConfigParam sysConfigParam =new SysConfigParam ();
        sysConfigParam.setBusinessId(ProjectConstant.ORDER_INFO_SKIP_BISSION_ID);//商城下单跳转业务Id
        BaseResult<SysConfigDTO>  sysConfig =   sysConfigService.querySysConfig(sysConfigParam);
        if (null!=sysConfig.getData() &&sysConfig.getData().getValue().compareTo(BigDecimal.ZERO) > 0) {
        		goodsOrderDTO.setButtonInfo("提交订单"); 
			}else {
				goodsOrderDTO.setButtonInfo("联系客服"); 
			}
		
		   return ResultGenerator.genSuccessResult(null,goodsOrderDTO); 
	}	
	
	
	@ApiOperation(value = "商品详情", notes = "商品详情")
    @PostMapping("/goodsDetail")
    public BaseResult<GoodsDetailsDTO> goodsDetail(@RequestBody IdParam param) {
        Goods goods = goodsService.findById(param.getGoodsId());
        GoodsDetailsDTO  goodsDetailsDTO =new GoodsDetailsDTO();
        goodsDetailsDTO.setDescription(goods.getDescription());
        goodsDetailsDTO.setPaidNum(goods.getPaidNum().toString());
        String historyPrice = goods.getHistoryPrice().toString();
        goodsDetailsDTO.setHistoryPrice(historyPrice.replaceAll(".00", ""));
        String presentPrice = goods.getPresentPrice().toString();
        goodsDetailsDTO.setPresentPrice(presentPrice.replaceAll(".00", ""));
       List<GoodsPic> goodsPicList= goodsPicService.findByGoodsId(goods.getId());
         List<GoodsPicDTO> bannerList = new ArrayList<GoodsPicDTO>();
         List<GoodsPicDTO> detailsList = new ArrayList<GoodsPicDTO>();
       for (int i = 0; i < goodsPicList.size(); i++) {
    	   GoodsPicDTO goodsPicDTO =new  GoodsPicDTO();
    	   goodsPicDTO.setBannerImage(goodsPicList.get(i).getImage());
    	   if (goodsPicList.get(i).getImageType()==1) { //类型 (1:详情,2轮播)
    		   bannerList.add(goodsPicDTO);
		}else {
			detailsList.add(goodsPicDTO);
			goodsDetailsDTO.setDetailPicList(detailsList);
		}
	}
       goodsDetailsDTO.setBannerList(bannerList);
       String attributeArray[] = goods.getBaseAttribute().split(",");
       List<String> baseAttributeList =new ArrayList<String>();
       for (String s : attributeArray) {
    	   baseAttributeList.add(s);
    	}
       goodsDetailsDTO.setBaseAttributeList(baseAttributeList);
        return ResultGenerator.genSuccessResult(null,goodsDetailsDTO);
    }

	@ApiOperation(value = "商品首页", notes = "商品首页")
	@PostMapping("/goodsList")
	public BaseResult<PageInfo<GoodsDTO>> goodsList(@RequestBody ListGoodsParam param) {
		Integer page = param.getPage();
		page = null == page ? 1 : page;
		Integer size = param.getSize();
		size = null == size ? 20 : size;
		PageHelper.startPage(page, size);
		PageInfo<GoodsDTO> rst = goodsService.findGoodsList();
		return ResultGenerator.genSuccessResult(null, rst);
	}
	
	@ApiOperation(value = "计算商品总价", notes = "计算商品总价")
	@PostMapping("/calculatePrice")
	public BaseResult<CalculateDTO> calculatePrice(@RequestBody CalculateParam param) {
		CalculateDTO calculateDTO = new CalculateDTO();
		GoodsOrder goodsOrder  = goodsOrderService.findById(param.getOrderId());
		if (null != goodsOrder) {
			BigDecimal num = new BigDecimal(param.getNum());
			String totalPrice = goodsOrder.getPrice().multiply(num).toString();
			calculateDTO.setTotalPrice(totalPrice.replaceAll(".00", ""));
		}else {
			calculateDTO.setTotalPrice("0");
		}
		return ResultGenerator.genSuccessResult(null, calculateDTO);
	}
	
	@ApiOperation(value = "商城轮播图", notes = "商城轮播图")
	@PostMapping("/bannerList")
	public BaseResult<BannerListDTO> bannerList(@RequestBody EmptyParam param) {
		 BaseResult<List<DlBannerPicDTO>>  bannerPicList= 	navBannerService.shopBanners(param);
		 List<BannerEntityDTO> bannerList =new ArrayList<BannerEntityDTO>();
		 for (int i = 0; i < 	 bannerPicList.getData().size(); i++) {
			 BannerEntityDTO bannerEntityDTO =new BannerEntityDTO();
			 bannerEntityDTO.setBannerImage(bannerPicList.getData().get(i).getBannerImage());
			 bannerList.add(bannerEntityDTO);
		}
		 BannerListDTO banner= new BannerListDTO();
		 banner.setBannerList(bannerList);
		return ResultGenerator.genSuccessResult(null, banner);
	}
}
