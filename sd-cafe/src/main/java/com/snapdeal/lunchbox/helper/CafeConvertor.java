/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */  
package com.snapdeal.lunchbox.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snapdeal.lunchbox.bean.UserGroupRequestBean;
import com.snapdeal.lunchbox.mao.AccountMao;
import com.snapdeal.lunchbox.mongo.entity.Account;

/**
 *  
 *  @version     1.0, 04-Sep-2015
 *  @author mukund
 */
@Component
public class CafeConvertor {
    @Autowired
    private AccountMao accountMao;
    
    public Account createGroupMerge(UserGroupRequestBean userGroupRequestBean) {
        if(userGroupRequestBean.getBuddyGroup() != null) {
            return accountMao.createAccountGroup(userGroupRequestBean.getUserId(), userGroupRequestBean.getBuddyGroup());
        } else {
            return null;
        }
    }
}
