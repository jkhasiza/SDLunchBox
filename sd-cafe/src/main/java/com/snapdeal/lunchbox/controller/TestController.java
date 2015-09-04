/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */  
package com.snapdeal.lunchbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snapdeal.lunchbox.service.TwillioServiceInterface;
import com.twilio.sdk.TwilioRestException;




/**
 *  
 *  @version     1.0, 04-Sep-2015
 *  @author pronto
 */
@Controller
@RequestMapping("/twillio")
public class TestController {
    
    @Autowired
    TwillioServiceInterface twillioServiceInterface;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public void  findAdById() {
        
        try {
            twillioServiceInterface.messageTest("+918800127807", "Test Message");
        } catch (TwilioRestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
