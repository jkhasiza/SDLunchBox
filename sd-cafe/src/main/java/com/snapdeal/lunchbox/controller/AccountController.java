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

import com.snapdeal.lunchbox.mao.AccountMao;
import com.snapdeal.lunchbox.mongo.entity.Account;

/**
 * @version 1.0, 04-Sep-2015
 * @author himani
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountMao accountMao;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String findAdById() {
    Account account = new Account();
    account.setDeviceId("2");
    account.setId(3L);
    account.setMobileNumber("8897867906");
    //accountMao.getRusInfoList();
    accountMao.saveAccount(account);
    //accountMao.getCurrentUsers();
        return "hello";
    }
    
   
}
