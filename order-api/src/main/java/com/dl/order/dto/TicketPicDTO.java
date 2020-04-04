package com.dl.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TicketPicDTO {

    @ApiModelProperty("彩票照片url")
    private String ticketPicUrl;
    
    @ApiModelProperty("彩票照片上传时间")
    private String  picAddTime;
    
    @ApiModelProperty("彩票状态:1已出票，2出票失败")
    private String  ticketStatus;
    
    @ApiModelProperty("出票失败的文字描述")
    private String  failReason;
}
