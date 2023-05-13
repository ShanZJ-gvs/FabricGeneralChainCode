package com.gvssimux.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gvssimux.pojo.QueryResult;
import com.gvssimux.pojo.QueryResultList;

import java.text.SimpleDateFormat;
import java.util.List;

public class JsonUtil {
    //简单的getJson
    public static String getJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    public static String getJson(Object obj,String dataFormat) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        //自定义日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormat);
        mapper.setDateFormat(simpleDateFormat);
        return  mapper.writeValueAsString(obj);
    }


    /**
     * 对集合进行转json
     * */
    public static String getJson(List<Object> objList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(objList);
        return json;
    }


    /**
     * 将fabric查询出来的字符串转为 QueryResultList
     * */
    public static QueryResultList jsonStrToQueryResultList(String jsonStr) {
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        QueryResultList resultList = JSON.toJavaObject(jsonObject, QueryResultList.class);
        System.out.println("JsonUtil工具 把jsonStr转为QueryResultList对象==》 "+resultList);
        return resultList;
    }

    /**
     * 将fabric查询出来的字符串转为 List<QueryResult>
     *
     * */
    public static List<QueryResult> jsonStrToList(String jsonStr) {

        QueryResultList queryResultList = jsonStrToQueryResultList(jsonStr);
        List<QueryResult> resultList = queryResultList.getResultList();
        System.out.println("JsonUtil工具 把jsonStr转为List数组==》 "+resultList);

        return resultList;
    }





}
