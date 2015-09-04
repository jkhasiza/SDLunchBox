/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */  
package com.snapdeal.lunchbox.bean;

import java.io.Serializable;

import com.snapdeal.lunchbox.mongo.entity.BuddyGroup;

/**
 *  
 *  @version     1.0, 04-Sep-2015
 *  @author mukund
 */
public class UserGroupRequestBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private BuddyGroup      buddyGroup;
   
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public BuddyGroup getBuddyGroup() {
        return buddyGroup;
    }
    public void setBuddyGroup(BuddyGroup buddyGroup) {
        this.buddyGroup = buddyGroup;
    }
   
}
