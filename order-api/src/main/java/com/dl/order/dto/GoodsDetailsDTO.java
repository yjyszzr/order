package com.dl.order.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GoodsDetailsDTO {
	
	@ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "付款人数")
    private String paidNum;

    @ApiModelProperty(value = "历史价格")
    private String historyPrice;

    @ApiModelProperty(value = "现在价格")
    private String presentPrice;
    
    @ApiModelProperty(value = "轮播图List")
    private List<GoodsPicDTO> bannerList;
    
    @ApiModelProperty(value = "详情图List")
    private List<GoodsPicDTO>  detailPicList;
    
    @ApiModelProperty(value = "基本属性")
    private List<String> baseAttributeList;
	
}
