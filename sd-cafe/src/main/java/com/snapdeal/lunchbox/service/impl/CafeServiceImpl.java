/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.service.impl;

import org.springframework.stereotype.Service;

import com.snapdeal.lunchbox.bean.CafeMeterBean;
import com.snapdeal.lunchbox.bean.UserGroupRequestBean;
import com.snapdeal.lunchbox.service.CafeServiceInterface;

/**
 * @version 1.0, 04-Sep-2015
 * @author mukund
 */

@Service
public class CafeServiceImpl implements CafeServiceInterface {

    @Override
    public String findAdById() {
        // TODO Auto-generated method stub
        return null;
    }

    public String login(String phoneNumber) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CafeMeterBean getCafeStatus() {
        // TODO Auto-generated method stub
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
