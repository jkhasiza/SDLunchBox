/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.snapdeal.lunchbox.mao.RushInfoMao;
import com.snapdeal.lunchbox.mongo.entity.RushInfo;

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

    private static final SimpleDateFormat dt = new SimpleDateFormat("HH:mm--dd/MM/yy");

    @Scheduled(fixedDelay = 2 * 60 * 1000)
    public void getCafeUsers() {
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
            } catch (ParseException e) {
            }
            RushInfo rushInfo = new RushInfo();
            rushInfo.setDate(lastAccessedTime);
            rushInfo.setUsers(noOfUsers);
            rushMaoInfo.saveRushInfo(rushInfo);
            System.out.println(noOfUsers + lastAccessedTime.toString());
        }
    }
}
