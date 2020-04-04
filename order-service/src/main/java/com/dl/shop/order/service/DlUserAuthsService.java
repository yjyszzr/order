package com.dl.shop.order.service;

import com.dl.base.service.AbstractService;
import com.dl.shop.order.dao2.DlUserAuthsMapper;
import com.dl.shop.order.model.DlUserAuths;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional("transactionManager2")
public class DlUserAuthsService extends AbstractService<DlUserAuths> {
    @Resource
    private DlUserAuthsMapper dlUserAuthsMapper;

    public Integer saveThirdUser(DlUserAuths userAuths){
       return  dlUserAuthsMapper.insertWithId(userAuths);
    }

    public Integer delThirdUser(Integer userId){
        return dlUserAuthsMapper.delUserAuthById(userId);
    }

    public Boolean queryBindThird(Integer thirdUserId){
        Integer rst =  dlUserAuthsMapper.countBindThird(thirdUserId);
        if(rst > 0 ){
            return true;
        }
        return false;
    }

    public DlUserAuths queryBindsUser(Integer userId){
        return dlUserAuthsMapper.getUserAuthById(userId);
    }
}
