/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.mao;

import java.util.List;
import java.util.Map;

import com.snapdeal.lunchbox.mongo.entity.RushInfo;
import com.snapdeal.lunchbox.mongo.entity.RushPrediction;

/**
 * @version 1.0, 04-Sep-2015
 * @author himani
 */
public interface RushInfoMao {

    void saveRushInfo(RushInfo rushInfo);

    List<RushInfo> getRushInfoList();

    RushInfo getCurrentUsers();

    Map<String, RushPrediction> getRushPredictionList();

}
