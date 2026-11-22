package com.pm.miniwaf.Filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Component
public class WAFfilter extends OncePerRequestFilter {
    /**
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("Incoming request to: " + request.getRequestURI() + " -type: " + request.getMethod() + " -params: " + request.getQueryString());
        Map<String,String[]> parameterMap = request.getParameterMap();

        if(parameterMap.values().stream().filter(Objects::nonNull).flatMap(Arrays::stream).anyMatch(s -> s.equalsIgnoreCase("blocked")))
        {
            response.sendError(403,"bad boy");
        }

        else{
            filterChain.doFilter(request,response);
        }


    }



}
