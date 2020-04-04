package com.dl.shop.order.web;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value="订单日志接口",hidden=true)
@RestController
@RequestMapping("/order/log")
public class OrderLogController {

}
