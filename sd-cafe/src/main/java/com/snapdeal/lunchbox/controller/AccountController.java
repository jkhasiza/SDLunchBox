/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snapdeal.lunchbox.mao.AccountMao;
import com.snapdeal.lunchbox.mao.RushInfoMao;
import com.snapdeal.lunchbox.mongo.entity.Account;
import com.snapdeal.lunchbox.mongo.entity.RushInfo;

/**
 * @version 1.0, 04-Sep-2015
 * @author himani
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private RushInfoMao accountMao;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String findAdById() {
        RushInfo rush = new RushInfo();
        rush.setUsers(5);
        rush.setDate(new Date());
        accountMao.saveRushInfo(rush);
        return "hello";
    }
}
