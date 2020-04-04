package com.dl.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GoodsPicDTO {

	@ApiModelProperty(value = "图片")
    private String bannerImage;
 
}