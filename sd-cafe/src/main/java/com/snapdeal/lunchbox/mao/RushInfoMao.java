/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */  
package com.snapdeal.lunchbox.mao;

import java.util.List;

import com.snapdeal.lunchbox.mongo.entity.RushInfo;

/**
 *  
 *  @version     1.0, 04-Sep-2015
 *  @author himani
 */
public interface RushInfoMao {

    void saveRushInfo(RushInfo rushInfo);

    List<RushInfo> getRusInfoList();

}
