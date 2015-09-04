/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */  
package com.snapdeal.lunchbox.bean;

import java.io.Serializable;
import java.util.List;

/**
 *  
 *  @version     1.0, 04-Sep-2015
 *  @author mukund
 */
public class UserGroupRequestBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private List<String> users;
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public List<String> getUsers() {
        return users;
    }
    public void setUsers(List<String> users) {
        this.users = users;
    }
    
}
