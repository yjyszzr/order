package com.dl.order.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GoodsOrderParam implements Serializable{

	@ApiModelProperty(value="订单Id")
	private Integer orderId;
	
	@ApiModelProperty(value="数量")
    private Integer num;
	
	@ApiModelProperty(value="电话")
    private String phone;
	
	@ApiModelProperty(value="地址")
	private String address;
	
	@ApiModelProperty(value="联系人")
    private String contactsName;
}
