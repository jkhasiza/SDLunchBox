/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.service;

import com.snapdeal.lunchbox.bean.AccountBean;
import com.snapdeal.lunchbox.bean.CafeMeterBean;
import com.snapdeal.lunchbox.bean.UserGroupRequestBean;
import com.snapdeal.lunchbox.mongo.entity.Account;

/**
 * @version 1.0, 04-Sep-2015
 * @author mukund
 */
public interface CafeServiceInterface {
    public String findAdById();
    public Account login(AccountBean account);
    public CafeMeterBean getCafeStatus();
    public UserGroupRequestBean getGroupInfo(String phoneNumber);
    public UserGroupRequestBean getPendingUsers(String phoneNumber);
    public Account createGroup(UserGroupRequestBean userGroupRequestBean);
}
