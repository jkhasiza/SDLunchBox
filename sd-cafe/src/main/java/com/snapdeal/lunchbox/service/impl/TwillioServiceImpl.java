/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.instrument.classloading.tomcat.TomcatLoadTimeWeaver;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.snapdeal.lunchbox.service.TwillioServiceInterface;
import com.snapdeal.lunchbox.utils.CafeUtils;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;

@Service
public class TwillioServiceImpl implements TwillioServiceInterface {

    public static final String ACCOUNT_SID = "ACb3e06a447ecfe4eb48af9109d0163f56";
    public static final String AUTH_TOKEN  = "c92b139a79758a470934baaf8ca9a741";

    public String messageTest(String To, String text) {
        // Send an SMS (Requires version 3.4+)
        String otp = CafeUtils.generateOTP();
       try{
         text = text.replace("$otp", otp);
        String toMobile = "";
        System.out.println("send message to " + To + text);
        if (!StringUtils.isEmpty(To)) {
           toMobile = "+"+"91"+To;
        }
        final SmsFactory messageFactory = mainAccount.getSmsFactory();
        final List<NameValuePair> messageParams = new ArrayList<NameValuePair>();
        messageParams.add(new BasicNameValuePair("To", toMobile)); // Replace with a17637036155 valid phone number
        messageParams.add(new BasicNameValuePair("From", "+17637036155")); // Replace with a valid phone number in your account
        messageParams.add(new BasicNameValuePair("Body", text));
        messageFactory.create(messageParams);
       }catch(TwilioRestException e){
           return null;
       }
        return otp;
    }

    // Create a rest client
    final TwilioRestClient client      = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

    // Get the main accou   nt (The one we used to authenticate the client)
    final Account          mainAccount = client.getAccount();

}
