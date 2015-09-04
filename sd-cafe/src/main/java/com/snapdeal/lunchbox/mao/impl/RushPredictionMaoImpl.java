/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.mao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.snapdeal.lunchbox.mao.RushPredictionMao;
import com.snapdeal.lunchbox.mongo.entity.RushPrediction;

/**
 * @version 1.0, Sep 4, 2015
 * @author jitesh
 */
@Repository
public class RushPredictionMaoImpl implements RushPredictionMao {
    public static final AtomicLong        idCounter  = new AtomicLong(0);
    @Autowired
    private MongoOperations               mongoOperations;

    private static final SimpleDateFormat dt         = new SimpleDateFormat("HH:mm--dd/MM/yy");

    public static final long              DELTA_TIME = 3 * 3600 * 1000;

    @Override
    public void save(RushPrediction rushObject) {
        if (rushObject.getId() == null) {
            rushObject.setId(getId());
            mongoOperations.save(rushObject);
        } else {
            update(rushObject);
        }
    }

    @Override
    public void update(RushPrediction rushObject) {
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is(rushObject.getId());
        query.addCriteria(criteria);
        Update update = new Update();
        update.set("userCount", rushObject.getUserCount());
        update.set("date", rushObject.getDate());
        mongoOperations.findAndModify(query, update, RushPrediction.class);
    }

    @Override
    public void updateWithIncrement(Date date, int increment) {
        try {
            Date dbDate = dt.parse(date.toString());
            Query query = new Query();
            Criteria criteria = Criteria.where("date").is(dbDate);
            RushPrediction dbRushPrediction = mongoOperations.findOne(query, RushPrediction.class);
            query.addCriteria(criteria);
            Update update = new Update();
            update.set("userCount", dbRushPrediction.getUserCount() + increment);
            mongoOperations.findAndModify(query, update, RushPrediction.class);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RushPrediction> getRushPredictionList() {
        Date startTime = new Date();
        Date endTime = new Date(startTime.getTime() + DELTA_TIME);
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "date"));
        Criteria criteria = Criteria.where("date").gte(startTime).lt(endTime);
        query.addCriteria(criteria);
        List<RushPrediction> rushPredictionList = mongoOperations.find(query, RushPrediction.class);
        return rushPredictionList;
    }

    private Long getId() {
        if (idCounter.get() == 0) {
            Query query = new Query();
            query.with(new Sort(Sort.Direction.DESC, "_id"));
            RushPrediction rushPrediction = mongoOperations.findOne(query, RushPrediction.class);
            if (rushPrediction != null) {
                idCounter.compareAndSet(0, (Long) rushPrediction.getId());
            } else {
                idCounter.set(0);
            }
        }
        return idCounter.incrementAndGet();
    }
}
