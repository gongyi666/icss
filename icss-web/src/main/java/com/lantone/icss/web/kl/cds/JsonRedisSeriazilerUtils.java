package com.lantone.icss.web.kl.cds;

import org.apache.commons.lang.SerializationException;
import org.codehaus.jackson.map.ObjectMapper;

import java.nio.charset.Charset;

/**
 * Created by langtong0002 on 2017/3/31.
 */
public class JsonRedisSeriazilerUtils {
    public static final String EMPTY_JSON = "{}";

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    protected static ObjectMapper objectMapper = new ObjectMapper();

    public JsonRedisSeriazilerUtils(){

    }

    /**
     * java-object as json-string
     * @param object
     * @return
     */
    public static String seriazileAsString(Object object){
        if (object== null) {
            return EMPTY_JSON;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception ex) {
            throw new SerializationException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }

    /**
     * json-string to java-object
     * @param str
     * @return
     */
    public static <T> T deserializeAsObject(String str,Class<T> clazz){
        if(str == null || clazz == null){
            return null;
        }
        try{
            return objectMapper.readValue(str, clazz);
        }catch (Exception ex) {
            throw new SerializationException("Could not read JSON: " + ex.getMessage(), ex);
        }
    }
}
