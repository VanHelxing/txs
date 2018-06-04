package cn.zhimadi.txs.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Json工具类
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
public class JsonUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * object to josn str
     * @param obj
     * @return
     */
    public static String objectToJson(Object obj) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(obj);
        return json;
    }

    /**
     * json str to clazz
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T jsonToPojo(String json, Class<T> clazz) throws IOException {
        T t = objectMapper.readValue(json, clazz);
        return t;
    }
}
