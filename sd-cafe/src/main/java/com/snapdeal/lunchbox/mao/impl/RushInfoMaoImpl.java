/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.mao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.snapdeal.lunchbox.mao.RushInfoMao;
import com.snapdeal.lunchbox.mongo.entity.RushInfo;
import com.snapdeal.lunchbox.mongo.entity.RushPrediction;

/**
 * @version 1.0, 04-Sep-2015
 * @author himani
 */
@Repository
public class RushInfoMaoImpl implements RushInfoMao {

    public static final AtomicLong        idCounter  = new AtomicLong(0);
    @Autowired
    private MongoOperations               mongoOperations;

    public static final long              DELTA_TIME = 3 * 3600 * 1000;

    private static final SimpleDateFormat dt         = new SimpleDateFormat("HH:mm--dd/MM/yy");

    @Override
    public void saveRushInfo(RushInfo rushInfo) {
        rushInfo.setId(getId());
        mongoOperations.save(rushInfo);
    }

    @Override
    public List<RushInfo> getRushInfoList() {
        Date endTime = new Date();
        Date startTime = new Date(endTime.getTime() - DELTA_TIME);
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "date"));
        Criteria criteria = Criteria.where("date").gte(startTime).lt(endTime);
        query.addCriteria(criteria);
        List<RushInfo> rushInfoList = mongoOperations.find(query, RushInfo.class);
        return rushInfoList;

    }

    @Override
    public Map<String, RushPrediction> getRushPredictionList() {
        Date startTime = new Date();
        Date endTime = new Date(startTime.getTime() + DELTA_TIME);
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "date"));
        Criteria criteria = Criteria.where("date").gte(startTime).lt(endTime);
        query.addCriteria(criteria);
        List<RushPrediction> rushPredictionList = mongoOperations.find(query, RushPrediction.class);
        Map<String, RushPrediction> predictionMap = new HashMap<String, RushPrediction>();

        for (RushPrediction rushPrediction : rushPredictionList) {
            String dateString = rushPrediction.getDate().toString();
            try {
                predictionMap.put(dt.parse(dateString).toString(), rushPrediction);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return predictionMap;

    }

    @Override
    public RushInfo getCurrentUsers() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "date"));
        RushInfo rushInfo = mongoOperations.findOne(query, RushInfo.class);
        return rushInfo;

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
