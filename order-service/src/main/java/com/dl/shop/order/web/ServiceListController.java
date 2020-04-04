package com.dl.shop.order.web;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dl.base.result.BaseResult;
import com.dl.member.param.StrParam;
import com.dl.order.dto.ServDTO;
import com.dl.shop.order.service.ServService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/***
 * 服务列表
 * @date 2018.11.30
 */
@Api(value="服务列表",hidden=true)
@Slf4j
@RestController
@RequestMapping("/serv")
public class ServiceListController {
	
	@Resource
	private ServService servService;
	
	@ApiOperation(value = "服务列表", notes = "服务列表")
    @PostMapping("/servlist")
    public BaseResult<List<ServDTO>> listAll(@Valid @RequestBody StrParam param) {
		log.info("[listAll]");
		return servService.listAll();
    }
}
