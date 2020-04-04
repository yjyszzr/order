package com.dl.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GoodsDTO {
	
	@ApiModelProperty(value = "id")
	private String goodsId;
	
	@ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "付款人数")
    private String paidNum;

    @ApiModelProperty(value = "历史价格")
    private String historyPrice;

    @ApiModelProperty(value = "现在价格")
    private String presentPrice;
    
    @ApiModelProperty(value = "列表页主图")
    private String mainPic;
	
}
