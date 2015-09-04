/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.snapdeal.lunchbox.bean.AccountBean;
import com.snapdeal.lunchbox.bean.CafeMeterBean;
import com.snapdeal.lunchbox.bean.UserGroupRequestBean;
import com.snapdeal.lunchbox.helper.CafeConvertor;
import com.snapdeal.lunchbox.mao.AccountMao;
import com.snapdeal.lunchbox.mao.RushInfoMao;
import com.snapdeal.lunchbox.mao.RushPredictionMao;
import com.snapdeal.lunchbox.mao.UserArrivalMao;
import com.snapdeal.lunchbox.mao.UserOtpMao;
import com.snapdeal.lunchbox.mongo.entity.Account;
import com.snapdeal.lunchbox.mongo.entity.Buddy;
import com.snapdeal.lunchbox.mongo.entity.BuddyGroup;
import com.snapdeal.lunchbox.mongo.entity.RushInfo;
import com.snapdeal.lunchbox.mongo.entity.UserArrivalInfo;
import com.snapdeal.lunchbox.mongo.entity.UserOtp;
import com.snapdeal.lunchbox.service.CafeServiceInterface;
import com.snapdeal.lunchbox.service.TwillioServiceInterface;

/**
 * @version 1.0, 04-Sep-2015
 * @author mukund
 */

@Service
public class CafeServiceImpl implements CafeServiceInterface {

    @Autowired
    private AccountMao              accountMao;
    @Autowired
    private RushInfoMao             rusInfoMao;
    @Autowired
    private CafeConvertor           cafeConvertor;
    @Autowired
    private UserArrivalMao          userArrivalMao;
    @Autowired
    private RushPredictionMao       rushPredictionMao;

    @Autowired
    private TwillioServiceInterface twillioServiceInterface;
    @Autowired
    private UserOtpMao              userOtpMao;

    @Override
    public Account getUser(String mobileNumber) {
        Account account = accountMao.getAccountByPhoneNo(mobileNumber);
        return account;
    }

    public void login(String mobileNo,String deviceId) {
        String message = "Your otp for SdCafe app is $otp";
        String otp = twillioServiceInterface.messageTest(mobileNo, message);
        if (!StringUtils.isEmpty(otp)) {
            UserOtp dbuserOtp = userOtpMao.getOTP(mobileNo);
            if (null == dbuserOtp) {
                UserOtp userOtp = new UserOtp();
                userOtp.setOtp(otp);
                userOtp.setMobileNumber(mobileNo);
                userOtpMao.saveUserOtp(userOtp);
            }else{
                userOtpMao.updateUserOtp(dbuserOtp.getId(), otp);
            }
        }
    }

    @Override
    public Account verifyOtp(AccountBean account) {
        UserOtp userOtp = userOtpMao.getOTP(account.getMobileNumber());
        if (null != userOtp && userOtp.getOtp().equalsIgnoreCase(account.getOtp())) {
            Account dbAccount = accountMao.getAccountByPhoneNo(account.getMobileNumber());
            if (null == dbAccount) {
                Account newaccount = new Account();
                newaccount.setDeviceId(account.getDeviceId());
                newaccount.setMobileNumber(account.getMobileNumber());
                accountMao.saveAccount(newaccount);
                return newaccount;
            } else {
                dbAccount = accountMao.updateAccountDeviceId(dbAccount.getId(), account.getDeviceId());
            }
            return dbAccount;
        }
        return null;
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

        if (account != null) {
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
        if (userGroupRequestBean.getBuddyGroup() != null) {
            return accountMao.createOrUpdateAccountGroup(userGroupRequestBean.getUserId(), userGroupRequestBean.getBuddyGroup());
        } else {
            return null;
        }
    }

    @Override
    public Account updateGroup(UserGroupRequestBean userGroupRequestBean) {
        Account account = accountMao.getAccountByPhoneNo(userGroupRequestBean.getUserId());
        if (account != null && userGroupRequestBean.getBuddyGroup() != null) {
            BuddyGroup buddyGroup = account.getBuddyGroup();
            Set<Buddy> buddies = new HashSet<Buddy>();
            if (buddyGroup != null) {
                buddies = buddyGroup.getBuddies();
            }
            buddies.addAll(userGroupRequestBean.getBuddyGroup().getBuddies());
            buddyGroup.setBuddies(buddies);
            return accountMao.createOrUpdateAccountGroup(userGroupRequestBean.getUserId(), buddyGroup);
        } else {
            return null;
        }
    }

    @Override
    public void updateUserArrivalInfo(List<String> mobileNumbers, Date date) {
        for (String mobile : mobileNumbers) {
            UserArrivalInfo userInfo = userArrivalMao.findUserByMobile(mobile);
            if (userInfo != null) {
                if (userInfo.getEstimatedArrival().compareTo(date) != 0) {
                    userInfo.setEstimatedArrival(date);
                    userArrivalMao.updateUser(userInfo);
                    rushPredictionMao.updateWithIncrement(userInfo.getEstimatedArrival(), -1);
                    rushPredictionMao.updateWithIncrement(date, 1);
                }
            } else {
                userInfo = new UserArrivalInfo();
                userInfo.setEstimatedArrival(date);
                userInfo.setMobileNumber(mobile);
                userArrivalMao.saveUserArrival(userInfo);
                rushPredictionMao.updateWithIncrement(date, 1);
            }
        }
    }
}
