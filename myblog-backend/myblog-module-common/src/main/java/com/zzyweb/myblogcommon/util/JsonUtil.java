package com.zzyweb.myblogcommon.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhiyi
 * json 转换成字符串的工具类
 * 使用ObjectMapper
 **/
@Slf4j
public class JsonUtil {

    //静态成员变量用于静态方法调用
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static String toJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return object.toString();
        }
    }
}
