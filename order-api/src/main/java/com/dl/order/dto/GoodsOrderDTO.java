package com.dl.order.dto;

import java.math.BigDecimal;
import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class GoodsOrderDTO {
    @ApiModelProperty(value = "订单Id")
    private Integer orderId;
    @ApiModelProperty(value = "单价")
    private BigDecimal price;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "主图")
    private String orderPic;
    @ApiModelProperty(value = "按钮信息")
    private String buttonInfo;
 
}