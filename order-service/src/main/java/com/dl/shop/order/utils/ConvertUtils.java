package com.dl.shop.order.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.dl.shop.order.model.AOrder;
import com.dl.shop.order.model.Order;

public class ConvertUtils {

	
	public static final List<Order> convertOrderList(List<AOrder> mList){
		List<Order> rList = new ArrayList<Order>();
		for(AOrder a : mList) {
			if(a != null) {
				Order order = convertOrder(a);
				rList.add(order);
			}
		}
		return rList;
	}
	
	public static Order convertOrder(AOrder a) {
		Order order = new Order();
		try {
			BeanUtils.copyProperties(order,a);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return order;
	}
}
