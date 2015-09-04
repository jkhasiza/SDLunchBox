/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.snapdeal.lunchbox.mao.RushInfoMao;
import com.snapdeal.lunchbox.mao.RushPredictionMao;
import com.snapdeal.lunchbox.mongo.entity.RushInfo;
import com.snapdeal.lunchbox.mongo.entity.RushPrediction;

/**
 * @version 1.0, Sep 4, 2015
 * @author jitesh
 */
@Service
public class CrawlerService {
    @Autowired
    private RestTemplate                  restTemplate;

    @Autowired
    private RushInfoMao                   rushMaoInfo;

    @Autowired
    private RushPredictionMao             rushPrediction;

    private static final SimpleDateFormat dt              = new SimpleDateFormat("HH:mm--dd/MM/yy");
    private static final int              PREDICTION_MINS = 28;

    @Scheduled(fixedDelay = 2 * 60 * 1000)
    public void getCafeUsers() {
        try {
            ResponseEntity<String> response = restTemplate.exchange("http://172.20.1.116/results.txt", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
            });

            if (response != null && response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                String networkString = response.getBody();
                String[] words = networkString.split(" ");
                Integer noOfUsers = 0;
                try {
                    noOfUsers = Integer.parseInt(words[2]);
                } catch (NumberFormatException e) {
                    noOfUsers = 0;
                }
                Date lastAccessedTime = null;
                try {
                    String date = words[8].substring(0, words[8].length() - 2);
                    lastAccessedTime = dt.parse(date);
                    updatePrediction(lastAccessedTime, noOfUsers);
                    RushInfo rushInfo = new RushInfo();
                    rushInfo.setDate(lastAccessedTime);
                    rushInfo.setUsers(noOfUsers);
                    rushMaoInfo.saveRushInfo(rushInfo);
                    System.out.println(noOfUsers + lastAccessedTime.toString());
                } catch (ParseException e) {
                }
            }
        } catch (Exception e) {

        }
    }

    public void updatePrediction(Date startTime, int noOfUsers) {
        RushInfo rush = rushMaoInfo.getCurrentUsers();
        int deltaUsers = rush.getUsers() - noOfUsers;
        if (deltaUsers > 0) {
            updatePrecdiction(deltaUsers);
        }
    }

    private void updatePrecdiction(int deltaUsers) {
        Map<String, RushPrediction> predictionMap = rushMaoInfo.getRushPredictionList();
        Date currTime = new Date();
        String currTimeString = null;
        try {
            Date currTimeInMins = dt.parse(currTime.toString());
            for (int i = 0; i < PREDICTION_MINS; i++) {
                currTimeString = currTimeInMins.toString();
                RushPrediction prediction = predictionMap.get(currTimeString);
                if (prediction == null) {
                    prediction = new RushPrediction();
                    try {
                        prediction.setDate(dt.parse(currTimeString));
                        prediction.setUserCount(0);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                prediction.setUserCount(prediction.getUserCount() + deltaUsers);
                currTimeInMins = new Date(currTimeInMins.getTime() + 60 * 1000);
            }
            predictionMap.get(currTimeString);
        } catch (ParseException e) {
        }
    }
}
