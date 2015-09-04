/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */  
package com.snapdeal.lunchbox.mao.impl;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.snapdeal.lunchbox.mao.UserOtpMao;
import com.snapdeal.lunchbox.mongo.entity.Account;
import com.snapdeal.lunchbox.mongo.entity.UserOtp;

/**
 *  
 *  @version     1.0, 04-Sep-2015
 *  @author himani
 */
@Repository
public class UserOtpMaoImpl implements UserOtpMao{
    public static final AtomicLong idCounter = new AtomicLong(0);

    @Autowired
    private MongoOperations        mongoOperations;

    @Override
    public void saveUserOtp(UserOtp userOtp) {
       Long id= getId();
       userOtp.setId(id);
       mongoOperations.save(userOtp);
       
    }
    @Override
    public UserOtp getOTP(String mobileNumber) {
       Criteria criteria = Criteria.where("mobileNumber").is(mobileNumber);
       UserOtp userOtp = mongoOperations.findOne(new Query(criteria), UserOtp.class);
       return userOtp;
    }
    @Override
    public UserOtp updateUserOtp(Long id , String otp) {
        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(id);
        query.addCriteria(criteria);
        Update update = new Update();
        update.set("otp", otp);
        UserOtp userOtp = mongoOperations.findAndModify(query, update, UserOtp.class);
        return userOtp;
    }
    private Long getId() {
        if (idCounter.get() == 0) {
            Query query = new Query();
            query.with(new Sort(Sort.Direction.DESC, "_id"));
            UserOtp dbObj = mongoOperations.findOne(query, UserOtp.class);
            if (dbObj != null ) {
                idCounter.compareAndSet(0, (Long) dbObj.getId());
            } else {
                idCounter.set(0);
            }
        }
        return idCounter.incrementAndGet();
    }

}
