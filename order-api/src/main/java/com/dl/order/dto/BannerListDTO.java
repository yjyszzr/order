package com.dl.order.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BannerListDTO {

    @ApiModelProperty(value = "banner列表")
    private List<BannerEntityDTO> bannerList;
}
