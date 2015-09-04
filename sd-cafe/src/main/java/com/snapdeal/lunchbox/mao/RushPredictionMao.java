/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.mao;

import java.util.Date;

import com.snapdeal.lunchbox.mongo.entity.RushPrediction;

/**
 * @version 1.0, Sep 4, 2015
 * @author jitesh
 */
public interface RushPredictionMao {
    public void save(RushPrediction rushObject);

    void update(RushPrediction rushObject);

    void updateWithIncrement(Date date, int increment);

}
