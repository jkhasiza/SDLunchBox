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

import com.snapdeal.lunchbox.mao.UserArrivalMao;
import com.snapdeal.lunchbox.mongo.entity.UserArrivalInfo;

/**
 * @version 1.0, Sep 4, 2015
 * @author jitesh
 */
@Repository
public class UserArrivalMaoImpl implements UserArrivalMao {
    public static final AtomicLong idCounter = new AtomicLong(0);
    @Autowired
    private MongoOperations        mongoOperations;

    @Override
    public void saveUserArrival(UserArrivalInfo userArrival) {
        userArrival.setId(getId());
        mongoOperations.save(userArrival);
    }

    @Override
    public void updateUser(UserArrivalInfo userArrival) {
        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(userArrival.getId());
        query.addCriteria(criteria);
        Update update = new Update();
        update.set("estimatedArrival", userArrival.getEstimatedArrival());
        mongoOperations.findAndModify(query, update, UserArrivalInfo.class);
    }

    @Override
    public UserArrivalInfo findUserByMobile(String mobile) {
        Query query = new Query();
        Criteria criteria = Criteria.where("mobileNumber").is(mobile);
        query.addCriteria(criteria);
        UserArrivalInfo arrivalInfo = mongoOperations.findOne(query, UserArrivalInfo.class);
        return arrivalInfo;
    }

    private Long getId() {
        if (idCounter.get() == 0) {
            Query query = new Query();
            query.with(new Sort(Sort.Direction.DESC, "_id"));
            UserArrivalInfo userArrival = mongoOperations.findOne(query, UserArrivalInfo.class);
            if (userArrival != null) {
                idCounter.compareAndSet(0, (Long) userArrival.getId());
            } else {
                idCounter.set(0);
            }
        }
        return idCounter.incrementAndGet();
    }
}
