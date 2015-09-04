/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snapdeal.lunchbox.bean.AccountBean;
import com.snapdeal.lunchbox.bean.CafeMeterBean;
import com.snapdeal.lunchbox.bean.UserGroupRequestBean;
import com.snapdeal.lunchbox.mao.AccountMao;
import com.snapdeal.lunchbox.mao.RushInfoMao;
import com.snapdeal.lunchbox.mongo.entity.Account;
import com.snapdeal.lunchbox.mongo.entity.RushInfo;
import com.snapdeal.lunchbox.service.CafeServiceInterface;

/**
 * @version 1.0, 04-Sep-2015
 * @author mukund
 */

@Service
public class CafeServiceImpl implements CafeServiceInterface {

    @Autowired private AccountMao accountMao;
    @Autowired private RushInfoMao rusInfoMao;
    
    
    @Override
    public String findAdById() {
        // TODO Auto-generated method stub
        return null;
    }

    public void login(AccountBean account) {
        Account newaccount = new Account();
        newaccount.setDeviceId(account.getDeviceId());
        newaccount.setMobileNumber(account.getMobileNumber());
        accountMao.saveAccount(newaccount);
    }

    @Override
    public CafeMeterBean getCafeStatus() {
        RushInfo rushInfo = rusInfoMao.getCurrentUsers();
        if(null != rushInfo){
            CafeMeterBean cafeMeterBean=  new CafeMeterBean(rushInfo);
            return cafeMeterBean;
        }
        return null;
    }

    @Override
    public UserGroupRequestBean getGroupInfo(String phoneNumber) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserGroupRequestBean getPendingUsers(String phoneNumber) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void createGroup(UserGroupRequestBean userGroupRequestBean) {
        // TODO Auto-generated method stub
        
    }
}
