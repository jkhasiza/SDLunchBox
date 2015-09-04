/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */  
package com.snapdeal.lunchbox.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snapdeal.lunchbox.bean.AccountBean;
import com.snapdeal.lunchbox.bean.CafeMeterBean;
import com.snapdeal.lunchbox.bean.ResponseBean;
import com.snapdeal.lunchbox.bean.UserGroupRequestBean;
import com.snapdeal.lunchbox.service.CafeServiceInterface;

/**
 *  
 *  @version     1.0, 04-Sep-2015
 *  @author mukund
 */
@Controller
@RequestMapping("/cafe")
public class CafeController {
    private static final Logger     LOGGER = LoggerFactory.getLogger(CafeController.class);

    @Autowired
    private CafeServiceInterface cafeServiceInterface;
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseBean<String> login(@RequestBody AccountBean accountBean) {
        try {
            cafeServiceInterface.login(accountBean);
            return new ResponseBean<>("");
        } catch(Exception e) {
            LOGGER.error("Exception occurred while creating account", e);
           return setError(e.getMessage());
        }
    }
    
    private ResponseBean<String> setError(String error) {
        ResponseBean<String> responseBean = new ResponseBean<String>();
        responseBean.setStatusCode("ERROR");
        responseBean.setMessage(error);
        return responseBean;
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseBean<CafeMeterBean> getCafeStatus() {
        try {
            return new ResponseBean<>(cafeServiceInterface.getCafeStatus());
        } catch(Exception e) {
            LOGGER.error("Exception occurred while getting cafe status", e);
            return null;
        }
    }
    
    @RequestMapping(value = "/group", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseBean<UserGroupRequestBean> getGroupInfo(@RequestParam("phoneNumber") String phoneNumber) {
        try {
            return new ResponseBean<>(cafeServiceInterface.getGroupInfo(phoneNumber));
        } catch(Exception e ){
            return null;
        }
    }
    
    @RequestMapping(value = "/pendingGroup", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseBean<UserGroupRequestBean> getPendingUsers(@RequestParam("phoneNumber") String phoneNumber) {
        try {
            return new ResponseBean<>(cafeServiceInterface.getPendingUsers(phoneNumber));
        } catch(Exception e) {
            return null;
        }
    }
    
    @RequestMapping(value="/createGroup", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseBean<?> createGroup(@RequestBody UserGroupRequestBean userGroupRequestBean) {
        try {
            cafeServiceInterface.createGroup(userGroupRequestBean);
            return new ResponseBean<>();
        } catch(Exception e) {
            return null;
        }
    }
}