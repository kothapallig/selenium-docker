package org.seleniumdocker.utils;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtility {

    private static final Logger log = LoggerFactory.getLogger(JsonUtility.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getTestData(String path, Class<T> type){
        try(InputStream stream = ResourceLoader.getResource(path)){
            return mapper.readValue(stream, type);
        }catch (Exception e){
            log.error("unable to read test data {}", path, e);
        }
        return null;
    }


}
