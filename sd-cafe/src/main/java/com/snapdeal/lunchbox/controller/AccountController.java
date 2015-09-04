/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snapdeal.lunchbox.bean.AccountBean;
import com.snapdeal.lunchbox.bean.ResponseBean;
import com.snapdeal.lunchbox.mao.RushInfoMao;

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
    accountMao.getRusInfoList();
    accountMao.getCurrentUsers();
        return "hello";
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json",value ="login")
    @ResponseBody
    public ResponseBean<?> login(@RequestBody AccountBean accountBean) {
        try {
            accountMao.getCurrentUsers();
            return new ResponseBean<>();
        } catch(Exception e) {
           return null;
        }
    }
    @RequestMapping(method = RequestMethod.POST, produces = "application/json" , value ="test")
    @ResponseBody
    public String findAd() {
    accountMao.getRusInfoList();
    accountMao.getCurrentUsers();
        return "hello";
    }
}
