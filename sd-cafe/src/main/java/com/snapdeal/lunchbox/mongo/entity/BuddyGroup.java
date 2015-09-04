/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.mongo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @version 1.0, 04-Sep-2015
 * @author mukund
 */
public class BuddyGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    private String            name;
    private Set<Buddy>        buddies = new HashSet<Buddy>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Buddy> getBuddies() {
        return buddies;
    }

    public void setBuddies(Set<Buddy> buddies) {
        this.buddies = buddies;
    }

}
