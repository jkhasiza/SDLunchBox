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

import com.snapdeal.lunchbox.mao.RushInfoMao;
import com.snapdeal.lunchbox.mongo.entity.Account;
import com.snapdeal.lunchbox.mongo.entity.RushInfo;

/**
 *  
 *  @version     1.0, 04-Sep-2015
 *  @author himani
 */
@Repository
public class RushInfoMaoImpl implements RushInfoMao{
    
    public static final AtomicLong idCounter = new AtomicLong(0);
    @Autowired
    private MongoOperations        mongoOperations;
    
    @Override
    public void saveRushInfo(RushInfo rushInfo) {
        rushInfo.setId(getId());
        mongoOperations.save(rushInfo);
    }

    @Override
    public List<RushInfo> getRusInfoList(){
        
        return null;
        
    }
    private Long getId() {
        if (idCounter.get() == 0) {
            Query query = new Query();
            query.with(new Sort(Sort.Direction.DESC, "_id"));
            RushInfo rushInfo = mongoOperations.findOne(query, RushInfo.class);
            if (rushInfo != null) {
                idCounter.compareAndSet(0, (Long) rushInfo.getId());
            } else {
                idCounter.set(0);
            }
        }
        return idCounter.incrementAndGet();
    }
}
