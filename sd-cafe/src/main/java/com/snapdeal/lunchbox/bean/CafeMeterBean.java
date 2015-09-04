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
public class CafeMeterBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int numberOfUser;
    private int availableSeats;
   
    public int getNumberOfUser() {
        return numberOfUser;
    }
    public void setNumberOfUser(int numberOfUser) {
        this.numberOfUser = numberOfUser;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    
}
