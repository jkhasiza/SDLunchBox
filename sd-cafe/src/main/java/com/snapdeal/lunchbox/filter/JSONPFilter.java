/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.snapdeal.lunchbox.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @version 1.0, Sep 5, 2015
 * @author jitesh
 */
public class JSONPFilter extends OncePerRequestFilter {
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException("This filter can " + " only process HttpServletRequest requests");
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (isJSONPRequest(request)) {
            ServletOutputStream out = response.getOutputStream();

            out.println(getCallbackMethod(httpRequest) + "(");
            chain.doFilter(request, response);
            out.println(");");
            response.setContentType("text/javascript");
        } else {
            chain.doFilter(request, response);
        }
    }

    private String getCallbackMethod(HttpServletRequest httpRequest) {
        return httpRequest.getParameter("callback");
    }

    private boolean isJSONPRequest(HttpServletRequest httpRequest) {
        String callbackMethod = getCallbackMethod(httpRequest);
        return (callbackMethod != null && callbackMethod.length() > 0);
    }
}
