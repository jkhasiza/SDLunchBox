/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.mao;

import com.snapdeal.lunchbox.mongo.entity.UserArrivalInfo;

/**
 * @version 1.0, Sep 4, 2015
 * @author jitesh
 */
public interface UserArrivalMao {

    void saveUserArrival(UserArrivalInfo userArrivalObj);

    UserArrivalInfo findUserByMobile(String mobile);

    void updateUser(UserArrivalInfo userArrival);

}
