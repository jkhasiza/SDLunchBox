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
            System.out.println("Crawler Service Started..");
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
                    int adjustedUsers = noOfUsers * 2;
                    lastAccessedTime = dt.parse(date);
                    updatePrediction(lastAccessedTime, adjustedUsers);
                    RushInfo rushInfo = new RushInfo();
                    rushInfo.setDate(lastAccessedTime);
                    rushInfo.setUsers(adjustedUsers);
                    
                    rushMaoInfo.saveRushInfo(rushInfo);
                    System.out.println(noOfUsers + lastAccessedTime.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

        }
    }

    public void updatePrediction(Date startTime, int noOfUsers) {
        RushInfo rush = rushMaoInfo.getCurrentUsers();
        int deltaUsers = noOfUsers - rush.getUsers();
        if (deltaUsers > 0) {
            updatePrecdiction(rush.getUsers(), deltaUsers);
        }
    }

    private void updatePrecdiction(int currentUsers, int deltaUsers) {
        Map<String, RushPrediction> predictionMap = rushMaoInfo.getRushPredictionList();
        Date currTime = new Date();
        String currTimeString = null;
        try {
            Date currTimeInMins = dt.parse(dt.format(currTime));
            for (int i = 0; i < PREDICTION_MINS; i++) {
                currTimeString = dt.format(currTimeInMins);
                RushPrediction prediction = predictionMap.get(currTimeString);
                if (prediction == null) {
                    prediction = new RushPrediction();
                    try {
                        prediction.setDate(dt.parse(currTimeString));
                        prediction.setUserCount(currentUsers);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                prediction.setUserCount(prediction.getUserCount() + deltaUsers);
                rushPrediction.save(prediction);
                currTimeInMins = new Date(currTimeInMins.getTime() + 60 * 1000);
            }
            predictionMap.get(currTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
