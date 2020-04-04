package com.dl.shop.order.dao2;

import com.dl.base.mapper.Mapper;
import com.dl.shop.order.model.DlUserAuths;
import com.dl.shop.order.model.User;

import java.util.List;

public interface UserMapper2 extends Mapper<User> {

	User getUserInfoById(Integer userId);

	List<User> getUserInfo(String mobile);

	DlUserAuths getUserAuthById(Integer userId);

}