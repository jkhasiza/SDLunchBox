/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */  
package com.snapdeal.lunchbox.mongo.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *  
 *  @version     1.0, 05-Sep-2015
 *  @author himani
 */
@Document(collection = "buyFood")
public class BuyFoodInfo implements Serializable{
    @Id
    private Long              id;
    private int            noOfOrders;
    private Date            date;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getNoOfOrders() {
        return noOfOrders;
    }
    public void setNoOfOrders(int noOfOrders) {
        this.noOfOrders = noOfOrders;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
