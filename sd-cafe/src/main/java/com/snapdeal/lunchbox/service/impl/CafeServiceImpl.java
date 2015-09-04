/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snapdeal.lunchbox.bean.AccountBean;
import com.snapdeal.lunchbox.bean.CafeMeterBean;
import com.snapdeal.lunchbox.bean.UserGroupRequestBean;
import com.snapdeal.lunchbox.helper.CafeConvertor;
import com.snapdeal.lunchbox.mao.AccountMao;
import com.snapdeal.lunchbox.mao.RushInfoMao;
import com.snapdeal.lunchbox.mongo.entity.Account;
import com.snapdeal.lunchbox.mongo.entity.Buddy;
import com.snapdeal.lunchbox.mongo.entity.BuddyGroup;
import com.snapdeal.lunchbox.mongo.entity.RushInfo;
import com.snapdeal.lunchbox.service.CafeServiceInterface;

/**
 * @version 1.0, 04-Sep-2015
 * @author mukund
 */

@Service
public class CafeServiceImpl implements CafeServiceInterface {

    @Autowired
    private AccountMao  accountMao;
    @Autowired
    private RushInfoMao rusInfoMao;
    @Autowired
    private CafeConvertor cafeConvertor;
    
    @Override
    public String findAdById() {
        // TODO Auto-generated method stub
        return null;
    }

    public Account login(AccountBean account) {
        Account dbAccount = accountMao.getAccountByPhoneNo(account.getMobileNumber());
        if (null == dbAccount) {
            Account newaccount = new Account();
            newaccount.setDeviceId(account.getDeviceId());
            newaccount.setMobileNumber(account.getMobileNumber());
            accountMao.saveAccount(newaccount);
            return newaccount;
        }else{
            dbAccount = accountMao. updateAccountDeviceId(dbAccount.getId(), account.getDeviceId());
        }
        return dbAccount;
    }

    @Override
    public CafeMeterBean getCafeStatus() {
        RushInfo rushInfo = rusInfoMao.getCurrentUsers();
        if (null != rushInfo) {
            CafeMeterBean cafeMeterBean = new CafeMeterBean(rushInfo);
            return cafeMeterBean;
        }
        return null;
    }

    @Override
    public UserGroupRequestBean getGroupInfo(String phoneNumber) {
        Account account = accountMao.getAccountByPhoneNo(phoneNumber);
        return convertor(account);
    }

    private UserGroupRequestBean convertor(Account account) {
        UserGroupRequestBean userGroupRequestBean = new UserGroupRequestBean();
        
        if(account != null) {
            userGroupRequestBean.setUserId(account.getMobileNumber());
            userGroupRequestBean.setBuddyGroup(account.getBuddyGroup());
        }
        return userGroupRequestBean;
    }
    @Override
    public UserGroupRequestBean getPendingUsers(String phoneNumber) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Account createGroup(UserGroupRequestBean userGroupRequestBean) {
        if(userGroupRequestBean.getBuddyGroup() != null) {
            return accountMao.createOrUpdateAccountGroup(userGroupRequestBean.getUserId(), userGroupRequestBean.getBuddyGroup());
        } else {
            return null;
        }
    }

    @Override
    public Account updateGroup(UserGroupRequestBean userGroupRequestBean) {
        Account account = accountMao.getAccountByPhoneNo(userGroupRequestBean.getUserId());
        if(account != null && userGroupRequestBean.getBuddyGroup() != null) {
            BuddyGroup buddyGroup = account.getBuddyGroup();
            Set<Buddy> buddies = new HashSet<Buddy>();
            if(buddyGroup != null) {
                buddies = buddyGroup.getBuddies();
            } 
            buddies.addAll(userGroupRequestBean.getBuddyGroup().getBuddies());
            buddyGroup.setBuddies(buddies);
            return accountMao.createOrUpdateAccountGroup(userGroupRequestBean.getUserId(), buddyGroup);
        } else {
            return null;
        }
    }
}
