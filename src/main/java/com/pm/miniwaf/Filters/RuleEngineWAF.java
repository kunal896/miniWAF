package com.pm.miniwaf.Filters;


import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class RuleEngineWAF {


    public threatType ruleCheck(Map<String,String[]> parameterMap ){
       for(String str[]: parameterMap.values()){
           for(String s: str){

               if(s.toLowerCase().contains("<script")){

                   return threatType.XSS;
               }

               if(s.equalsIgnoreCase("' OR 1=1")){
                   return threatType.SQLI;
               }
           }
       }
       return threatType.NONE;
    }


}



//        if(parameterMap.values().stream().filter(Objects::nonNull).flatMap(Arrays::stream).anyMatch(s -> s.equalsIgnoreCase("blocked")))
//        {
//            response.sendError(403,"bad boy");
//        }
// above code is using java streams

