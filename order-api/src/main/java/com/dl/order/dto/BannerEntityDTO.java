package com.dl.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BannerEntityDTO {

    @ApiModelProperty(value = "banner大图")
    public String bannerImage;
}
