/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */  
package com.snapdeal.lunchbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.snapdeal.lunchbox.service.CafeServiceInterface;

/**
 *  
 *  @version     1.0, 04-Sep-2015
 *  @author mukund
 */
@Controller
public class CafeController {
    @Autowired
    private CafeServiceInterface cafeServiceInterface;
    
    
}
