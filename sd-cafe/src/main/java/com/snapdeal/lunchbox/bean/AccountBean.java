/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */  
package com.snapdeal.lunchbox.bean;

import java.io.Serializable;

/**
 *  
 *  @version     1.0, 04-Sep-2015
 *  @author mukund
 */
public class AccountBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String            mobileNumber;
    private String            deviceId;
    
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
}
