/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.mongo.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * @version 1.0, Sep 4, 2015
 * @author jitesh
 */
public class UserArrivalInfo {
    @Id
    private Long   id;
    @Indexed(unique = true)
    private String mobileNumber;

    private Date   estimatedArrival;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getEstimatedArrival() {
        return estimatedArrival;
    }

    public void setEstimatedArrival(Date estimatedArrival) {
        this.estimatedArrival = estimatedArrival;
    }

}
