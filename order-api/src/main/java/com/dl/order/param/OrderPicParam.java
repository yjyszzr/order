package com.dl.order.param;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单图片地址
 *
 * @author zhangzirong
 */
@Data
public class OrderPicParam {
	
    @ApiModelProperty(value = "订单图片地址")
    @NotBlank(message = "订单图片地址不能为空")
    private String orderPic;
    
    @ApiModelProperty(value = "订单号")
    @NotBlank(message = "订单号不能为空")
    private String orderSn;
    
    @ApiModelProperty(value = "店铺ID")
    private Integer storeId;
    
    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;
}
