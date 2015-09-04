/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.mongo.entity;

import java.io.Serializable;

/**
 * @version 1.0, 04-Sep-2015
 * @author mukund
 */
public class Buddy implements Serializable {
    private static final long serialVersionUID = 1L;
    private String            name;
    private String            phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
