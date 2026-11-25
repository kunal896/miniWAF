package com.pm.miniwaf.Filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
public class WAFfilter extends OncePerRequestFilter {

    private final RuleEngineWAF ruleEngineWAF;
    private final boolean enabled;

    public WAFfilter(RuleEngineWAF ruleEngineWAF, @Value("${waf.enabled}") boolean enabled) {
        this.ruleEngineWAF = ruleEngineWAF;
        this.enabled = enabled;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       if(enabled){
           logger.info("Incoming request to: " + request.getRequestURI() + " -type: " + request.getMethod() + " -params: " + request.getQueryString());
           Map<String,String[]> parameterMap = request.getParameterMap();

           threatType check = ruleEngineWAF.ruleCheck(parameterMap);

           if(check==threatType.XSS){
               response.sendError(403,"bad boy");
               logger.warn("XSS Detected");
               return;
           } else if (check==threatType.SQLI) {
               response.sendError(403,"bad boy");
               logger.warn("SQLi Detected");
               return;
           }

           filterChain.doFilter(request,response);

       }else{
           filterChain.doFilter(request,response);
       }
    }



}

//this is the filter, it sees bad stuff and says bad boy that's all
//so i activate filter now
