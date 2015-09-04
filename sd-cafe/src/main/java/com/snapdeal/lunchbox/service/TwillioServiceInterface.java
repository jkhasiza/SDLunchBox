/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.service;

import com.twilio.sdk.TwilioRestException;


public interface TwillioServiceInterface {
   
    public String messageTest(String To,String Text)throws TwilioRestException;
}
