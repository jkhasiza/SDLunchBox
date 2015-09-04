/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */  
package com.snapdeal.lunchbox.mao.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.snapdeal.lunchbox.mao.OrderMao;
import com.snapdeal.lunchbox.mongo.entity.Account;
import com.snapdeal.lunchbox.mongo.entity.BuyFoodInfo;

/**
 *  
 *  @version     1.0, 05-Sep-2015
 *  @author himani
 */
@Repository
public class OrderMaoImpl implements OrderMao{

    public static final AtomicLong idCounter = new AtomicLong(0);
    @Autowired
    private MongoOperations        mongoOperations;
    
    @Override
    public void saveOrderInfo(BuyFoodInfo buyFoodInfo) {
        Long id = getId();
        buyFoodInfo.setId(id);
        mongoOperations.save(buyFoodInfo);
    }
    @Override
    public List<BuyFoodInfo> getOrderInfo() {
        return mongoOperations.findAll(BuyFoodInfo.class);
    }
    private Long getId() {
        if (idCounter.get() == 0) {
            Query query = new Query();
            query.with(new Sort(Sort.Direction.DESC, "_id"));
            BuyFoodInfo dbObj = mongoOperations.findOne(query, BuyFoodInfo.class);
            if (dbObj != null) {
                idCounter.compareAndSet(0, (Long) dbObj.getId());
            } else {
                idCounter.set(0);
            }
        }
        return idCounter.incrementAndGet();
    }

}
