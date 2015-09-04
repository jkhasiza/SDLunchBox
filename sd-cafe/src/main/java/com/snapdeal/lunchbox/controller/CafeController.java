/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snapdeal.lunchbox.bean.AccountBean;
import com.snapdeal.lunchbox.bean.ResponseBean;
import com.snapdeal.lunchbox.bean.UserGroupRequestBean;
import com.snapdeal.lunchbox.mongo.entity.Account;
import com.snapdeal.lunchbox.mongo.entity.Buddy;
import com.snapdeal.lunchbox.mongo.entity.BuddyGroup;
import com.snapdeal.lunchbox.service.CafeServiceInterface;

/**
 * @version 1.0, 04-Sep-2015
 * @author mukund
 */
@Controller
@RequestMapping("/cafe")
public class CafeController {
    private static final Logger  LOGGER = LoggerFactory.getLogger(CafeController.class);

    @Autowired
    private CafeServiceInterface cafeServiceInterface;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseBean<?> login(HttpServletResponse response, HttpServletRequest request, @RequestParam("mobileNumber") String mobileNumber,@RequestParam(value ="deviceId",required = false) String deviceId) {
        try {
            cafeServiceInterface.login(mobileNumber,deviceId);
            return new ResponseBean<>("0", "OK");
        } catch (Exception e) {
            LOGGER.error("Exception occurred while creating account", e);
            return new ResponseBean<>("500", "Oops! Something bad is happened");
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/user")
    @ResponseBody
    public ResponseBean<?> getUser(@RequestParam String mobileNumber) {
        try {
            Account account = cafeServiceInterface.getUser(mobileNumber);
            return new ResponseBean<Account>(account);
        } catch (Exception e) {
            LOGGER.error("Exception occurred while creating account", e);
            return new ResponseBean<>("500", "Oops! Something bad is happened");
        }
    }
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/verifyOtp")
    @ResponseBody
    public ResponseBean<?> verifyOtp(@RequestParam("mobileNumber") String mobileNumber, @RequestParam(value = "deviceId", required = false) String deviceId,
            @RequestParam(value = "otp", required = false) String otp) {
        try {
            AccountBean accountBean = new AccountBean();
            accountBean.setDeviceId(deviceId);
            accountBean.setMobileNumber(mobileNumber);
            accountBean.setOtp(otp);
            
            Account account = cafeServiceInterface.verifyOtp(accountBean);
            return new ResponseBean<Account>(account);
        } catch (Exception e) {
            LOGGER.error("Exception occurred while creating account", e);
            return new ResponseBean<>("500", "Oops! Something bad is happened");
        }
    }

    /*private ResponseBean<String> setError(String error) {
        ResponseBean<String> responseBean = new ResponseBean<String>();
        responseBean.setStatusCode("ERROR");
        responseBean.setMessage(error);
        return responseBean;
    }*/

    /*private ResponseBean<CafeMeterBean> setStatusError(String error) {
        ResponseBean<CafeMeterBean> responseBean = new ResponseBean<CafeMeterBean>();
        responseBean.setStatusCode("ERROR");
        responseBean.setMessage(error);
        return responseBean;
    }*/

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseBean<?> getCafeStatus() {
        try {
            return new ResponseBean<>(cafeServiceInterface.getCafeStatus());
            //return "jsonCallback({})";
        } catch (Exception e) {
            LOGGER.error("Exception occurred while getting cafe status", e);
            return new ResponseBean<>("500", "Oops! Something bad is happened");
        }
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseBean<UserGroupRequestBean> getGroupInfo(@RequestParam("phoneNumber") String phoneNumber) {
        try {
            return new ResponseBean<>(cafeServiceInterface.getGroupInfo(phoneNumber));
        } catch (Exception e) {
            LOGGER.error("Exception occurred while getting group ", e);
            return new ResponseBean<>("500", "Oops! Something bad is happened");
        }
    }

    /*@RequestMapping(value = "/pendingGroup", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseBean<UserGroupRequestBean> getPendingUsers(@RequestParam("phoneNumber") String phoneNumber) {
        try {
            return new ResponseBean<>(cafeServiceInterface.getPendingUsers(phoneNumber));
        } catch(Exception e) {
            return null;
        }
    }*/

    @RequestMapping(value = "/createGroup", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseBean<String> createGroup(@RequestParam(value = "userId") String userId, @RequestParam(value = "groupName") String groupName,
            @RequestParam("phoneNumbers") List<String> phoneNumbers) {
        try {
            UserGroupRequestBean userGroupRequestBean = convertor(userId, groupName, phoneNumbers);
            
            cafeServiceInterface.createGroup(userGroupRequestBean);
            return new ResponseBean<>("");
        } catch (Exception e) {
            LOGGER.error("Exception occurred while creating group", e);
            return new ResponseBean<>("500", "Oops! Something bad is happened");
        }
    }

    private UserGroupRequestBean convertor(String userId, String groupName, List<String> phoneNumbers) {
        UserGroupRequestBean userGroupRequestBean = new UserGroupRequestBean();
        
        userGroupRequestBean.setUserId(userId);
        BuddyGroup buddyGroup = new BuddyGroup();
        buddyGroup.setName(groupName);
        if(phoneNumbers != null && phoneNumbers.size() > 0) {
            Set<Buddy> buddies = new HashSet<Buddy>();
            for(String phoneNumber : phoneNumbers) {
                Buddy buddy = new Buddy();
                buddy.setPhone(phoneNumber);
                buddy.setName(phoneNumber);
                buddies.add(buddy);
            }
            buddyGroup.setBuddies(buddies);
        }
        return userGroupRequestBean;
    }
    
    @RequestMapping(value = "/updateGroup", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseBean<String> updateGroup(@RequestParam(value = "userId") String userId, @RequestParam(value = "groupName") String groupName,
            @RequestParam("phoneNumbers") List<String> phoneNumbers) {
        try {
            UserGroupRequestBean userGroupRequestBean = convertor(userId, groupName, phoneNumbers);
            cafeServiceInterface.updateGroup(userGroupRequestBean);
            return new ResponseBean<>("");
        } catch (Exception e) {
            LOGGER.error("Exception occurred while updating group", e);
            return new ResponseBean<>("500", "Oops! Something bad is happened");
        }
    }
}