package com.dl.shop.order.service;

import com.dl.base.service.AbstractService;
import com.dl.shop.order.dao2.UserMapper2;
import com.dl.shop.order.model.DlUserAuths;
import com.dl.shop.order.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional("transactionManager2")
public class UserService2 extends AbstractService<User> {
 
	@Resource
	private UserMapper2 userMapper2;

	public List<User> getUserInfo(String mobile) {
		return	userMapper2.getUserInfo(mobile);
	}

	public User getUserInfoById(Integer userId){
		return userMapper2.getUserInfoById(userId);
	}

	public DlUserAuths queryBindsUser(Integer userId) {
        return userMapper2.getUserAuthById(userId);
    }
}
