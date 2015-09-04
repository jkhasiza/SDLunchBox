/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */  
package com.snapdeal.lunchbox.mao;

import com.snapdeal.lunchbox.mongo.entity.UserOtp;

/**
 *  
 *  @version     1.0, 04-Sep-2015
 *  @author himani
 */
public interface UserOtpMao {

    void saveUserOtp(UserOtp userOtp);

    UserOtp getOTP(String mobileNumber);

    UserOtp updateUserOtp(Long id, String otp);

}
