/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.mao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
    public void saveAccount(Account account) {
        mongoOperations.save(account);
    }

    @Override
    public Account getAccountByPhoneNo(String mobileNo) {
        Criteria criteria = Criteria.where("mobileNumber").is(mobileNo);
        Account account = mongoOperations.findOne(new Query(criteria), Account.class);
        return account;
    }

    @Override
    public Account updateAccountGroup(Long groupId, String mobileNo) {
        Query query = new Query();
        Criteria criteria = Criteria.where("mobileNumber").is(mobileNo);
        query.addCriteria(criteria);
        Update update = new Update();
        update.set("groupId", groupId);
        Account account = mongoOperations.findAndModify(query, update, Account.class);
        return account;
    }

}
