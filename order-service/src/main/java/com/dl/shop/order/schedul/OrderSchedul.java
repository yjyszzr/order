/*package com.dl.shop.order.schedul;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.dl.shop.order.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableScheduling
public class OrderSchedul {

	@Resource
	private OrderService orderService;
	*//**
	 * 订单详情赛果 （每5分钟执行一次）
	 *//*
	@Scheduled(cron = "0 0/2 * * * ?")
    public void updateOrderMatchResult() {
		log.info("开始执行更新订单详情赛果任务");
		orderService.updateOrderMatchResult();
		log.info("结束执行更新订单详情赛果任务");
	}
	
	*//**
	 * 订单出票结果 （每5分钟执行一次）
	 * 这里暂时主要处理出票失败的订单
	 *//*
	@Scheduled(cron = "0 0/1 * * * ?")
	public void refreshOrderPrintStatus() {
		log.info("开始执行更新订单出票结果任务");
		orderService.refreshOrderPrintStatus();
		log.info("结束执行更新订单出票结果任务");
	}
	
	*//**
	 * 更新中奖用户的账户
	 *//*
	@Scheduled(cron = "0 0/1 * * * ?")
	public void addRewardMoneyToUsers() {
		log.info("更新中奖用户的账户，派奖开始");
		orderService.addRewardMoneyToUsers();
		log.info("更新中奖用户的账户，派奖结束");
		
	}
}
*/