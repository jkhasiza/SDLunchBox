/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.mao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.snapdeal.lunchbox.mao.AccountMao;
import com.snapdeal.lunchbox.mongo.entity.Account;

/**
 * @version 1.0, 04-Sep-2015
 * @author himani
 */
@Repository
public class AccountMaoImpl implements AccountMao {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void saveAccount() {
        Account account = new Account();
        account.setDeviceId("1");account.setId(1L);
        account.setGroupId(1L);
        mongoOperations.save(account);
    }

}
